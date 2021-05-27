package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Cobrador;
import pe.gob.muni.apimercado.service.ICobradorService;

@RestController
@RequestMapping("/cobrador")
public class CobradorApi extends BasicController<Cobrador, ICobradorService> {

}