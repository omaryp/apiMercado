package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.PuestoComerciante;

@Mapper
public interface IPuestoComercianteMapper extends IBasicMapper<PuestoComerciante> {
	
	List<PuestoComerciante> getAllPuestosActive() throws SQLException,SQLIntegrityConstraintViolationException; 
	
	List<PuestoComerciante> getAllPuestosMercado(int id)  throws SQLException,SQLIntegrityConstraintViolationException; 
	
	PuestoComerciante getPuestoComercianteMercado(int comerciantes_id, int mercados_id) throws SQLException,SQLIntegrityConstraintViolationException; 

	void eliminarPuestoComerciante(int comerciante,int puesto) throws SQLException,SQLIntegrityConstraintViolationException;
}
