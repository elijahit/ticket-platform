package it.elijah.ticket.projections;

import java.time.LocalDate;
import java.util.List;

import it.elijah.ticket.model.Roles;

public interface TicketApi {
  public Integer getId();
  public String getTitle();
  public String getText();
  public Integer getState();
  public TicketApiUser getUser();
  public List<TicketApiNote> getNote();
  public List<TicketApiCategory> getCategory();


  interface TicketApiNote {
    public Integer getId();
    public String getText();
    public LocalDate getCreatedAt();
    public TicketApiUser getUser();
  }

  interface TicketApiCategory {
    public Integer getId();
    public String getCategory();
  }

  interface TicketApiUser {
    public String getUsername();
    public String getEmail();
    public Roles getRoles();
  }
}
