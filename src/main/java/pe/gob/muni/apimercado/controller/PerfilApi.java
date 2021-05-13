package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.service.IPerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilApi extends BasicController<Perfil, IPerfilService> {
		
}