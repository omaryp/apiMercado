package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Constants.ACTIVO;
import static pe.gob.muni.apimercado.utils.Constants.NO_VISTADO;
import static pe.gob.muni.apimercado.utils.Constants.NO_PAGADO;
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
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.model.dto.TicketVisita;
import pe.gob.muni.apimercado.repository.TicketRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;
import pe.gob.muni.apimercado.utils.dto.PageTableTicket;
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
	private Validador<ProcesoTicket> validadorProceso;

	@Override
	public PageInfo<Ticket> pagingEntitys(Map<String, String> params) throws ApiException, Exception {
		logger.info("obteniendo tickets con los filtros {}.", objectToJson(params));
		try {
			List<Ticket> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
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
	public PageInfo<TicketDto> pagingTickets(Map<String, String> params) throws ApiException, Exception {
		logger.info("obteniendo tickets con los filtros {}.", objectToJson(params));
		try {
			List<TicketDto> rptaData = null;
			PageTableTicket pagData = mapToObject(params, PageTableTicket.class);
			
			PageHelper.startPage(pagData.getPage(), pagData.getLimit());

			rptaData = repository.pagingTickets(pagData);

			return new PageInfo<TicketDto>(rptaData);
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
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
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
		} catch (ValidatorException e) {
			logger.error("Hay Errores de validación - {} - {}", e.getMessage(), e);
			throw e;
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
			if(getTicketsFechaMercado(params.getMercados_id(), params.getFechaProceso()) == 0) {
				puestos = (params.getMercados_id() == 0) ? pueService.getAllEntitys() : pueService.getAllPuestosMercado(params.getMercados_id());
				puestos.forEach((puesto) -> {
					Ticket nvoTicket = new Ticket();
					nvoTicket.setComerciantes_id(puesto.getComerciantes_id());
					nvoTicket.setCorrelativo(puesto.getCorrelativo());
					nvoTicket.setCreado_por(auth.getUserToken());
					nvoTicket.setEliminado_por(0);
					nvoTicket.setEstado(ACTIVO);
					nvoTicket.setPagado(NO_PAGADO);
					nvoTicket.setFecha_creacion(new Date());
					nvoTicket.setFecha_ticket(params.getFechaProceso());
					nvoTicket.setFecha_modifcacion(null);
					nvoTicket.setMercados_id(puesto.getMercados_id());
					nvoTicket.setModifcado_por(0);
					nvoTicket.setEstado_visita(NO_VISTADO);
					nvoTicket.setObservaciones("");
					nvoTicket.setUbicaciones_id(puesto.getUbicaciones_id());
					nvoTicket.setPuestos_id(puesto.getPuestos_id());
					tickets.add(nvoTicket);
				});
				repository.saveAllTickets(tickets);
			}else throw new ValidatorException("Ya existen tickets para esta fecha");
			
		}catch (ValidatorException e) {
			logger.error("Error de validación al crear tickets - {} - {} ", e.getMessage(), e);
			throw e;
		}catch (ApiException e) {
			logger.error("Error api procesando ticket - {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error api procesando ticket - {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void marcarTicketPagado(int id,int estado_visita) throws ApiException, Exception {
		try {
			repository.marcarTicketPagado(id,estado_visita);
		} catch (ApiException e) {
			logger.error("Error api marcando ticket pagado - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidades ticket - {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void marcarEstadoVisitaTicket(TicketVisita ticket) throws ApiException, Exception {
		try {
			ticket.setFecha_obs(new Date());
			repository.marcarEstadoVisitaTicket(ticket);
		} catch (ApiException e) {
			logger.error("Error api marcando ticket no habido - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general marcando ticket no habido - {} - {}", e.getMessage(), e);
			throw e;
		}		
	}

	@Override
	public int getTicketsFechaMercado(int mercados_id, Date fecha_ticket) throws ApiException, Exception {
		try {
			return repository.getTicketsFechaMercado(mercados_id,fecha_ticket);
		} catch (ApiException e) {
			logger.error("Error api verificando ticket ya creados - {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general verificando ticket ya creados - {} - {}", e.getMessage(), e);
			throw e;
		}	
	}

}
