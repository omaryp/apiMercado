package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IMercadoMapper;
import pe.gob.muni.apimercado.model.Mercado;

@Repository
public class MercadoRepository extends BasicRepository<Mercado,IMercadoMapper> {

}