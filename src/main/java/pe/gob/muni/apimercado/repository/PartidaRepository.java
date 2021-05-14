package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPartidaMapper;
import pe.gob.muni.apimercado.model.Partida;

@Repository
public class PartidaRepository extends BasicRepository<Partida,IPartidaMapper> {

}