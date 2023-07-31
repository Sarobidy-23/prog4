package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
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
  private String phone;
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
