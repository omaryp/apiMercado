package pe.gob.muni.apimercado.config.security;

import static pe.gob.muni.apimercado.utils.Constants.HEADER_AUTHORIZACION_KEY;
import static pe.gob.muni.apimercado.utils.Constants.ISSUER_INFO;
import static pe.gob.muni.apimercado.utils.Constants.SUPER_SECRET_KEY;
import static pe.gob.muni.apimercado.utils.Constants.TOKEN_BEARER_PREFIX;
import static pe.gob.muni.apimercado.utils.Constants.TOKEN_EXPIRATION_TIME;

import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.UsuarioDto;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.dto.Jwt;
import pe.gob.muni.apimercado.utils.dto.RespuestaApi;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	private Usuario credenciales;
	private Authentication auth;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			credenciales= new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciales.getUsuario(), credenciales.getPassword(), new ArrayList<>()));
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication auth) throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		try {
			Jwt jwt = new Jwt();
			String token;
			UsuarioDto usuario;
			usuario = (UsuarioDto)auth.getPrincipal();
			
			token = Jwts.builder()
					.setHeaderParam("typ", "JWT")
					.setIssuedAt(new Date())
					.setIssuer(ISSUER_INFO)
					.setSubject(usuario.getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
			response.setContentType("application/json");
			response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
			
			jwt.setUser(usuario);
			jwt.setToken(token);
			
			if(auth.getAuthorities().isEmpty()) {
				RespuestaApi<String> rpta = new RespuestaApi<String>();
				rpta.setCodigo(ERROR_AL_PROCESAR_PETICION);
				rpta.setMensaje("Usuario no autorizado, no tiene roles.");
				rpta.setContenido("");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				writer.println(Util.objectToJson(rpta));
			}			
			RespuestaApi<Jwt> rpta = new RespuestaApi<Jwt>();
			rpta.setCodigo(TRANSACCION_OK);
			rpta.setMensaje("Transacción OK");
			rpta.setContenido(jwt);
			response.setStatus(HttpStatus.OK.value());
			writer.println(Util.objectToJson(rpta));
			
		} catch (Exception e) {
			RespuestaApi<String> rpta = new RespuestaApi<String>();
			rpta.setCodigo(ERROR_INTERNO);
			rpta.setMensaje("Ocurrió un error interno en la aplicación");
			rpta.setContenido("");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			writer.println(Util.objectToJson(rpta));
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		RespuestaApi<String> rpta = new RespuestaApi<String>();
		rpta.setCodigo(ERROR_AL_PROCESAR_PETICION);
		rpta.setMensaje("Usuario no autorizado, no existe usuario.");
		rpta.setContenido("");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		writer.println(Util.objectToJson(rpta));
		
	}

}
