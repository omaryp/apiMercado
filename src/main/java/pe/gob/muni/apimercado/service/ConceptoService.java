package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Concepto;
import pe.gob.muni.apimercado.repository.ConceptoRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class ConceptoService implements IConceptoService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ConceptoRepository repository;
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private Validador<Concepto> validadorConcepto;
	
	@Override
	public PageInfo<Concepto> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo conceptos con los filtros {}.",objectToJson(params));
		try {
			List<Concepto> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Concepto>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando entidades concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades concepto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Concepto entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorConcepto.validarModelo(entity);
			if (validadorConcepto.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorConcepto.getErrores());
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		} catch (ApiException e) {
			logger.error("Error api guardando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Concepto entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorConcepto.validarModelo(entity);
			if (validadorConcepto.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorConcepto.getErrores());
			entity.setModifcado_por(auth.getUserToken());
			entity.setFecha_modifcacion(new Date());
			repository.updateEntity(entity);
		} catch (ApiException e) {
			logger.error("Error api actualizando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		} catch (ApiException e) {
			logger.error("Error api eliminando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidad concepto {} - {}", e.getMessage(), e);
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
			logger.error("Error api buscando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Concepto getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obteniendo entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad concepto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Concepto> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obteniendo todas las entidades concepto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo todas las entidades concepto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
