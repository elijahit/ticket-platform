package it.elijah.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.elijah.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

  @Query("SELECT p FROM Ticket p WHERE p.title like %:title%")
	public List<Ticket> findByTitleIgnoreCase(String title);
}
