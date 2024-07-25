package it.elijah.ticket.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
  @Column(name="title", nullable=false)
  private String title;

  @NotBlank
  @Column(name="text", nullable=false)
  private String text;

  @NotNull
  @Column(name="state", nullable=false)
  private Integer state;

  private Boolean deleted;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="user_id", nullable=false)
  private User user;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name="ticket_id", nullable=false, insertable=false, updatable=false)
  private List<Note> note;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Category> category;

  
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
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

  public List<Category> getCategory() {
    return category;
  }
  
  public void setCategory(List<Category> category) {
    this.category = category;
  }
}
