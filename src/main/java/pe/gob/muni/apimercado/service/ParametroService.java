package pe.gob.muni.apimercado.service;


import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.muni.apimercado.model.Parametro;
import pe.gob.muni.apimercado.repository.ParametroRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class ParametroService implements IParametroService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ParametroRepository repository;
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private Validador<Parametro> validadorPago;


	@Override
	public Parametro getEntity(int codigo, int subcodigo) throws ApiException {
		try {
			return repository.getEntity(codigo,subcodigo);
		} catch (ApiException e) {
			logger.error("Error api getEntity  Parametro  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general  getEntity Parametro     {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Parametro> getAllEntitys(int codigo) throws ApiException {
		try {
			return repository.getAllEntitys(codigo);
		} catch (ApiException e) {
			logger.error("Error api get tabla Parametro  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general  get tabla Parametro     {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Parametro entity) throws ApiException,Exception,ValidatorException{
		try {
			validadorPago.validarModelo(entity);
			if (validadorPago.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPago.getErrores());
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
		} catch (ValidatorException e) {
			logger.error("Error api validación guardando Parametro {} - {} - {}",e.getMessage(), e.getErrores(),e);
			throw e;
		} catch (ApiException e) {
			logger.error("Error api guardando Parametro  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando Parametro     {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveAllEntity(List<Parametro> items) throws ApiException,Exception{
		try {
			repository.saveAllEntity(items);
		} catch (ApiException e) {
			logger.error("Error api guardando all Parametro  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando all Parametro    {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Parametro entity) throws ApiException,Exception {
		try {
			repository.updateEntity(entity);
		} catch (ApiException e) {
			logger.error("Error api update Parametro  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error update Parametro     {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int codigo, int subcodigo) throws ApiException {
		try {
			repository.deleteEntity(codigo,subcodigo);
		} catch (ApiException e) {
			logger.error("Error api delete Parametro  {} - {}",e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error delete Parametro     {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
