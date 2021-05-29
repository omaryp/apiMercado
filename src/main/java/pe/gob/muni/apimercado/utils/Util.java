package pe.gob.muni.apimercado.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.muni.apimercado.model.Persona;
import pe.gob.muni.apimercado.utils.dto.RespuestaApi;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Omar Yarleque
 */
public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    
    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
        	logger.error(ex.toString());
        }
        return json;
    }

    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            obj = mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
        	logger.error(e.toString());
        }
        return obj;
    }
    
    public static <T> T mapToObject(Map<String, String> aMap, Class<T> t) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.convertValue(aMap, mapper.getTypeFactory().constructType(t));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static <T> ResponseEntity<RespuestaApi<T>> respuestaApi(T contenido,String mensaje,int codigo,HttpStatus status) {
    	RespuestaApi<T> response = new RespuestaApi<T>();
		response.setCodigo(codigo);
		response.setMensaje(mensaje);
        response.setContenido(contenido);
		return new ResponseEntity<RespuestaApi<T>>(response,status);
    }
    
    public static <T> void respuestaApi(T contenido,String mensaje,int codigo,HttpStatus status,HttpServletResponse response) {
    	try {
    		PrintWriter writer = response.getWriter();
    		response.setContentType("application/json");
            RespuestaApi<T> rpta = new RespuestaApi<T>();
    		rpta.setCodigo(codigo);
    		rpta.setMensaje(mensaje);
    		rpta.setContenido(contenido);
    		response.setStatus(status.value());
    		writer.println(objectToJson(rpta));
		} catch (Exception e) {
			logger.error("Error al enviar rpta autenticación {} - {}",e.getMessage(),e);
		}
    }
    
    public static <T extends Persona> Persona getPersona(T entity) {
    	Persona persona = new Persona();
		persona.setApellidos(entity.getApellidos());
		persona.setCorreo(entity.getCorreo());
		persona.setCreado_por(entity.getCreado_por());
		persona.setDireccion(entity.getDireccion());
		persona.setDni(entity.getDni());
		persona.setEliminado_por(entity.getEliminado_por());
		persona.setEstado(entity.getEstado());
		persona.setFecha_creacion(entity.getFecha_creacion());
		persona.setFecha_modificacion(entity.getFecha_modifcacion());
		persona.setFecha_nacimiento(entity.getFecha_nacimiento());
		persona.setModificado_por(entity.getModificado_por());
		persona.setNombres(entity.getNombres());
		persona.setTelefono(entity.getTelefono());
		return persona;
    }
    
}
