package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Constants.VISITADO;
import static pe.gob.muni.apimercado.utils.Constants.NO_VISTADO;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.config.ParametrosApiRest;
import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.Serie;
import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.repository.PagoRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ResourceProject;

import static pe.gob.muni.apimercado.utils.Util.encodeFileToBase64Binary;
import static pe.gob.muni.apimercado.utils.Util.isDateEquals;
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
	private IComercianteService com;
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
	private ResourceProject resource;
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
								
				if(isDateEquals(ticket.getFecha_ticket(), new Date()))
					ticketSer.marcarTicketPagado(ticket.getId(),VISITADO);
				else
					ticketSer.marcarTicketPagado(ticket.getId(), NO_VISTADO);
				
				TicketPago ticPag= new TicketPago();
				ticPag.setTickets_id(ticket.getId());
				ticPag.setPagos_id(pag.getId());
				
				tiPags.add(ticPag);
				
				//enviamos comprobante al correo registrado
				Comerciante comerciante = com.getEntity(ticket.getComerciantes_id());
				EnvioDto envio = new EnvioDto();
				envio.setCorreo(comerciante.getCorreo());
				envio.setId_pago(pag.getId());
				enviarCorreo(envio);
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
			File f = resource.getResource("static/logo_mobile.png");
            String encodstring = encodeFileToBase64Binary(f);
			Map<String, Object> params = new HashMap<String,Object>();
			PagoDto dto = getEntityPagoDto(id);
			dto.setDescripcion_concepto(dto.getDescripcion_concepto().toUpperCase());
			dto.setDescripcion_mercado(dto.getDescripcion_mercado().toUpperCase());
			titulo = dto.getSerie()+" "+dto.getCorrelativo();
			params.put("titulo", titulo);
			params.put("pago", dto);
			params.put("imagen", encodstring);
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
	@Async
	public void enviarCorreo(EnvioDto entity) throws ApiException, Exception {
		logger.info("Enviando mensaje a {} pago {} ", entity.getCorreo(), entity.getId_pago());
		String rutaTemp = "";
		String nombre = "";
		String asunto = "";
		String mensaje = "";
		byte [] adjunto = null;
		try {
			if(!entity.getCorreo().equals("") && !entity.getCorreo().equals(null)) {
				nombre = "comprobante_pago.pdf";
				asunto = paramsApi.getAsunto()+" : "+nombre;
				adjunto = reporteTicketPago(entity.getId_pago());
				rutaTemp = "/temp/pago_"+nombre;
				mensaje = paramsApi.getMensaje();
				writeBytesToFileApache(rutaTemp, adjunto);
				email.enviarMensaje(entity.getCorreo(), asunto, mensaje, rutaTemp,nombre);
				logger.info("Se envio mensaje correctamente a {}", entity.getCorreo());
			}else
				logger.info("No se envió comprobante a {} del pago {}", entity.getCorreo(),entity.getCorreo());
			
		} catch (Exception e) {
			logger.error("Error enviando mensaje a {} pago {} - {} - {} ", entity.getCorreo(), entity.getId_pago(),e.getMessage(),e);
			throw new ApiException("Error al enviar mensaje de correo a "+entity.getCorreo(), null);
		}
	}

}
