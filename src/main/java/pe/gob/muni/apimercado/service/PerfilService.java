package pe.demo.apirest.service;

import static pe.demo.apirest.utils.Constants.RESPONSE_LIST;
import static pe.demo.apirest.utils.Constants.RESPONSE_OBJECT;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.demo.apirest.model.Perfil;
import pe.demo.apirest.model.Rol;
import pe.demo.apirest.model.RolPerfil;
import pe.demo.apirest.model.RptaDataModel;
import pe.demo.apirest.repository.PerfilRepository;
import pe.demo.apirest.utils.ApiException;
import pe.demo.apirest.utils.Validador;
import pe.demo.apirest.utils.ValidatorException;

@Service
public class PerfilService implements IPerfilService {

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	PerfilRepository repository;
	
	@Autowired
	Validador<Perfil> validadorEntity;

	@Override
	public Perfil getEntity(int id) throws ApiException, Exception {
		logger.info("obteniendo perfiles del perfil {}", id);
		try {
			return repository.getEntity(id);
		} catch (ApiException e) {
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public List<Perfil> getAllEntitys() throws ApiException, Exception {
		logger.info("obteniendo todos los perfiles.");
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public RptaDataModel<Perfil> pagingEntitys(String valorBusqueda, int tipoBusqueda, int inicio, int fin)
			throws ApiException, Exception {
		logger.info("obteniendo entitys {} para busqueda {}.",this.getClass(), valorBusqueda);
		try {
			RptaDataModel<Perfil> rpta = new RptaDataModel<Perfil>();
			List<Perfil> rptaData = null;
			int totalReg = 0;
			totalReg = repository.totalRecordsEntity(valorBusqueda);
			rpta.setTotal(totalReg);
			if (totalReg != 0) {
				rptaData = repository.pagingEntitys(valorBusqueda, inicio, fin);
				rpta.setDatos(rptaData);
			}
			return rpta;
		} catch (ApiException e) {
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void saveEntity(Perfil entity) throws ApiException, Exception, ValidatorException {
		logger.info("guardando perfil {}", entity);
		try {
			validadorEntity.validarModelo(entity);
			if (validadorEntity.isHayErrores())
				throw new ValidatorException("Error de validación", validadorEntity.getErrores());
			repository.updateEntity(entity);
		} catch (ValidatorException e) {
			throw e;
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void updateEntity(Perfil entity) throws ApiException, Exception, ValidatorException {
		logger.info("actualizando perfil {}", entity);
		try {
			validadorEntity.validarModelo(entity);
			if (validadorEntity.isHayErrores())
				throw new ValidatorException("Error de validación", validadorEntity.getErrores());
			repository.updateEntity(entity);
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
		logger.info("eliminando perfil {}", id);
		try {
			repository.deleteEntity(id);
		} catch (ApiException e) {
			throw new ApiException(e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}

	}
	
	@Override
	public void guardarRolesPerfil(int perfil, List<Rol> roles) throws ApiException, Exception {
		logger.info("guardando roles del perfil {}", perfil);
		try {
			for (Rol rol : roles) {
				RolPerfil rolPef = new RolPerfil();
				rolPef.setCodigoPerfil(perfil);
				rolPef.setCodigoRol(rol.getCodigo());
				repository.guardarRolesPerfil(rolPef);
			}
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
			int tipo = Integer.parseInt(params.get("tipo"));
			int valor = Integer.parseInt(params.get("valor"));
			switch (tipo) {
				case RESPONSE_LIST:
					//colocar funcionalidad  de ser necesario
					break;

				case RESPONSE_OBJECT:
					rpta = repository.getEntity(valor);
					break;
			}
		}catch(NullPointerException e) {
			rpta = repository.getAllEntitys();
		} 
		catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return rpta;
	}

}
