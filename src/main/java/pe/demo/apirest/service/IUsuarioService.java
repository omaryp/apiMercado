package pe.demo.apirest.service;

import pe.demo.apirest.model.Usuario;
import pe.demo.apirest.utils.ApiException;
import pe.demo.apirest.utils.ValidatorException;

public interface IUsuarioService extends IBasicService<Usuario>{

	Usuario findByUsername(String username) throws Exception, ApiException;
	
	void disabledUserbyUsername(String username) throws Exception, ApiException;

	void actualizarUserByUsername(Usuario user) throws Exception, ValidatorException, ApiException;

}
