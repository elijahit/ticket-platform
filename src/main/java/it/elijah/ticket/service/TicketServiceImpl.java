package it.elijah.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.elijah.ticket.projections.TicketApi;
import it.elijah.ticket.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<TicketApi> getTicket() {
      return ticketRepository.findAllByDeletedApi();
    }

    @Override
    public List<TicketApi> getTicketByCategory(String category) {
      return ticketRepository.findAllByDeletedApiCategory(category);
    }

    @Override
    public List<TicketApi> getTicketByState(Integer state) {
      return ticketRepository.findAllByDeletedApiState(state);
    }

}
