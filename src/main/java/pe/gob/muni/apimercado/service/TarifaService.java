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

import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.repository.TarifaRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Service
public class TarifaService implements ITarifaService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private TarifaRepository repository;

	@Autowired
	private Validador<Tarifa> validadorTarifa;
	
	@Autowired
	private IUsuarioService auth;
	
	@Override
	public PageInfo<Tarifa> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("paginando tarifas {}.",params);
		try {
			List<Tarifa> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Tarifa>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando tarifas  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando tarifas  {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Tarifa entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorTarifa.validarModelo(entity);
			if (validadorTarifa.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorTarifa.getErrores());
			entity.setCreado_por(auth.getUserToken());
			entity.setFecha_creacion(new Date());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando tarifas  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando tarifas  {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Tarifa entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorTarifa.validarModelo(entity);
			if (validadorTarifa.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorTarifa.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(auth.getUserToken());
			repository.updateEntity(entity);
		}catch (ApiException e) {
			logger.error("Error api actualizando tarifa  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando tarifa  {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		}catch (ApiException e) {
			logger.error("Error api eliminando tarifa {} - {} - {}",id,e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando tarifa {} - {} - {}",id, e.getMessage(), e);
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
			logger.error("Error api buscando tarifa  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando tarifa  {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Tarifa getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obteniendo tarifa {} - {} - {}",id,e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo tarifa {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Tarifa> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		}catch (ApiException e) {
			logger.error("Error api obteniendo all tarifas {} - {}",e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo al tarifas {} - {}",e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Tarifa getTarifaPuesto(int idPuesto) throws ApiException, Exception {
		try {
			return repository.getTarifaPuesto(idPuesto);
		}catch (ApiException e) {
			logger.error("Error api obteniendo all tarifa por puesto {} - {} - {}",idPuesto,e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo al tarifa por puesto  {} - {} - {}",idPuesto,e.getMessage(), e);
			throw e;
		}
	}

}
