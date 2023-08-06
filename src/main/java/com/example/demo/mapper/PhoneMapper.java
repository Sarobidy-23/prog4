package com.example.demo.mapper;

import com.example.demo.model.PhoneEntity;
import com.example.demo.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PhoneMapper {
  private final PhoneRepository repository;
  public PhoneEntity toDomain(PhoneEntity phone) {
    return repository.save(phone);
  }
}
