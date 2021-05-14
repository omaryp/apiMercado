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
import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.repository.TarifaRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class TarifaService implements ITarifaService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private TarifaRepository repository;

	@Autowired
	private Validador<Tarifa> validadorTarifa;
	
	@Override
	public RptaDataModel<Tarifa> pagingEntitys(String valorBusqueda, int tipoBusqueda, int inicio, int fin)
			throws ApiException, Exception {
		logger.info("obteniendo roles para busqueda {}.",valorBusqueda);
		try {
			RptaDataModel<Tarifa> rpta = new RptaDataModel<Tarifa>();
			List<Tarifa> rptaData = null;
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
	public void saveEntity(Tarifa entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorTarifa.validarModelo(entity);
			if (validadorTarifa.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorTarifa.getErrores());
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
	public void updateEntity(Tarifa entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorTarifa.validarModelo(entity);
			if (validadorTarifa.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorTarifa.getErrores());
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
	public Tarifa getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Tarifa> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
