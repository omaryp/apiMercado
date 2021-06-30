package pe.gob.muni.apimercado.service;

import java.util.Date;
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
	
	@Autowired
	private IUsuarioService auth;

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PuestoComerciante getEntity(int id) throws ApiException, Exception {
		return repository.getEntity(id);
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
			PuestoComerciante actual = getPuestoComercianteMercado(entity.getComerciantes_id(),entity.getMercados_id());
			if(null != actual) {
				actual.setFecha_fin(new Date());
				actual.setFecha_modifcacion(new Date());
				actual.setEstado(0);
				actual.setModifcado_por(auth.getUserToken());
				repository.updateEntity(actual);
			}
			entity.setCreado_por(auth.getUserToken());
			entity.setFecha_creacion(new Date());
			entity.setEstado(1);
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
	public void eliminarPuestoComerciante(int comerciante,int puesto)throws ApiException, Exception{
		try {
			repository.eliminarPuestoComerciante(comerciante,puesto);
		}catch (ApiException e) {
			logger.error("Error api eliminar puesto {} - comerciante {}", puesto, comerciante, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general puesto {} - comerciante {}",puesto, comerciante, e.getMessage(), e);
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
	
	@Override
	public PuestoComerciante getPuestoComercianteMercado(int comerciantes_id,int mercados_id)throws ApiException {
		try {
			return repository.getPuestoComercianteMercado(comerciantes_id, mercados_id);
		}catch (ApiException e) {
			logger.error("Error api obtener puestos por comerciante y mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obtener puestos por comerciante y mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	
}
