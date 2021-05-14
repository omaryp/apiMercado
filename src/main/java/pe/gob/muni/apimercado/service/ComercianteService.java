package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.muni.apimercado.model.RptaDataModel;
import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.repository.ComercianteRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class ComercianteService implements IComercianteService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ComercianteRepository repository;

	@Autowired
	private Validador<Comerciante> validadorComerciante;
	
	@Override
	public RptaDataModel<Comerciante> pagingEntitys(String valorBusqueda, int tipoBusqueda, int inicio, int fin)
			throws ApiException, Exception {
		logger.info("obteniendo roles para busqueda {}.",valorBusqueda);
		try {
			RptaDataModel<Comerciante> rpta = new RptaDataModel<Comerciante>();
			List<Comerciante> rptaData = null;
			int totalReg = 0;
			totalReg = repository.totalRecordsEntity(valorBusqueda);
			rpta.setTotal(totalReg);
			if(totalReg != 0) {
				rptaData = repository.pagingEntitys(valorBusqueda, inicio, fin);
				rpta.setDatos(rptaData);
			}
			return rpta;
		} catch (ApiException e) {
			throw e;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void saveEntity(Comerciante entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorComerciante.validarModelo(entity);
			if (validadorComerciante.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorComerciante.getErrores());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			throw e;
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void updateEntity(Comerciante entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorComerciante.validarModelo(entity);
			if (validadorComerciante.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorComerciante.getErrores());
			repository.updateEntity(entity);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
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
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return rpta;
	}

	@Override
	public Comerciante getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Comerciante> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
