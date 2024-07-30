package it.elijah.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.elijah.ticket.model.Ticket;
import it.elijah.ticket.model.User;
import it.elijah.ticket.projections.TicketApi;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT p FROM Ticket p WHERE p.deleted = false AND p.title like %:title%")
    public List<Ticket> findByTitleIgnoreCase(String title);

    @Query("SELECT p FROM Ticket p WHERE p.deleted = false")
    public List<Ticket> findAllByDeleted();

    @Query("SELECT p FROM Ticket p WHERE p.deleted = false")
    public List<TicketApi> findAllByDeletedApi();

    @Query("SELECT p FROM Ticket p JOIN p.category c WHERE p.deleted = false AND c.category like %:category%")
    public List<TicketApi> findAllByDeletedApiCategory(@Param("category") String category);
    
    @Query("SELECT p FROM Ticket p WHERE p.deleted = false AND p.state = :state")
    public List<TicketApi> findAllByDeletedApiState(@Param("state") Integer category);

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

    @Query(
            "select u "
            + "from Ticket u "
            + "where u.user = :user "
            + "and u.deleted = false "
            + "and u.state BETWEEN 0 AND 1"
    )
    List<Ticket> getTicketByIdAndStatus(@Param("user") User user);
}
