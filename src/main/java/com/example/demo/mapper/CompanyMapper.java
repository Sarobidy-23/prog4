package com.example.demo.mapper;

import com.example.demo.model.CompanyEntity;
import com.example.demo.model.CompanyForm;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
  public CompanyEntity toEntity(CompanyForm form) throws IOException {
    return CompanyEntity.builder()
        .name(form.getName())
        .logo(Base64.getEncoder().encodeToString(form.getLogo().getBytes()))
        .address(form.getAddress())
        .rcs(form.getRcs())
        .stat(form.getStat())
        .nif(form.getNif())
        .email(form.getEmail())
        .slogan(form.getSlogan())
        .build();
  }
}
