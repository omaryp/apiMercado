package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.model.RolPerfil;
import pe.gob.muni.apimercado.repository.PerfilRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

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
	public PageInfo<Perfil> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo entitys {} para busqueda {}.",this.getClass(), params);
		try {
			List<Perfil> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Perfil>(rptaData);
		} catch (ApiException e) {
			throw e;
		}catch (Exception e) {
			throw e;
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
			if(!params.isEmpty()) {
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
			}else
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
