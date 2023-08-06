package com.example.demo.service;

import com.example.demo.model.PhoneEntity;
import com.example.demo.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {
  private final PhoneRepository repository;

  public PhoneEntity saveOne(PhoneEntity entity) {
    return repository.save(entity);
  }
}
