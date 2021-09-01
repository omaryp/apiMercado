package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IConceptoMapper;
import pe.gob.muni.apimercado.model.Concepto;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class ConceptoRepository extends BasicRepository<Concepto,IConceptoMapper> {
	
	public int verificarConcepto(int id)throws ApiException {
		try {
			return mapper.verificarConcepto(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
}