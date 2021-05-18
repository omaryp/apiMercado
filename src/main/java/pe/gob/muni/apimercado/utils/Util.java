package pe.gob.muni.apimercado.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.muni.apimercado.utils.dto.RespuestaApi;

import java.util.Map;

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
    
    
}
