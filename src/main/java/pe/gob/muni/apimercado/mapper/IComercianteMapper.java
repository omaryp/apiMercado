package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.model.dto.ComercianteDto;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Mapper
public interface IComercianteMapper extends IBasicMapper<Comerciante> {

	List<ComercianteDto> getDatosReporte(GeneralPageTable pagData) throws SQLException,SQLIntegrityConstraintViolationException;
	
}
