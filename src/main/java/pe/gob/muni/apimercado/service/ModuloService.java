package pe.demo.apirest.service;

import static pe.demo.apirest.utils.Constants.RESPONSE_LIST;
import static pe.demo.apirest.utils.Constants.RESPONSE_OBJECT;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.demo.apirest.model.Modulo;
import pe.demo.apirest.model.RptaDataModel;
import pe.demo.apirest.repository.ModuloRepository;
import pe.demo.apirest.utils.ApiException;
import pe.demo.apirest.utils.ValidatorException;

@Service
public class ModuloService implements IModuloService {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ModuloRepository moduloRepository;

	@Override
	public List<Modulo> getModulosByUsuario(String usuario) throws ApiException,Exception{
		return null;
	}

	@Override
	public Modulo getEntity(int id) throws ApiException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modulo> getAllEntitys() throws ApiException, Exception {
		return null;
	}

	@Override
	public RptaDataModel<Modulo> pagingEntitys(String valorBusqueda, int tipoBusqueda, int inicio, int fin)
			throws ApiException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEntity(Modulo entity) throws ApiException, Exception, ValidatorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEntity(Modulo entity) throws ApiException, Exception, ValidatorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEntity(int id) throws ApiException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object searchEntity(Map<String, String> params) throws ApiException, Exception {
		Object rpta = null;
		String usuario = "";
		try {
			if(!params.isEmpty()) {
				int tipo = Integer.parseInt(params.get("tipo"));
				switch (tipo) {
					case RESPONSE_LIST:
						usuario = params.get("user");
						logger.info("Obteniendo lista modulos por usuario {} ",usuario);
						rpta = moduloRepository.getModulosByUsuario(usuario);
						break;
					case RESPONSE_OBJECT:
						//colocar aqui la funcionalidad de ser necesario
				}
			}else
				rpta = moduloRepository.getAllEntitys();
		
		}catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return rpta;
	}
}
