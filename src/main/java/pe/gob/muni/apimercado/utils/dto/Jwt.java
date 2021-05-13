package pe.gob.muni.apimercado.utils.dto;

import pe.gob.muni.apimercado.model.dto.UsuarioDto;

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