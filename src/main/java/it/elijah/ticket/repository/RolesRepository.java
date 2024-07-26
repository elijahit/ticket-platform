package it.elijah.ticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.elijah.ticket.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

  @Query
  Optional<Roles> findByName(String name);
}
