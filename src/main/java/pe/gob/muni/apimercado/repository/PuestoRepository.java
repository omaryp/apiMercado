package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPuestoMapper;
import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.dto.PuestoDto;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.PageTable;

@Repository
public class PuestoRepository extends BasicRepository<Puesto,IPuestoMapper> {
	
	public List<PuestoDto> getAllPuestosMercado(int idMercado)throws ApiException {
		try {
			return mapper.getAllPuestosMercado(idMercado);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<PuestoDto> pagingDtoEntitys(PageTable params) throws ApiException{
		try {
			return mapper.pagingDtoEntitys(params);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
}