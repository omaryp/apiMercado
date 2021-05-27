package pe.gob.muni.apimercado.repository;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ITicketMapper;
import pe.gob.muni.apimercado.model.Ticket;

@Repository
public class TicketRepository extends BasicRepository<Ticket,ITicketMapper> {

}