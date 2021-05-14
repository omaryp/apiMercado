package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ISerieMapper;
import pe.gob.muni.apimercado.model.Serie;

@Repository
public class SerieRepository extends BasicRepository<Serie,ISerieMapper> {

}