package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.service.IUsuarioService;



@RestController
@RequestMapping("/usuario")
public class UsuarioApi extends BasicController<Usuario, IUsuarioService> {	

	
}