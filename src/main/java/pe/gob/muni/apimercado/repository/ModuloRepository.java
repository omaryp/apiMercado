package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IModuloMapper;
import pe.gob.muni.apimercado.model.Modulo;
import pe.gob.muni.apimercado.utils.ApiException;

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