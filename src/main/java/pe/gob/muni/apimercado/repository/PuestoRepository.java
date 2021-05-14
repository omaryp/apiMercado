package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPuestoMapper;
import pe.gob.muni.apimercado.model.Puesto;

@Repository
public class PuestoRepository extends BasicRepository<Puesto,IPuestoMapper> {

}