package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Concepto;
import pe.gob.muni.apimercado.service.IConceptoService;

@RestController
@RequestMapping("/concepto")
public class ConceptoApi extends BasicController<Concepto, IConceptoService> {

}