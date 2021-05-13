package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Rol;

@Mapper
public interface IRolMapper extends IBasicMapper<Rol> {
	
	List<Rol> getRolesByPerfil(int perfil) throws SQLException;
	
	List<Rol> getRolesByUsuario(String usuario ) throws SQLException;

}
