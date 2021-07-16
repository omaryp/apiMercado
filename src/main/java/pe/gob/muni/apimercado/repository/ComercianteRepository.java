package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IComercianteMapper;
import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.model.dto.ComercianteDto;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Repository
public class ComercianteRepository extends BasicRepository<Comerciante,IComercianteMapper> {
	
	public List<ComercianteDto> getDatosReporte(GeneralPageTable pagData)  throws ApiException {
		try {
			return mapper.getDatosReporte(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public Comerciante getComercianteDni(String dni)  throws ApiException {
		try {
			return mapper.getComercianteDni(dni);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
}