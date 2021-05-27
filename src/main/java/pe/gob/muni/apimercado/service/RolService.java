package pe.gob.muni.apimercado.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.repository.RolRepository;
import pe.gob.muni.apimercado.utils.ApiException;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;

import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class RolService implements IRolService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private Validador<Rol> validadorRol;

	@Override
	public List<Rol> getRolesByPerfil(int perfil) throws ApiException, Exception {
		logger.info("obteniendo roles del perfil {}", perfil);
		try {
			return rolRepository.getRolesByPerfil(perfil);
		} catch (ApiException e) {
			logger.error("Error api obteniendo roles by perfil {} - {} - {}",perfil,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo roles by perfil {} - {} - {}",perfil, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Rol> getRolesByUsuario(String usuario) throws ApiException, Exception {
		logger.info("obteniendo roles del usuario {}", usuario);
		try {
			return rolRepository.getRolesByUsuario(usuario);
		} catch (ApiException e) {
			logger.error("Error api obteniendo roles by usuario {} - {} - {}",usuario,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo roles by usuario {} - {} - {}",usuario, e.getMessage(), e);
			throw e;
		}

	}
	
	@Override
	public List<Rol> getRolesByUsuarioModulo(String usuario,int modulo) throws ApiException, Exception {
		logger.info("obteniendo roles del usuario - modulo {} - {}", usuario, modulo);
		try {
			return rolRepository.getRolesByUsuarioModulo(usuario,modulo);
		} catch (ApiException e) {
			logger.error("Error api obteniendo roles by usuario-modulo {} - {} - {} - {}",usuario,modulo,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo roles by usuario {} - {} - {} - {}",usuario,modulo, e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public Rol getEntity(int id) throws ApiException, Exception {
		logger.info("obteniendo rol {}", id);
		try {
			return rolRepository.getEntity(id);
		}  catch (ApiException e) {
			logger.error("Error api obteniendo rol {} - {} - {}",id,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo rol {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Rol> getAllEntitys() throws ApiException, Exception {
		logger.info("obteniendo todos los roles.");
		try {
			return rolRepository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obteniendo all roles {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo all roles {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public PageInfo<Rol> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo roles para busqueda {}.", params);
		try {
			List<Rol> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = rolRepository.pagingEntitys(pagData);
				
			return new PageInfo<Rol>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando roles {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando roles {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Rol entity) throws ApiException, Exception, ValidatorException {
		logger.info("guardando rol {}", entity.getNombre());
		try {
			validadorRol.validarModelo(entity);
			if (validadorRol.isHayErrores())
				throw new ValidatorException("Error de validación", validadorRol.getErrores());
			rolRepository.saveEntity(entity);
		}  catch (ValidatorException e) {
			logger.error("Error api validacion guardando rol {} - {} - {} ",e.getMessage(),e.getErrores(), e);
			throw e;
		} catch (ApiException e) {
			logger.error("Error api guardando rol {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando rol {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Rol entity) throws ApiException, Exception, ValidatorException {
		logger.info("actualizando rol {}", entity.getNombre());
		try {
			validadorRol.validarModelo(entity);
			if (validadorRol.isHayErrores())
				throw new ValidatorException("Error de validación", validadorRol.getErrores());
			rolRepository.updateEntity(entity);
		}catch (ValidatorException e) {
			logger.error("Error api validacion actualizando rol {} - {} - {} ",e.getMessage(),e.getErrores(), e);
			throw e;
		} catch (ApiException e) {
			logger.error("Error api actualizando rol {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando rol {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		logger.info("eliminando rol {}", id);
		try {
			rolRepository.deleteEntity(id);
		}  catch (ApiException e) {
			logger.error("Error api eliminando rol {} - {} - {}",id,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando rol {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		Object rpta = null;
		try {
			if(!params.isEmpty()) {
				int tipo = Integer.parseInt(params.get("tipo"));
				int valor = Integer.parseInt(params.get("valor"));
				switch (tipo) {
					case RESPONSE_LIST:
						rpta = rolRepository.getRolesByPerfil(valor);
						break;

					case RESPONSE_OBJECT:
						rpta = rolRepository.getEntity(valor);
						break;
				}
			}else 
				rpta = rolRepository.getAllEntitys();
			
		}catch (ApiException e) {
			logger.error("Error api buscando rol {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando rol {} - {}",e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

}
