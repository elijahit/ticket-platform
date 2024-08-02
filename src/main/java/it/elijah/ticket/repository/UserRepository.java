package it.elijah.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.elijah.ticket.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query
  Optional<User> findByUsername(String username);

  @Query("SELECT u FROM User u WHERE u.active = true")
    public List<User> findAllByActive();

}
