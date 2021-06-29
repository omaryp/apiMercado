package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.model.Persona;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.repository.ComercianteRepository;
import pe.gob.muni.apimercado.repository.PersonaRepository;
import pe.gob.muni.apimercado.repository.TicketRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ResourceProject;

import static pe.gob.muni.apimercado.utils.Util.encodeFileToBase64Binary;
import static pe.gob.muni.apimercado.utils.Util.getPersona;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;
import pe.gob.muni.apimercado.utils.dto.PageTableTicket;

@Service
public class ComercianteService implements IComercianteService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ComercianteRepository repository;
	@Autowired
	private PersonaRepository perRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private IReportService report;
	@Autowired
	private Validador<Comerciante> validadorComerciante;
	@Autowired
	private ResourceProject resource;
	
	@Override
	public PageInfo<Comerciante> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("obteniendo comerciantes con los filtros {}.",objectToJson(params));
		try {
			List<Comerciante> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData =repository.pagingEntitys(pagData);
			PageInfo<Comerciante> rpta  = new PageInfo<Comerciante>(rptaData);
			rpta.setList(procesarLista(rpta.getList()));
			return rpta;
		}catch (ApiException e) {
			logger.error("Error api paginando entidades comerciante {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	private List<Comerciante> procesarLista(List<Comerciante> datos){
		List<Comerciante> rpta = new ArrayList<Comerciante>();
		datos.forEach((entity) -> {
			entity.setId(entity.getPersonas_id());
			rpta.add(entity);
		});
		return rpta;
	}

	@Override
	public void saveEntity(Comerciante entity) throws ApiException, Exception, ValidatorException {
		Persona padre = null;
		try {
			validadorComerciante.validarModelo(entity);
			if (validadorComerciante.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorComerciante.getErrores());
		
			entity.setCreado_por(auth.getUserToken());
			entity.setFecha_creacion(new Date());
			padre = getPersona(entity);
			perRepository.saveEntity(padre);
			
			entity.setPersonas_id(padre.getId());
			repository.saveEntity(entity);
			
		}catch (ValidatorException e) {
			throw e;
		}catch (ApiException e) {
			logger.error("Error api guardando entidad comerciante {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general guardando entidad comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Comerciante entity) throws ApiException, Exception, ValidatorException {
		Persona padre = null;
		try {
			validadorComerciante.validarModelo(entity);
			if (validadorComerciante.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorComerciante.getErrores());
			
			entity.setModifcado_por(auth.getUserToken());
			entity.setFecha_modifcacion(new Date());
			entity.setEstado(1);
			padre = getPersona(entity);
			perRepository.updateEntity(padre);
			
			entity.setPersonas_id(padre.getId());
			repository.updateEntity(entity);
			
		}catch (ApiException e) {
			logger.error("Error api actualizando entidad comerciante {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general actualizando entidad comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			Comerciante tmp = getEntity(id);
			perRepository.deleteEntity(tmp.getPersonas_id());
		}catch (ApiException e) {
			logger.error("Error api eliminando entidad comerciante {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general eliminando entidad comerciante {} - {} - {}",id, e.getMessage(), e);
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
				rpta = procesarLista(getAllEntitys());
			
		}catch (ApiException e) {
			logger.error("Error api buscando entidad comerciante {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general buscando entidad comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}

	@Override
	public Comerciante getEntity(int id) throws ApiException, Exception {
		try {
			Comerciante entity = repository.getEntity(id);
			entity.setId(entity.getPersonas_id());
			return entity;
		}catch (ApiException e) {
			logger.error("Error api obteniendo entidad comerciante {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo entidad comerciante {} - {} - {}",id, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Comerciante> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		}catch (ApiException e) {
			logger.error("Error api obteniendo all entitys comerciante {} - {}", e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("Error general obteniendo all entitys comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public byte[] reporteAsistencia(Map<String, String> params) throws ApiException, Exception {
		logger.info("generando reporte de asistencia por comerciante - {}", objectToJson(params));
		try {
			String titulo = "Reporte Asistencia Comerciantes";
			Map<String, Object> paramReport= new HashMap<String,Object>();
			PageTableTicket queryParams = new PageTableTicket();
			queryParams = mapToObject(params, PageTableTicket.class);
			File f = resource.getResource("static/logo_1.png");
            String encodstring = encodeFileToBase64Binary(f);
			List<TicketDto> datos = ticketRepository.pagingTickets(queryParams);
			
			final Map<String, List<TicketDto>> rolModulo = datos.stream().collect(Collectors.groupingBy(TicketDto::getKeyOrder));
			
			paramReport.put("titulo", titulo);
			paramReport.put("datos", rolModulo);
			paramReport.put("fecha_reporte", new Date());
			paramReport.put("imagen", encodstring);
			return report.generarReporte("reporteAsistencia", paramReport);
			
		} catch (ApiException e) {
			logger.error("Error api generando reporte ticket pagado  {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general generando reporte ticket pagado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
