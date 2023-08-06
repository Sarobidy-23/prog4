package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstname;
  private String lastname;
  private String sex;
  private String address;
  private String cinNumber;
  private LocalDate cinDate;
  private String cinLocation;
  @Lob
  private String image;
  private LocalDate birthdate;
  @OneToMany(mappedBy = "employee")
  @JsonIgnore
  private List<PhoneEntity> phones;
  private String post;
  @Column(unique = true)
  private String email;
  private int children;
  private LocalDate entranceDate;
  private LocalDate exitDate;
  @Column(unique = true)
  private String cnaps;
  @Column(unique = true)
  private String matricule;
  public enum Sex {
    Male,
    Female
  }
}
