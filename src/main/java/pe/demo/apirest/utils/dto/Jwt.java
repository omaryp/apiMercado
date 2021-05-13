package pe.demo.apirest.utils.dto;

import pe.demo.apirest.model.dto.UsuarioDto;

public class Jwt {

    private String token;
    private UsuarioDto user;
   
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public UsuarioDto getUser() {
		return user;
	}

	public void setUser(UsuarioDto user) {
		this.user = user;
	}


}