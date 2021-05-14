package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Partida;
import pe.gob.muni.apimercado.service.IPartidaService;

@RestController
@RequestMapping("/partida")
public class PartidaApi extends BasicController<Partida, IPartidaService> {

}