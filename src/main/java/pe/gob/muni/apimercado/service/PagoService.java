package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Constants.VISITADO;
import static pe.gob.muni.apimercado.utils.Constants.NO_VISTADO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.config.ParametrosApiRest;
import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.Serie;
import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.repository.PagoRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import static pe.gob.muni.apimercado.utils.Util.writeBytesToFileApache;

import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.EnvioDto;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;
import pe.gob.muni.apimercado.utils.dto.PageTablePago;

@Service
public class PagoService implements IPagoService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PagoRepository repository;
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private ISerieService ser;
	@Autowired
	private ITarifaService tar;
	@Autowired
	private ITicketService ticketSer;
	@Autowired
	private IReportService report;
	@Autowired
	private IEmailService email;
	@Autowired
	private ParametrosApiRest paramsApi;
	@Autowired
	private Validador<Pago> validadorPago;
	
	@Override
	public PageInfo<Pago> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo comerciantes con los filtros {}.",objectToJson(params));
		try {
			List<Pago> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Pago>(rptaData);
		} catch (ApiException e) {
			throw e;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void saveEntity(Pago entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPago.validarModelo(entity);
			if (validadorPago.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPago.getErrores());
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
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
	public void updateEntity(Pago entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPago.validarModelo(entity);
			if (validadorPago.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPago.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(auth.getUserToken());
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
	public Pago getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Pago> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void asociarTicketPago(List<TicketPago> tickets) throws ApiException, Exception {
		try {
			repository.asociarTicketPago(tickets);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public void pagoTickets(List<Ticket> tickets) throws ApiException, Exception {
		List<TicketPago> tiPags = new ArrayList<TicketPago>();
		try {
			for (Ticket ticket : tickets) {
				Serie serie = ser.getSeriePuesto(ticket.getPuestos_id());
				Tarifa tarifa = tar.getTarifaPuesto(ticket.getPuestos_id());
				
				Pago pag = new Pago();
				pag.setFecha_creacion(new Date());
				pag.setCreado_por(auth.getUserToken());
				pag.setSerie(serie.getCodigo());
				pag.setFecha_pago(new Date());
				pag.setCorrelativo(serie.getCorrelativo()+1);
				pag.setMonto_pagado(tarifa.getMonto());
				
				repository.saveEntity(pag);
				
				serie.setCorrelativo(pag.getCorrelativo());
				ser.updateEntity(serie);
				
				if(ticket.getFecha_ticket().equals(new Date()))
					ticketSer.marcarTicketPagado(ticket.getId(),VISITADO);
				else
					ticketSer.marcarTicketPagado(ticket.getId(), NO_VISTADO);
				
				TicketPago ticPag= new TicketPago();
				ticPag.setTickets_id(ticket.getId());
				ticPag.setPagos_id(pag.getId());
				
				tiPags.add(ticPag);
			}
			repository.asociarTicketPago(tiPags);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public PageInfo<PagoDto> pagingTickets(Map<String, String> params) throws ApiException, Exception {
		logger.info("obteniendo tickets con los filtros {}.", objectToJson(params));
		try {
			List<PagoDto> rptaData = null;
			PageTablePago pagData = mapToObject(params, PageTablePago.class);
			
			PageHelper.startPage(pagData.getPage(), pagData.getLimit());

			rptaData = repository.pagingPagos(pagData);

			return new PageInfo<PagoDto>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades ticket {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public byte[] reporteTicketPago(int id) throws ApiException, Exception {
		logger.info("generando reporte pago {} ticket", id);
		try {
			String titulo = "";
			Map<String, Object> params = new HashMap<String,Object>();
			PagoDto dto = getEntityPagoDto(id);
			dto.setDescripcion_concepto(dto.getDescripcion_concepto().toUpperCase());
			dto.setDescripcion_mercado(dto.getDescripcion_mercado().toUpperCase());
			titulo = dto.getSerie()+" "+dto.getCorrelativo();
			params.put("titulo", titulo);
			params.put("pago", dto);
			return report.generarReporte("pago", params);
		} catch (ApiException e) {
			logger.error("Error api generando reporte ticket pagado  {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general generando reporte ticket pagado {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public PagoDto getEntityPagoDto(int id) throws ApiException, Exception {
		try {
			return repository.getEntityPagoDto(id);
		} catch (ApiException e) {
			logger.error("Error api obteniendo detelle pago  {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo detelle pago{} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public PagoDto getEntityPagoDtoTicket(int id) throws ApiException, Exception {
		try {
			return repository.getEntityPagoDtoTicket(id);
		} catch (ApiException e) {
			logger.error("Error api obteniendo detalle pago por ticket {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo detalle pago por ticket{} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void enviarCorreo(EnvioDto entity) throws ApiException, Exception {
		logger.info("Enviando mensaje a {} pago {} ", entity.getCorreo(), entity.getId_pago());
		String rutaTemp = "";
		String asunto = "";
		String mensaje = "";
		byte [] adjunto = null;
		Map<String, Object> params = new HashMap<String,Object>();
		PagoDto  dto = null;
		try {
			dto = getEntityPagoDto(entity.getId_pago());
			dto.setDescripcion_concepto(dto.getDescripcion_concepto().toUpperCase());
			dto.setDescripcion_mercado(dto.getDescripcion_mercado().toUpperCase());
			asunto = paramsApi.getAsunto()+" : "+dto.getSerie()+" - "+dto.getCorrelativo();
			params.put("pago", dto);
			params.put("titulo", asunto);
			adjunto = report.generarReporte("pago", params);
			rutaTemp = "/temp/pago_"+dto.getSerie()+dto.getCorrelativo()+".pdf";
			mensaje = paramsApi.getMensaje();
			writeBytesToFileApache(rutaTemp, adjunto);
			email.enviarMensaje(entity.getCorreo(), asunto, mensaje, rutaTemp);
			logger.info("Se envio mensaje correctamente a {}", entity.getCorreo());
		} catch (Exception e) {
			logger.error("Error enviando mensaje a {} pago {} - {} - {} ", entity.getCorreo(), entity.getId_pago(),e.getMessage(),e);
			throw new ApiException("Error al enviar mensaje de correo a "+entity.getCorreo(), null);
		}
	}

}
