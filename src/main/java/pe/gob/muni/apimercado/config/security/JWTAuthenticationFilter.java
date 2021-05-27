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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.UsuarioDto;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;
import pe.gob.muni.apimercado.utils.dto.Jwt;

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
		} catch (Exception e) {
			throw new RuntimeException("Error general al autenticar usuario : "+e.getMessage(),e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication auth) throws IOException, ServletException {
		try {
			List<GrantedAuthority> grantedAuthorities;
			Jwt jwt = new Jwt();
			String token;
			UsuarioDto user;
			if(auth.getAuthorities().isEmpty()) {
				respuestaApi(null, "Usuario no autorizado, no tiene roles.", ERROR_AL_PROCESAR_PETICION, HttpStatus.UNAUTHORIZED, response);
			}else {
				user = (UsuarioDto)auth.getPrincipal();
				
				grantedAuthorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
				
				token = Jwts.builder()
						.setHeaderParam("typ", "JWT")
						.setIssuer(ISSUER_INFO)
						.setSubject(user.getUsername())
						.claim("authorities",
								grantedAuthorities.stream()
										.map(GrantedAuthority::getAuthority)
										.collect(Collectors.toList()))
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY.getBytes()).compact();
				
				response.setContentType("application/json");
				response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
			
				jwt.setUser(user);
				jwt.setToken(token);
			}	
			respuestaApi(jwt,"Transacción OK", TRANSACCION_OK, HttpStatus.OK, response);
		} catch (Exception e) {
			respuestaApi(null,"Ocurrió un error interno en la aplicación", ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR, response);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub		
		respuestaApi(null,"Usuario no autorizado, "+failed.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.UNAUTHORIZED, response);
	
	}

}
