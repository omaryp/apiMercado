package pe.gob.muni.apimercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.service.ITicketService;

@RestController
@RequestMapping("/ticket")
public class TicketApi extends BasicController<Ticket, ITicketService> {

}