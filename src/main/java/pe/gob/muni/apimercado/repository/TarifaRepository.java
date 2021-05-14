package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ITarifaMapper;
import pe.gob.muni.apimercado.model.Tarifa;

@Repository
public class TarifaRepository extends BasicRepository<Tarifa,ITarifaMapper> {

}