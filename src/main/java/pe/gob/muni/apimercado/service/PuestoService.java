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

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.repository.PuestoRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class PuestoService implements IPuestoService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PuestoRepository repository;

	@Autowired
	private Validador<Puesto> validadorPuesto;
	
	@Override
	public PageInfo<Puesto> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("paginando puestos con parámetros {}.",params);
		try {
			List<Puesto> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Puesto>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Puesto entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPuesto.validarModelo(entity);
			if (validadorPuesto.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPuesto.getErrores());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			logger.error("Error api validacion guardando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (ApiException e) {
			logger.error("Error api guardando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Puesto entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPuesto.validarModelo(entity);
			if (validadorPuesto.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPuesto.getErrores());
			repository.updateEntity(entity);
		} catch (ApiException e) {
			logger.error("Error api actualizando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		}catch (ApiException e) {
			logger.error("Error api eliminando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		Object rpta = null;
		int codigo = 0;
		try {
			if(!params.isEmpty()) {
				int tipo = Integer.parseInt(params.get("tipo"));
				switch (tipo) {
					case RESPONSE_LIST:
						//colocar aqui funcionalidad, si es necesaria
						break;
					case RESPONSE_OBJECT:
						codigo = Integer.parseInt(params.get("codigo"));
						logger.info("Buscando usuario por codigo - {}", codigo);
						rpta = repository.getEntity(codigo);
						break;
				}
			}else
				rpta = repository.getAllEntitys();
			
		}catch (ApiException e) {
			logger.error("Error api buscando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Puesto getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obtiendo entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Puesto> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obtiendo all entitys puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo all entitys puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void asociarPuestoComerciante(PuestoComerciante puesto) throws ApiException {
		try {
			repository.asociarPuestoComerciante(puesto);
		}catch (ApiException e) {
			logger.error("Error api asociando puesto comerciante {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general asociando puesto comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
