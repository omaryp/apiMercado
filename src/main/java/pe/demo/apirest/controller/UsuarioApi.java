package pe.demo.apirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import pe.demo.apirest.model.Usuario;
import pe.demo.apirest.service.IUsuarioService;



@RestController
@RequestMapping("/usuario")
public class UsuarioApi extends BasicController<Usuario, IUsuarioService> {	

	
}