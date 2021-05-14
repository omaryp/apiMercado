package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.service.IComercianteService;

@RestController
@RequestMapping("/comerciante")
public class ComercianteApi extends BasicController<Comerciante, IComercianteService> {

}