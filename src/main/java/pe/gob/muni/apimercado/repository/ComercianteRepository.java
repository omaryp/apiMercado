package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IComercianteMapper;
import pe.gob.muni.apimercado.model.Comerciante;

@Repository
public class ComercianteRepository extends BasicRepository<Comerciante,IComercianteMapper> {

}