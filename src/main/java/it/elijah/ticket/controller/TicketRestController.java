package it.elijah.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.elijah.ticket.projections.TicketApi;
import it.elijah.ticket.response.Payload;
import it.elijah.ticket.service.TicketService;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket")
public class TicketRestController {
  
  @Autowired
  TicketService ticketService;

  @GetMapping()
  public ResponseEntity<Payload<List<TicketApi>>> getAllTicket() {
    return new ResponseEntity<>(new Payload<>(null, ticketService.getTicket(), HttpStatus.OK), HttpStatus.OK);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<Payload<List<TicketApi>>> getAllTicketByCategory(@PathVariable("category") String category) {
    return new ResponseEntity<>(new Payload<>(null, ticketService.getTicketByCategory(category), HttpStatus.OK), HttpStatus.OK);
  }

  @GetMapping("/state/{state}")
  public ResponseEntity<Payload<List<TicketApi>>> getAllTicketByCategory(@PathVariable("state") Integer state) {
    return new ResponseEntity<>(new Payload<>(null, ticketService.getTicketByState(state), HttpStatus.OK), HttpStatus.OK);
  }
}
