package it.elijah.ticket.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="roles")
public class Roles {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(name="name", nullable=false, unique=true)
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result;
    return result;
}

  @Override
   public boolean equals(Object obj) {
      if(!(obj instanceof Roles)) {
         return false;
      }
      Roles role = (Roles)obj;
      return Objects.equals(this.id, role.getId()) && this.name.equals(role.getName());
   }
}
