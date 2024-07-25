package it.elijah.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.elijah.ticket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
