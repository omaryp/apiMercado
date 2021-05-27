package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IConceptoMapper;
import pe.gob.muni.apimercado.model.Concepto;

@Repository
public class ConceptoRepository extends BasicRepository<Concepto,IConceptoMapper> {

}