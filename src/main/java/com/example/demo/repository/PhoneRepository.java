package com.example.demo.repository;

import com.example.demo.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
  PhoneEntity getByPhoneWithCountry(String phoneWithCountry);
}
