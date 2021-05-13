package pe.demo.apirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pe.demo.apirest.model.Perfil;
import pe.demo.apirest.service.IPerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilApi extends BasicController<Perfil, IPerfilService> {
		
}