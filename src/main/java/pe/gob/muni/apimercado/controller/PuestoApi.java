package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.service.IPuestoService;

@RestController
@RequestMapping("/puesto")
public class PuestoApi extends BasicController<Puesto, IPuestoService> {

}