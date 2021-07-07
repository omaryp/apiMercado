package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IDashBoardMapper;
import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;
import pe.gob.muni.apimercado.utils.dto.Monto;

@Repository
public class DashBoardRepository {
	@Autowired
	IDashBoardMapper mapper;
	
	public List<Comerciante> top_10_puntuales(GeneralPageTable pagData) throws ApiException {
		try {
			return mapper.top_10_deudores(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<Comerciante> top_10_deudores(GeneralPageTable pagData) throws ApiException {
		try {
			return mapper.top_10_deudores(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<Monto> totalCobrado(GeneralPageTable pagData) throws ApiException {
		try {
			return mapper.totalCobrado(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<Monto> totalPendiente(GeneralPageTable pagData) throws ApiException {
		try {
			return mapper.totalPendiente(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public long totalComerciantes(GeneralPageTable pagData)  throws ApiException {
		try {
			return mapper.totalComerciantes(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public long totalPuestos(GeneralPageTable pagData) throws ApiException {
		try {
			return mapper.totalPuestos(pagData);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
}