package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForm {
  private Long id;
  @NotEmpty(message = "Required field")
  private String firstname;
  @NotEmpty(message = "Required field")
  private String lastname;
  private String sex;
  private String address;
  private MultipartFile image;
  private LocalDate birthdate;
  private String phone;
  private String post;
  private String cinNumber;
  private LocalDate cinDate;
  private String cinLocation;
  @Email
  private String email;
  private int children;
  private LocalDate entranceDate;
  private LocalDate exitDate;
  private String cnaps;
  @NotEmpty
  private String matricule;
}
