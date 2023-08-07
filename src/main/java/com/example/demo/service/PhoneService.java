package com.example.demo.service;

import com.example.demo.model.PhoneEntity;
import com.example.demo.repository.PhoneRepository;
import java.util.List;
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
  public List<PhoneEntity> saveAll(List<PhoneEntity> entities) {
    return repository.saveAll(entities);
  }
}
