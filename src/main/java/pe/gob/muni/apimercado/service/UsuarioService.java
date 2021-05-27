package pe.gob.muni.apimercado.service;

import static java.util.Collections.emptyList;
import static pe.gob.muni.apimercado.utils.Constants.POR_CODIGO;
import static pe.gob.muni.apimercado.utils.Constants.POR_USUARIO;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Modulo;
import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.ModuloDto;
import pe.gob.muni.apimercado.model.dto.UsuarioDto;
import pe.gob.muni.apimercado.repository.UsuarioRespository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UsuarioRespository repository;
	
	@Autowired
	private IModuloService modService;

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
			user = repository.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException(username + "no existe.");
			}
			logger.info("Cargando usuario {}", Util.objectToJson(user));
			roles = rolService.getRolesByUsuario(username);
			perfil = perfilService.getEntity(user.getCodigoPerfil());

			userDto = new UsuarioDto(user.getUsuario(), user.getPassword(), user.isActivo(), true, true, true, roles);
			userDto.setNombres(user.getNombres());
			userDto.setApellidos(user.getApellidos());
			userDto.setCorreo(user.getCorreo());
			userDto.setPerfil(user.getCodigoPerfil());
			userDto.setNombrePerfil(perfil.getNombre());
		} catch (Exception e) {
			logger.error("Error al autenticar {}", e);
			throw new UsernameNotFoundException("Error interno al iniciar session. "+e.getMessage(),e);
		}
		return userDto;
	}
	
	@Override
	public Usuario findByUsername(String username) throws Exception, ApiException {
		try {
			return repository.findByUsername(username);
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
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Usuario>(rptaData);
		}catch (ApiException e) {
			logger.error("Error api paginando usuario  {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general paginando usuario   {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Usuario entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorUsuario.validarModelo(entity);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			repository.saveEntity(entity);
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
	public void updateEntity(Usuario entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorUsuario.validarModelo(entity);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			repository.updateEntity(entity);
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
			repository.deleteEntity(id);
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
								usuario = params.get("user");
								logger.info("Buscando usuario por username - {}", usuario);
								rpta = repository.findByUsername(usuario);
								break;
							case POR_CODIGO:
								codigo = Integer.parseInt(params.get("codigo"));
								logger.info("Buscando usuario por codigo - {}", codigo);
								rpta = repository.getEntity(codigo);
								break;
							}
						break;
				}
			}else
				rpta = repository.getAllEntitys();
			
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
			return repository.getEntity(id);
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

}
