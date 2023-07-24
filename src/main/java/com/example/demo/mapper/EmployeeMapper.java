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
        .firstname(form.getFirstname())
        .lastname(form.getLastname())
        .address(form.getAddress())
        .birthdate(form.getBirthdate())
        .sex(form.getSex())
        .entranceDate(form.getEntranceDate())
        .office(form.getOffice())
        .phone(form.getPhone())
        .children(form.getChildren())
        .cnaps(form.getCnaps())
        .image(Base64.getEncoder().encodeToString(form.getImage().getBytes()))
        .build();
  }
}
