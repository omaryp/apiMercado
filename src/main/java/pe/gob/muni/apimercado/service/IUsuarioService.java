package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;

public interface IUsuarioService extends IBasicService<Usuario>{

	Usuario findByUsername(String username) throws Exception, ApiException;
	
	void disabledUserbyUsername(String username) throws Exception, ApiException;

	void actualizarUserByUsername(Usuario user) throws Exception, ValidatorException, ApiException;

	int getUserToken();

}
