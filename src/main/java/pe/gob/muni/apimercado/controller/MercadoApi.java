package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Mercado;
import pe.gob.muni.apimercado.service.IMercadoService;

@RestController
@RequestMapping("/mercado")
public class MercadoApi extends BasicController<Mercado, IMercadoService> {

}