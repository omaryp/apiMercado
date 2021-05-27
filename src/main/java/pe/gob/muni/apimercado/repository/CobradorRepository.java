package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ICobradorMapper;
import pe.gob.muni.apimercado.model.Cobrador;

@Repository
public class CobradorRepository extends BasicRepository<Cobrador,ICobradorMapper> {

}