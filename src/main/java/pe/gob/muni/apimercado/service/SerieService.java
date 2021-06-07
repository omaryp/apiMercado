package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Serie;
import pe.gob.muni.apimercado.repository.SerieRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class SerieService implements ISerieService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SerieRepository repository;
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private Validador<Serie> validadorSerie;
	
	@Override
	public PageInfo<Serie> pagingEntitys(Map<String, String>params)
			throws ApiException, Exception {
		logger.info("obteniendo series para busqueda {}.",params);
		try {
			List<Serie> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Serie>(rptaData);
		}catch (ApiException e) {
			logger.error("Error api paginando series  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando series  {} - {}",e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Serie entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorSerie.validarModelo(entity);
			if (validadorSerie.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorSerie.getErrores());
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando series  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando series  {} - {}",e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Serie entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorSerie.validarModelo(entity);
			if (validadorSerie.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorSerie.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(auth.getUserToken());
			repository.updateEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando series  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando series  {} - {}",e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		}catch (ApiException e) {
			logger.error("Error api eliminando serie {} - {} - {}",id,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando serie {} - {} - {}",id,e.getMessage(), e);
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
			logger.error("Error api buscando series  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando series  {} - {}",e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Serie getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obteniendo serie {} - {} - {}",id,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo serie {} - {} - {}",id,e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Serie> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obteniendo all series  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo all series  {} - {}",e.getMessage(), e);
			throw e;
		}
	}

}
