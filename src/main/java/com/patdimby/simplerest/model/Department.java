package com.patdimby.simplerest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

/** This class represents a Department entity. Each department has an ID and a name. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

  /** The ID of the department. It is unique and generated automatically. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** The name of the department. */
  private String name;

  /** The list of employees in the department. */
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Employee> employees;

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return this.name;
  }
}
