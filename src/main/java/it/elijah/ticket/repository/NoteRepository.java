package it.elijah.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.elijah.ticket.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{

}
