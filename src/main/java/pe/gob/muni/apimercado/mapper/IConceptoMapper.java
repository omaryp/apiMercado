package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Concepto;

@Mapper
public interface IConceptoMapper extends IBasicMapper<Concepto> {

	public int verificarConcepto(int id) throws SQLException,SQLIntegrityConstraintViolationException;

}
