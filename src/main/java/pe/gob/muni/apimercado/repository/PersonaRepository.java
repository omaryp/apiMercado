package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPersonaMapper;
import pe.gob.muni.apimercado.model.Persona;

@Repository
public class PersonaRepository extends BasicRepository<Persona,IPersonaMapper> {

}