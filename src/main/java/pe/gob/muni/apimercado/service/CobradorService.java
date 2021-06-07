package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Cobrador;
import pe.gob.muni.apimercado.repository.CobradorRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class CobradorService implements ICobradorService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CobradorRepository repository;

	@Autowired
	private Validador<Cobrador> validadorCobrador;
	
	@Override
	public PageInfo<Cobrador> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo comerciantes con los filtros {}.",objectToJson(params));
		try {
			List<Cobrador> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = procesarLista(repository.pagingEntitys(pagData));
				
			return new PageInfo<Cobrador>(rptaData);
		}catch (ApiException e) {
			logger.error("Error api paginando entidades cobrador {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades cobrador {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	private List<Cobrador> procesarLista(List<Cobrador> datos){
		List<Cobrador> rpta = new ArrayList<Cobrador>();
		datos.forEach((entity) -> {
			entity.setId(entity.getPersonas_id());
			rpta.add(entity);
		});
		return rpta;
	}

	@Override
	public void saveEntity(Cobrador entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorCobrador.validarModelo(entity);
			if (validadorCobrador.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorCobrador.getErrores());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando entidad cobrador {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidad cobrador {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Cobrador entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorCobrador.validarModelo(entity);
			if (validadorCobrador.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorCobrador.getErrores());
			repository.updateEntity(entity);
		}catch (ApiException e) {
			logger.error("Error api actualizando entidad cobrador {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidad cobrador {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		}catch (ApiException e) {
			logger.error("Error api eliminando entidad cobrador {} - {} - {}",id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidad cobrador {} - {} - {}",id, e.getMessage(), e);
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
						rpta = getEntity(codigo);
						break;
				}
			}else
				rpta = procesarLista(getAllEntitys());
			
		}catch (ApiException e) {
			logger.error("Error api buscando entidad cobrador {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general buscando entidad cobrador {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Cobrador getEntity(int id) throws ApiException, Exception {
		try {
			Cobrador entity = repository.getEntity(id);
			entity.setId(entity.getPersonas_id());
			return entity;
		}catch (ApiException e) {
			logger.error("Error api obteniendo entidad cobrador {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo entidad cobrador {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cobrador> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		}catch (ApiException e) {
			logger.error("Error api obteniendo all entitys cobrador {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo all entitys cobrador {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
