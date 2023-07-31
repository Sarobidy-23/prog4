package com.example.demo.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilter {
  private String sex;
  private String firstname;
  private String lastname;
  private String post;
  private LocalDate entranceDateStart;
  private LocalDate entranceDateEnd;
  private LocalDate exitDateStart;
  private LocalDate exitDateEnd;
  private String firstnameOrder;
  private String lastnameOrder;
  private String sexOrder;
  private String postOrder;
  private String entranceDateOrder;
  private String exitDateOrder;
}
