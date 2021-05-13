package pe.demo.apirest.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.demo.apirest.mapper.IModuloMapper;
import pe.demo.apirest.model.Modulo;
import pe.demo.apirest.utils.ApiException;

@Repository
public class ModuloRepository extends BasicRepository<Modulo,IModuloMapper> {

	public List<Modulo> getModulosByUsuario(String usuario) throws ApiException {
		try {
			return mapper.getModulosByUsuario(usuario);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public List<Modulo> getAllModulos() throws ApiException {
		try {
			return mapper.getAllModulos();
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
}