package pe.demo.apirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import pe.demo.apirest.model.Rol;
import pe.demo.apirest.service.IRolService;

@RestController
@RequestMapping("/rol")
public class RolApi extends BasicController<Rol, IRolService> {
	
}