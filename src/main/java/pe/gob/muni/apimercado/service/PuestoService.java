package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_LIST;
import static pe.gob.muni.apimercado.utils.Constants.RESPONSE_OBJECT;
import static pe.gob.muni.apimercado.utils.Util.encodeFileToBase64Binary;
import static pe.gob.muni.apimercado.utils.Util.mapToObject;

import java.io.File;
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

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.model.dto.PuestoDto;
import pe.gob.muni.apimercado.repository.PuestoRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ResourceProject;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Service
public class PuestoService implements IPuestoService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private IPuestoComercianteService pcService;	
	@Autowired
	private IUsuarioService auth;
	@Autowired
	private ResourceProject resource;
	@Autowired
	private PuestoRepository repository;
	@Autowired
	private Validador<Puesto> validadorPuesto;
	@Autowired
	private Validador<PuestoComerciante> validadorPuestoC;
	@Autowired
	private IReportService report;
	
	@Override
	public PageInfo<Puesto> pagingEntitys(Map<String, String> params)
			throws ApiException, Exception {
		logger.info("paginando puestos con parámetros {}.",params);
		try {
			List<Puesto> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingEntitys(pagData);
				
			return new PageInfo<Puesto>(rptaData);
		} catch (ApiException e) {
			logger.error("Error api paginando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general paginando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void saveEntity(Puesto entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPuesto.validarModelo(entity);
			if (validadorPuesto.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPuesto.getErrores());
			if(repository.tieneSerie(entity.getConceptos_id()) == 0)
				throw new ValidatorException("El concepto seleccionado no tiene una serie asignada.");
			if(repository.tieneTarifa(entity.getConceptos_id()) == 0)
				throw new ValidatorException("El concepto seleccionado no tiene una tarifa asignada.");
			entity.setFecha_creacion(new Date());
			entity.setCreado_por(auth.getUserToken());
			repository.saveEntity(entity);
		}catch (ValidatorException e) {
			logger.error("Error api validacion guardando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (ApiException e) {
			logger.error("Error api guardando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general guardando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void updateEntity(Puesto entity) throws ApiException, Exception, ValidatorException {
		try {
			validadorPuesto.validarModelo(entity);
			if (validadorPuesto.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPuesto.getErrores());
			entity.setFecha_modifcacion(new Date());
			entity.setModifcado_por(auth.getUserToken());
			repository.updateEntity(entity);
		} catch (ApiException e) {
			logger.error("Error api actualizando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general actualizando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		try {
			repository.deleteEntity(id);
		}catch (ApiException e) {
			logger.error("Error api eliminando entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general eliminando entidad puesto {} - {}", e.getMessage(), e);
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
			logger.error("Error api buscando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general buscando entidades puesto {} - {}", e.getMessage(), e);
			throw e;
		}
		return rpta;
	}
	


	@Override
	public Puesto getEntity(int id) throws ApiException, Exception {
		try {
			return repository.getEntity(id);
		}catch (ApiException e) {
			logger.error("Error api obtiendo entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void eliminarPuestoComerciante(int comerciante,int puesto) throws ApiException, Exception {
		try {
			pcService.eliminarPuestoComerciante(comerciante,puesto);
		}catch (ApiException e) {
			logger.error("Error api obtiendo entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo entidad puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}


	@Override
	public List<Puesto> getAllEntitys() throws ApiException, Exception {
		try {
			return repository.getAllEntitys();
		} catch (ApiException e) {
			logger.error("Error api obtiendo all entitys puesto {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo all entitys puesto {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void asociarPuestoComerciante(PuestoComerciante puestoC) throws ValidatorException,ApiException,Exception {
		try {
			validadorPuestoC.validarModelo(puestoC);
			if (validadorPuestoC.isHayErrores())
				throw new ValidatorException("Hay Errores de validación", validadorPuesto.getErrores());
			puestoC.setFecha_creacion(new Date());
			puestoC.setCreado_por(auth.getUserToken());
			pcService.saveEntity(puestoC);
		}catch (ValidatorException e) {
			logger.error("Error api validando puesto comerciante {} - {}", e.getMessage(), e);
			throw e;
		}catch (ApiException e) {
			logger.error("Error api asociando puesto comerciante {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general asociando puesto comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public byte[] reportePuestos(Map<String, String> datos) throws ApiException,Exception {
		logger.info("generando reporte puestos");
		try {
			String titulo = "Reporte de Puestos";
			String mercado = "";
			File f = resource.getResource("static/logo_1.png");
            String encodstring = encodeFileToBase64Binary(f);
			Map<String, Object> params = new HashMap<String,Object>();
			List<PuestoDto> puestos = datosReortePuestos(datos);
			mercado = (datos.isEmpty())?"":puestos.get(0).getDescripcion_mercado();
			params.put("titulo", titulo);
			params.put("datos", puestos);
			params.put("imagen", encodstring);
			params.put("fecha_reporte", new Date());
			params.put("mercado", mercado);
			return report.generarReporte("reportePuestos", params);
		} catch (ApiException e) {
			logger.error("Error api generando reporte de puestos {} - {}", e.getMessage(), e);
			throw new ApiException("Error generando reporte de puestos",null);
		} catch (Exception e) {
			logger.error("Error general generando reporte de puestos {} - {}", e.getMessage(), e);
			throw new ApiException("Error generando reporte de puestos ",null);
		}
	}
	
	public List<PuestoDto> datosReortePuestos(Map<String, String> datos) throws ApiException,Exception {
		try {
			List<PuestoDto> rptaData = null;
			GeneralPageTable pagData = mapToObject(datos, GeneralPageTable.class);
			rptaData = repository.pagingDtoEntitys(pagData);
			return rptaData;
		}catch (ApiException e) {
			logger.error("Error api obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public PageInfo<PuestoDto> pagingDtoEntitys(Map<String, String> params) throws ApiException,Exception {
		try {
			List<PuestoDto> rptaData = null;
			GeneralPageTable pagData = mapToObject(params, GeneralPageTable.class);
			PageHelper.startPage(pagData.getPage(),pagData.getLimit());
			
			rptaData = repository.pagingDtoEntitys(pagData);
				
			return new PageInfo<PuestoDto>(rptaData);
		}catch (ApiException e) {
			logger.error("Error api obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<PuestoDto> getAllPuestosDtoMercado(int idMercado) throws ApiException {
		try {
			return repository.getAllPuestosDtoMercado(idMercado);
		}catch (ApiException e) {
			logger.error("Error api obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obetener puestos por mercado {} - {}", e.getMessage(), e);
			throw e;
		}
	}

}
