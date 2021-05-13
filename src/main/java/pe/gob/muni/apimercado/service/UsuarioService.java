package pe.gob.muni.apimercado.service;

import static java.util.Collections.emptyList;
import static pe.gob.muni.apimercado.utils.Constants.POR_CODIGO;
import static pe.gob.muni.apimercado.utils.Constants.POR_USUARIO;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;

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

import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.model.RptaDataModel;
import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.UsuarioDto;
import pe.gob.muni.apimercado.repository.UsuarioRespository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UsuarioRespository usuarioRepository;

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
			user = usuarioRepository.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
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
			logger.info("Error al momento de la autenticación {}", e);
		}
		return userDto;
	}

	@Override
	public Usuario findByUsername(String username) throws Exception, ApiException {
		try {
			return usuarioRepository.findByUsername(username);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void disabledUserbyUsername(String username) throws Exception, ValidatorException, ApiException {
		try {
			usuarioRepository.disabledUserbyUsername(username);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void actualizarUserByUsername(Usuario user) throws Exception, ValidatorException, ApiException {
		try {
			validadorUsuario.validarModelo(user);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			usuarioRepository.actualizarUserByUsername(user);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public RptaDataModel<Usuario> pagingEntitys(String valorBusqueda, int tipoBusqueda, int inicio, int fin)
			throws ApiException, Exception {
		logger.info("obteniendo roles para busqueda {}.",valorBusqueda);
		try {
			RptaDataModel<Usuario> rpta = new RptaDataModel<Usuario>();
			List<Usuario> rptaData = null;
			int totalReg = 0;
			totalReg = usuarioRepository.totalRecordsEntity(valorBusqueda);
			rpta.setTotal(totalReg);
			if(totalReg != 0) {
				rptaData = usuarioRepository.pagingEntitys(valorBusqueda, inicio, fin);
				rpta.setDatos(rptaData);
			}
			return rpta;
		} catch (ApiException e) {
			throw e;
		}catch (Exception e) {
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
			usuarioRepository.saveEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void updateEntity(Usuario entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorUsuario.validarModelo(entity);
			if (validadorUsuario.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorUsuario.getErrores());
			usuarioRepository.updateEntity(entity);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			usuarioRepository.deleteEntity(id);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		Object rpta = null;
		String usuario = "";
		int codigo = 0;
		try {
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
							rpta = usuarioRepository.findByUsername(usuario);
							break;
						case POR_CODIGO:
							codigo = Integer.parseInt(params.get("codigo"));
							logger.info("Buscando usuario por codigo - {}", codigo);
							rpta = usuarioRepository.getEntity(codigo);
							break;
						}
					break;
			}
		}catch(NullPointerException e) {
			rpta = usuarioRepository.getAllEntitys();
		} 
		catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return rpta;
	}

	@Override
	public Usuario getEntity(int id) throws ApiException, Exception {
		try {
			return usuarioRepository.getEntity(id);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Usuario> getAllEntitys() throws ApiException, Exception {
		try {
			return usuarioRepository.getAllEntitys();
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
