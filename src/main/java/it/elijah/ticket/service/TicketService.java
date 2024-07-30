package it.elijah.ticket.service;

import java.util.List;

import it.elijah.ticket.projections.TicketApi;

public interface TicketService {

  public List<TicketApi> getTicket();

  public List<TicketApi> getTicketByCategory(String category);

  public List<TicketApi> getTicketByState(Integer state);
}
