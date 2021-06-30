package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IComercianteMapper;
import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.model.dto.ComercianteDto;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class ComercianteRepository extends BasicRepository<Comerciante,IComercianteMapper> {
	
	public List<ComercianteDto> getDatosReporte()  throws ApiException {
		try {
			return mapper.getDatosReporte();
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
}