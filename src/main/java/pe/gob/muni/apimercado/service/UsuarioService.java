package pe.gob.muni.apimercado.service;

import static java.util.Collections.emptyList;
import static pe.gob.muni.apimercado.utils.Constants.POR_CODIGO;
import static pe.gob.muni.apimercado.utils.Constants.POR_USUARIO;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Constants.PERFIL_COBRADOR;
import static pe.gob.muni.apimercado.utils.Constants.SECRET_PASSWORD;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.getPersona;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Cobrador;
import pe.gob.muni.apimercado.model.Modulo;
import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.Persona;
import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.PermisoDto;
import pe.gob.muni.apimercado.model.dto.UsuarioDto;
import pe.gob.muni.apimercado.model.dto.UsuarioSessionDto;
import pe.gob.muni.apimercado.repository.CobradorRepository;
import pe.gob.muni.apimercado.repository.ModuloRepository;
import pe.gob.muni.apimercado.repository.PersonaRepository;
import pe.gob.muni.apimercado.repository.UsuarioRespository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UsuarioRespository repository;
	
	@Autowired
	private ModuloRepository modRepository;
	
	@Autowired
	private PersonaRepository perRepository;
	
	@Autowired
	private CobradorRepository cobRepository;
	
	@Autowired
	private IRolService rolService;

	@Autowired
	private IPerfilService perfilService;

	@Autowired
	private Validador<Usuario> validadorUsuario;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = null;
		List<Rol> roles = emptyList();
		UsuarioDto userDto = null;
		Perfil perfil = null;

		try {
			user = findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException(username + "no existe.");
			}

			logger.info("Cargando usuario {}", Util.objectToJson(user));
			roles = rolService.getRolesByUsuario(username);
			perfil = perfilService.getEntity(user.getPerfiles_codigo());

			userDto = new UsuarioDto(user.getUsuario(), user.getPassword(), user.isActivo(), true, true, true, roles);
			userDto.setId(user.getId());
			userDto.setNombres(user.getNombres());
			userDto.setApellidos(user.getApellidos());
			userDto.setCorreo(user.getCorreo());
			userDto.setPerfil(user.getPerfiles_codigo());
			userDto.setNombrePerfil(perfil.getNombre());
			userDto.setModulos(getAuthorities(username, roles));
			
		} catch (Exception e) {
			logger.error("Error al autenticar {}", e);
			throw new UsernameNotFoundException("Error interno al iniciar session. "+e.getMessage(),e);
		}
		return userDto;
	}
	
	private List<PermisoDto> getAuthorities(String usuario ,List<Rol> roles) throws ApiException, Exception{
		final List<PermisoDto> permisos = new ArrayList<PermisoDto>();
		List<Modulo> modUser = null;
		Map<Integer, Modulo> modulos = null;
		
		try {
			
			modUser = modRepository.getModulosByUsuario(usuario);
			modulos = modUser.stream().collect(Collectors.toMap(Modulo::getId, mod -> mod));
			final Map<Integer, List<Rol>> rolModulo = roles.stream().collect(Collectors.groupingBy(Rol::getModulos_codigo));
			modulos.forEach((key,modulo) -> {
				PermisoDto permiso = new PermisoDto();
				permiso.setModulo(modulo);
				permiso.setRoles(rolModulo.get(key));
				permisos.add(permiso);
			});
			
		}catch (ApiException e) {
			throw e;
		}catch (Exception e) {
			throw e;
		}
		return permisos;
	}
	
	@Override
	public Usuario findByUsername(String username) throws Exception, ApiException {
		try {
			Usuario entity = repository.findByUsername(username);
			return entity;
		}catch (ApiException e) {
			logger.error("Error api get usuario by username {} - {} - {}",username, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general get usuario by username {} - {} - {}",username, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void disabledUserbyUsername(String username) throws Exception, ValidatorException, ApiException {
		try {
			repository.disabledUserbyUsername(username);
		}catch (ApiException e) {
			logger.error("Error api deshabilitando usuario by username {} - {} - {}",username, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general deshabilitando usuario by username {} -  {} - {}",username, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void actualizarUserByUsername(Usuario user) throws Exception, ValidatorException, ApiException {
		try {
			validadorUsuario.validarModelo(user);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			repository.actualizarUserByUsername(user);
		}catch (ApiException e) {
			logger.error("Error api actualizando usuario by username {} - {} - {}",user.getUsuario(), e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general actualizando usuario by username {} -  {} - {}",user.getUsuario(), e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public PageInfo<Usuario> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("Paginando usuarios{}.",params);
		try {
			List<Usuario> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
			PageInfo<Usuario> rpta  = new PageInfo<Usuario>(rptaData);
			rpta.setList(procesarLista(rpta.getList()));
				
			return rpta;
		}catch (ApiException e) {
			logger.error("Error api paginando usuario  {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general paginando usuario   {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	private List<Usuario> procesarLista(List<Usuario> datos){
		List<Usuario> rpta = new ArrayList<Usuario>();
		datos.forEach((entity) -> {
			entity.setIdUsuario(entity.getId());
			entity.setPassword(SECRET_PASSWORD);
			entity.setPassword2(SECRET_PASSWORD);
			rpta.add(entity);
		});
		return rpta;
	}

	@Override
	public void saveEntity(Usuario entity) throws ApiException, Exception, ValidatorException {
		Persona padre = null;
		try {
			validadorUsuario.validarModelo(entity);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			
			
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(getUserToken());
			entity.setEstado(1);
			padre = getPersona(entity);
			padre.setEstado(1);
			perRepository.saveEntity(padre);
			
			entity.setPersonas_id(padre.getId());
			entity.setUsuario(entity.getUsuario().toUpperCase());
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			entity.setActivo(true);
			repository.saveEntity(entity);
			
			if(entity.getPerfiles_codigo() == PERFIL_COBRADOR) {
				Cobrador cob = new Cobrador();
				cob.setMercados_id(entity.getMercados_id());
				cob.setPersonas_id(padre.getId());
				cobRepository.saveEntity(cob);
			}
				
		}catch (ValidatorException e) {
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando usuario  {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general guardando usuario   {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public int getUserToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		return Integer.parseInt(user.split("-")[0]);
	}

	@Override
	public void updateEntity(Usuario entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorUsuario.validarModelo(entity);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(getUserToken());
			
			Persona padre = getPersona(entity);
			perRepository.updateEntity(padre);
			
			if(!entity.getPassword().equals(SECRET_PASSWORD)) {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
				repository.updateEntity(entity);
			}else
				repository.updateEntityUsuario(entity);
				
			if(entity.getPerfiles_codigo() == PERFIL_COBRADOR) {
				Cobrador cob = new Cobrador();
				cob.setMercados_id(entity.getMercados_id());
				cob.setPersonas_id(padre.getId());
				cobRepository.deleteEntity(padre.getId());
				cobRepository.saveEntity(cob);
			}
		}catch (ApiException e) {
			logger.error("Error api actualizando usuario  {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general actualizando usuario   {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			Usuario userTmp = repository.getEntity(id);
			repository.deleteEntity(id);
			perRepository.deleteEntity(userTmp.getPersonas_id());
		}catch (ApiException e) {
			logger.error("Error api eliminando usuario by username {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general eliminando usuario by username {} -  {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		Object rpta = null;
		String usuario = "";
		int codigo = 0;
		try {
			if(!params.isEmpty()) {
				int tipo = Integer.parseInt(params.get("tipo"));
				int busqueda = Integer.parseInt(params.get("bus"));
				switch (tipo) {
					case RESPONSE_LIST:
						//colocar aqui funcionalidad, si es necesaria
						break;
					case RESPONSE_OBJECT:
						switch (busqueda) {
							case POR_USUARIO:
								usuario = params.get("user").toUpperCase();
								logger.info("Buscando usuario por username - {}", usuario);
								rpta = findByUsername(usuario);
								break;
							case POR_CODIGO:
								codigo = Integer.parseInt(params.get("codigo"));
								logger.info("Buscando usuario por codigo - {}", codigo);
								rpta = getEntity(codigo);
								break;
							}
						break;
				}
			}else
				rpta = procesarLista(getAllEntitys());
			
		}catch (ApiException e) {
			logger.error("Error api buscando usuario by username {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general buscando usuario by username {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Usuario getEntity(int id) throws ApiException, Exception {
		try {
			Usuario entity = repository.getEntity(id);
			entity.setIdUsuario(entity.getId());
			entity.setPassword(SECRET_PASSWORD);
			entity.setPassword2(SECRET_PASSWORD);
			return entity;
		}catch (ApiException e) {
			logger.error("Error api obteniendo usuario by id {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo usuario by id {} -  {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Usuario> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		}catch (ApiException e) {
			logger.error("Error api obteniendo all entitys usuario {} - {}",e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo all entitys usuario  {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public UsuarioSessionDto getDatosSession(int id) throws Exception, ValidatorException, ApiException {
		// TODO Auto-generated method stub
		return null;
	}

}
