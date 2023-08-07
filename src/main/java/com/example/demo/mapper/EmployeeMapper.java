package com.example.demo.mapper;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeForm;
import com.example.demo.model.PhoneEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
  private final PhoneMapper phoneMapper;
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
        .csp(form.getCsp())
        .entranceDate(form.getEntranceDate())
        .exitDate(form.getExitDate())
        .post(form.getPost())
        .phones(form.getPhones())
        .children(form.getChildren())
        .cnaps(form.getCnaps())
        .matricule(form.getMatricule())
        .image(Base64.getEncoder().encodeToString(form.getImage().getBytes()))
        .build();
  }

  public PhoneEntity phoneEntity(PhoneEntity entity) {
    return PhoneEntity.builder()
        .phoneWithCountry(entity.getPhoneWithCountry()).build();
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
        .csp(entity.getCsp())
        .entranceDate(entity.getEntranceDate())
        .exitDate(entity.getExitDate())
        .post(entity.getPost())
        .phones(entity.getPhones())
        .children(entity.getChildren())
        .cnaps(entity.getCnaps())
        .matricule(entity.getMatricule())
        .build();
  }

  public List<PhoneEntity> toEntity(List<PhoneEntity> entities) {
    List<PhoneEntity> list = new ArrayList<>();
    entities.forEach(e -> list.add(PhoneEntity.builder()
        .phoneWithCountry(e.getPhoneWithCountry())
        .id(e.getId())
        .build()));
    return list;
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
    entity.setExitDate(form.getExitDate() == null ? entity.getExitDate() : form.getExitDate());
    entity.setPost(form.getPost());
    entity.setPhones(form.getPhones());
    entity.setChildren(form.getChildren());
    entity.setCnaps(form.getCnaps());
    entity.setMatricule(form.getMatricule());
    return entity;
  }
}
