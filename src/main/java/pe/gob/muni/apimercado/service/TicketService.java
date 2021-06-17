package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.repository.TicketRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTable;
import pe.gob.muni.apimercado.utils.dto.ProcesoTicket;

@Service
public class TicketService implements ITicketService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private TicketRepository repository;

	@Autowired
	private IPuestoComercianteService pueService;
	
	@Autowired
	private IUsuarioService auth;

	@Autowired
	private Validador<Ticket> validadorTicket;

	@Autowired
	private Validador<ProcesoTicket> validadorProceso;

	@Override
	public PageInfo<Ticket> pagingEntitys(Map<String, String> params) throws ApiException, Exception {
		logger.info("obteniendo tickets con los filtros {}.", objectToJson(params));
		try {
			List<Ticket> rptaData = null;
			PageTable pagData = mapToObject(params, PageTable.class);
			PageHelper.startPage(pagData.getPage(), pagData.getLimit());

			rptaData = repository.pagingEntitys(pagData);

			return new PageInfo<Ticket>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Ticket entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorTicket.validarModelo(entity);
			if (validadorTicket.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorTicket.getErrores());
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
		} catch (ValidatorException e) {
			logger.error("Error api validando entidad ticket {} - {}", e.getMessage(), e.getErrores());
			throw e;
		} catch (ApiException e) {
			logger.error("Error api guardando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Ticket entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorTicket.validarModelo(entity);
			if (validadorTicket.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorTicket.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(auth.getUserToken());
			repository.updateEntity(entity);
		} catch (ApiException e) {
			logger.error("Error api actualizando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		} catch (ApiException e) {
			logger.error("Error api eliminando entidades ticket {} - {} - {}", id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidades ticket {} - {} - {}", id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		Object rpta = null;
		int codigo = 0;
		try {
			if (!params.isEmpty()) {
				int tipo = Integer.parseInt(params.get("tipo"));
				switch (tipo) {
				case RESPONSE_LIST:
					// colocar aqui funcionalidad, si es necesaria
					break;
				case RESPONSE_OBJECT:
					codigo = Integer.parseInt(params.get("codigo"));
					rpta = repository.getEntity(codigo);
					break;
				}
			} else
				rpta = repository.getAllEntitys();

		} catch (ApiException e) {
			logger.error("Error api buscando entidad ticket - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando entidad ticket - {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Ticket getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		} catch (ApiException e) {
			logger.error("Error api obteniendo entidad ticket {} - {} - {}", id, e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad ticket {} - {} - {}", id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Ticket> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obteniendo entidades ticket - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidades ticket - {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void generarTickets(ProcesoTicket params) throws ApiException, Exception {
		try {
			validadorProceso.validarModelo(params);
			if (validadorProceso.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorProceso.getErrores());
			procesarTickets(params);
		} catch (ApiException e) {
			logger.error("Error api generando ticket - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error api generando ticket - {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	public void procesarTickets(ProcesoTicket params) throws ApiException, Exception {
		List<PuestoComerciante> puestos;
		final List<Ticket> tickets = new ArrayList<Ticket>();	
		try {
			puestos = (params.getMercados_id() == 0) ? pueService.getAllEntitys() : pueService.getAllPuestosMercado(params.getMercados_id());
			puestos.forEach((puesto) -> {
				Ticket nvoTicket = new Ticket();
				nvoTicket.setComerciantes_id(puesto.getComerciantes_id());
				nvoTicket.setCorrelativo(puesto.getCorrelativo());
				nvoTicket.setCreado_por(auth.getUserToken());
				nvoTicket.setEliminado_por(0);
				nvoTicket.setEstado(1);
				nvoTicket.setFecha_creacion(params.getFechaProceso());
				nvoTicket.setFecha_modifcacion(null);
				nvoTicket.setMercados_id(puesto.getMercados_id());
				nvoTicket.setModifcado_por(0);
				nvoTicket.setNo_habido(false);
				nvoTicket.setObservaciones("");
				nvoTicket.setPuestos_id(puesto.getPuestos_id());
				tickets.add(nvoTicket);
			});
			repository.saveAllTickets(tickets);
		} catch (ApiException e) {
			logger.error("Error api procesando ticket - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error api procesando ticket - {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
