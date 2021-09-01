package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.dto.PuestoDto;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Mapper
public interface IPuestoMapper extends IBasicMapper<Puesto> {
	
	List<PuestoDto> getAllPuestosDtoMercado(int id) throws SQLException,SQLIntegrityConstraintViolationException; 
	
	List<PuestoDto> pagingDtoEntitys(GeneralPageTable params) throws SQLException,SQLIntegrityConstraintViolationException;
	
	int tieneTarifa(int id) throws SQLException,SQLIntegrityConstraintViolationException;
	
	int tieneSerie(int id) throws SQLException,SQLIntegrityConstraintViolationException;
}
