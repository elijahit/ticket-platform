package it.elijah.ticket.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(name="username", nullable=false)
  private String username;

  @NotBlank
  @Column(name="password", nullable=false)
  private String password;

  @NotBlank
  @Column(name="email", nullable=false)
  private String email;

  private Boolean active;

  @ManyToMany(fetch=FetchType.EAGER)
  private List<Roles> roles;

  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Boolean getActive() {
    return active;
  }
  
  public void setActive(Boolean active) {
    this.active = active;
  }
  
  public List<Roles> getRoles() {
    return roles;
  }
  
  public void setRoles(List<Roles> roles) {
    this.roles = roles;
  }
}
