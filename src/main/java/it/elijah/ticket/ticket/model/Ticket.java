package it.elijah.ticket.ticket.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="ticket")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(name="text", nullable=false)
  private String text;

  @NotNull
  @Column(name="state", nullable=false)
  private Integer state;

  private Boolean deleted;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="id_user", nullable=false)
  private User user;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name="id_ticket", nullable=false)
  private List<Note> note;

  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(String text) {
    this.text = text;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }
  
  public Boolean getDeleted() {
    return deleted;
  }
  
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public List<Note> getNote() {
    return note;
  }
  
  public void setNote(List<Note> note) {
    this.note = note;
  }
}
