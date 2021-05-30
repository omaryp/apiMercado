package pe.gob.muni.apimercado.service;


import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.repository.PuestoComercianteRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class PuestoComercianteService implements IPuestoComercianteService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PuestoComercianteRepository repository;

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PuestoComerciante getEntity(int id) throws ApiException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PuestoComerciante> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		}catch (ApiException e) {
			logger.error("Error api obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public PageInfo<PuestoComerciante> pagingEntitys(Map<String, String> params) throws ApiException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEntity(PuestoComerciante entity) throws ApiException, Exception, ValidatorException {
		try {
			repository.saveEntity(entity);
		}catch (ApiException e) {
			logger.error("Error api guardando entidad puestocomerciante {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidad puestocomerciante {} - {}", e.getMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void updateEntity(PuestoComerciante entity) throws ApiException, Exception, ValidatorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PuestoComerciante> getAllPuestosActive() throws ApiException {
		try {
			return repository.getAllPuestosActive();
		}catch (ApiException e) {
			logger.error("Error api obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<PuestoComerciante> getAllPuestosMercado(int mercados_id) throws ApiException {
		try {
			return repository.getAllPuestosMercado(mercados_id);
		}catch (ApiException e) {
			logger.error("Error api obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	
}
