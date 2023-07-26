package com.example.demo.mapper;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeForm;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
  public EmployeeEntity toEntity(EmployeeForm form) throws IOException {
    return EmployeeEntity.builder()
        .id(form.getId())
        .firstname(form.getFirstname())
        .lastname(form.getLastname())
        .address(form.getAddress())
        .birthdate(form.getBirthdate())
        .cinNumber(form.getCinNumber())
        .cinDate(form.getCinDate())
        .email(form.getEmail())
        .cinLocation(form.getCinLocation())
        .sex(form.getSex())
        .entranceDate(form.getEntranceDate())
        .post(form.getPost())
        .phone(form.getPhone())
        .children(form.getChildren())
        .cnaps(form.getCnaps())
        .matricule(form.getMatricule())
        .image(Base64.getEncoder().encodeToString(form.getImage().getBytes()))
        .build();
  }

  public EmployeeForm toForm(EmployeeEntity entity) {
    return EmployeeForm.builder()
        .id(entity.getId())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .address(entity.getAddress())
        .email(entity.getEmail())
        .birthdate(entity.getBirthdate())
        .cinNumber(entity.getCinNumber())
        .cinDate(entity.getCinDate())
        .cinLocation(entity.getCinLocation())
        .sex(entity.getSex())
        .entranceDate(entity.getEntranceDate())
        .post(entity.getPost())
        .phone(entity.getPhone())
        .children(entity.getChildren())
        .cnaps(entity.getCnaps())
        .matricule(entity.getMatricule())
        .build();
  }
}
