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

import pe.gob.muni.apimercado.model.Partida;
import pe.gob.muni.apimercado.repository.PartidaRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Service
public class PartidaService implements IPartidaService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PartidaRepository repository;
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private Validador<Partida> validadorPartida;
	
	@Override
	public PageInfo<Partida> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo partidas con los filtros {}.",objectToJson(params));
		try {
			List<Partida> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Partida>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando entidades partida {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades partida {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Partida entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPartida.validarModelo(entity);
			if (validadorPartida.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPartida.getErrores());
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			logger.error("Error validacion api guardando entidades partida {} - {} - {}", e.getMessage(), e.getErrores(),e);
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando entidades partida {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidades partida {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Partida entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPartida.validarModelo(entity);
			if (validadorPartida.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPartida.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(auth.getUserToken());
			repository.updateEntity(entity);
		} catch (ApiException e) {
			logger.error("Error api actualizando entidad partida {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidad partida {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		} catch (ApiException e) {
			logger.error("Error api eliminando entidad partida {} - {} - {}",id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidad partida {} -  {} - {}",id, e.getMessage(), e);
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
				rpta = getAllEntitys();
			
		}catch (ApiException e) {
			logger.error("Error api buscando entidad partida {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando entidad partida {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Partida getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obteniendo entidad partida {} - {} - {}",id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad partida {} -  {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Partida> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obteniendo all entidades partida {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo all entidades partida {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
