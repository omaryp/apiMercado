package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPuestoMapper;
import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.dto.PuestoDto;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Repository
public class PuestoRepository extends BasicRepository<Puesto,IPuestoMapper> {
	
	public List<PuestoDto> getAllPuestosDtoMercado(int idMercado)throws ApiException {
		try {
			return mapper.getAllPuestosDtoMercado(idMercado);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<PuestoDto> pagingDtoEntitys(GeneralPageTable params) throws ApiException{
		try {
			return mapper.pagingDtoEntitys(params);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public int tieneSerie(int idConcepto)throws ApiException {
		try {
			return mapper.tieneSerie(idConcepto);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public int tieneTarifa(int idConcepto) throws ApiException{
		try {
			return mapper.tieneTarifa(idConcepto);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
}