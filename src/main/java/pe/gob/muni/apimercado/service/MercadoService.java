package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Mercado;
import pe.gob.muni.apimercado.repository.MercadoRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class MercadoService implements IMercadoService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private MercadoRepository repository;

	@Autowired
	private Validador<Mercado> validadorMercado;
	
	@Override
	public PageInfo<Mercado> pagingEntitys(Map<String,String> params)throws ApiException, Exception {
		logger.info("obteniendo mercados con los filtros {}.",objectToJson(params));
		try {
			List<Mercado> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Mercado>(rptaData);
		}catch (ApiException e) {
			logger.error("Error api paginando entidades mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Mercado entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorMercado.validarModelo(entity);
			if (validadorMercado.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorMercado.getErrores());
			entity.setFecha_creacion(new Date());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			logger.error("Error api validando entidad mercado {} - {}", e.getMessage(), e.getErrores());
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando entidades mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidades mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Mercado entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorMercado.validarModelo(entity);
			if (validadorMercado.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorMercado.getErrores());
			repository.updateEntity(entity);
		}catch (ApiException e) {
			logger.error("Error api actualizando entidades mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidades mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		}catch (ApiException e) {
			logger.error("Error api eliminando entidades mercado {} - {} - {}",id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidades mercado {} - {} - {}",id, e.getMessage(), e);
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
			logger.error("Error api buscando entidad mercado  {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando entidad mercado  {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Mercado getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obteniendo entidad mercado {} - {} - {}",id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad mercado {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Mercado> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		}catch (ApiException e) {
			logger.error("Error api obteniendo entidades mercado  {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidades mercado  {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
