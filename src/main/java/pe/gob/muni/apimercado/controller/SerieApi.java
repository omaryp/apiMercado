package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Serie;
import pe.gob.muni.apimercado.service.ISerieService;

@RestController
@RequestMapping("/serie")
public class SerieApi extends BasicController<Serie, ISerieService> {

}