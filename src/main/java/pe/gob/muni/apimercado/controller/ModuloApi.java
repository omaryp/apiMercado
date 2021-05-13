package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Modulo;
import pe.gob.muni.apimercado.service.IModuloService;

@RestController
@RequestMapping("/modulo")
public class ModuloApi extends BasicController<Modulo, IModuloService> {

}