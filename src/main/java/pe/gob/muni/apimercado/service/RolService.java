package pe.gob.muni.apimercado.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.model.RptaDataModel;
import pe.gob.muni.apimercado.repository.RolRepository;
import pe.gob.muni.apimercado.utils.ApiException;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;

import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class RolService implements IRolService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private Validador<Rol> validadorRol;

	@Override
	public List<Rol> getRolesByPerfil(int perfil) throws ApiException, Exception {
		// TODO Auto-generated method stub
		logger.info("obteniendo roles del perfil {}", perfil);
		try {
			return rolRepository.getRolesByPerfil(perfil);
		} catch (ApiException e) {
			// TODO: handle exception
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public List<Rol> getRolesByUsuario(String usuario) throws ApiException, Exception {
		// TODO Auto-generated method stub
		logger.info("obteniendo roles del usuario {}", usuario);
		try {
			return rolRepository.getRolesByUsuario(usuario);
		} catch (ApiException e) {
			// TODO: handle exception
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage(), e);
		}

	}

	@Override
	public Rol getEntity(int id) throws ApiException, Exception {
		logger.info("obteniendo rol {}", id);
		try {
			return rolRepository.getEntity(id);
		} catch (ApiException e) {
			// TODO: handle exception
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public List<Rol> getAllEntitys() throws ApiException, Exception {
		logger.info("obteniendo todos los roles.");
		try {
			return rolRepository.getAllEntitys();
		} catch (ApiException e) {
			// TODO: handle exception
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public RptaDataModel<Rol> pagingEntitys(String valorBusqueda, int tipoBusqueda, int inicio, int fin)
			throws ApiException, Exception {
		logger.info("obteniendo roles para busqueda {}.", valorBusqueda);
		try {
			RptaDataModel<Rol> rpta = new RptaDataModel<Rol>();
			List<Rol> rptaData = null;
			int totalReg = 0;
			totalReg = rolRepository.totalRecordsEntity(valorBusqueda);
			rpta.setTotal(totalReg);
			if (totalReg != 0) {
				rptaData = rolRepository.pagingEntitys(valorBusqueda, inicio, fin);
				rpta.setDatos(rptaData);
			}
			return rpta;
		} catch (ApiException e) {
			// TODO: handle exception
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage(), e);
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
			throw e;
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
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
		} catch (ValidatorException e) {
			throw e;
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		logger.info("eliminando rol {}", id);
		try {
			rolRepository.deleteEntity(id);
		} catch (ApiException e) {
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
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
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return rpta;
	}

}
