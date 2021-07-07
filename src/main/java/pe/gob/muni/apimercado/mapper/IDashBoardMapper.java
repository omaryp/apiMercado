package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.dto.ComercianteMontoDto;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;
import pe.gob.muni.apimercado.utils.dto.Monto;

@Mapper
public interface IDashBoardMapper {

	List<ComercianteMontoDto> mayor_recaudacion(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	List<ComercianteMontoDto> top_10_deudores(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	List<Monto> totalCobrado(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	List<Monto> totalPendiente(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	long totalComerciantes(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	long totalPuestos(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	
}
