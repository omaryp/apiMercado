package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.service.IRolService;

@RestController
@RequestMapping("/rol")
public class RolApi extends BasicController<Rol, IRolService> {
	
}