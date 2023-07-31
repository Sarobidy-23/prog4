package com.example.demo.mapper;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeForm;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

  public EmployeeEntity toUpdate(EmployeeForm form, EmployeeEntity entity) throws IOException {
    entity.setFirstname(form.getFirstname());
    entity.setLastname(form.getLastname());
    entity.setAddress(form.getAddress());
    entity.setEmail(form.getEmail());
    entity.setImage(
        form.getImage().getSize() >0 ? Base64.getEncoder().encodeToString(form.getImage().getBytes()) :
        entity.getImage()
    );
    entity.setBirthdate(form.getBirthdate() == null ? entity.getBirthdate() : form.getBirthdate());
    entity.setCinNumber(form.getCinNumber());
    entity.setCinDate(form.getCinDate() == null ? entity.getCinDate() : form.getCinDate());
    entity.setCinLocation(form.getCinLocation());
    entity.setSex(form.getSex());
    entity.setEntranceDate(form.getEntranceDate() == null ? entity.getEntranceDate() : form.getEntranceDate());
    entity.setPost(form.getPost());
    entity.setPhone(form.getPhone());
    entity.setChildren(form.getChildren());
    entity.setCnaps(form.getCnaps());
    entity.setMatricule(form.getMatricule());
    return entity;
  }
}
