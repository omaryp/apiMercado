package pe.demo.apirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.demo.apirest.model.Modulo;
import pe.demo.apirest.service.IModuloService;

@RestController
@RequestMapping("/modulo")
public class ModuloApi extends BasicController<Modulo, IModuloService> {

}