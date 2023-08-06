package com.example.demo.validator;

import com.example.demo.model.PhoneEntity;
import com.example.demo.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PhoneValidator {
  private final PhoneRepository repository;
  public String validatePhone (PhoneEntity phone) {
    PhoneEntity phonePersist = repository.getByPhoneWithCountry(phone.getPhoneWithCountry());
    String message = "";
    String regPhone = "^\\+\\(\\d+\\)|\\s+";
    if (phonePersist != null) {
      message += " phone already exist ";
      message += phone.getPhoneWithCountry() + ",";
    }
    if(phone.getPhoneWithCountry().replaceAll(regPhone, "").length() != 9) {
      message += " Phone numbe length is 9 ";
      message += phone.getPhoneWithCountry() + ",";
    }
    if (phone.getPhoneWithCountry().matches("[^0-9]")) {
      message += "Phone's number must only contains digits. ";
      message += phone.getPhoneWithCountry() + ",";
    }
    return message;
  }
}
