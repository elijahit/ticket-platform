package it.elijah.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.elijah.ticket.model.Ticket;
import it.elijah.ticket.model.User;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT p FROM Ticket p WHERE p.deleted = false AND p.title like %:title%")
    public List<Ticket> findByTitleIgnoreCase(String title);

    @Query("SELECT p FROM Ticket p WHERE p.deleted = false")
    public List<Ticket> findAllByDeleted();

    @Query(
            "select u "
            + "from Ticket u "
            + "where u.user = :user "
            + "and u.deleted = false"
    )
    List<Ticket> getTicketById(@Param("user") User user);

    @Query(
            "select u "
            + "from Ticket u "
            + "where u.user = :user and u.title like %:title% "
            + "and u.deleted = false"
    )
    List<Ticket> getTicketById(@Param("user") User user, @Param("title") String title);
}
