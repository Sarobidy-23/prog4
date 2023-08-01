package com.example.demo.service;

import com.example.demo.model.CompanyEntity;
import com.example.demo.repository.CompanyRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
  private final CompanyRepository repository;

  public void save(CompanyEntity entity) {
     repository.save(entity);
  }

  public CompanyEntity getOne() {
    try {
      List<CompanyEntity> list = repository.findAll();
      return list.get(0);
    } catch (Exception error) {
      return new CompanyEntity();
    }
  }
}
