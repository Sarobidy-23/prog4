package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyForm {
  private Long id;
  private String name;
  private String slogan;
  private String address;
  private String email;
  private String phone;
  private MultipartFile logo;
  private String nif;
  private String stat;
  private String rcs;
}
