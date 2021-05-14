package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.service.ITarifaService;

@RestController
@RequestMapping("/tarifa")
public class TarifaApi extends BasicController<Tarifa, ITarifaService> {

}