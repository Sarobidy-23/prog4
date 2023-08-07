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
    StringBuilder message = new StringBuilder();
    String regPhone = "^\\+\\(\\d+\\)|\\s+";
    if (phonePersist != null && phone.getId() == null) {
      message.append(" phone already exist ");
      message.append(phone.getPhoneWithCountry()).append(",");
    }
    if(phone.getPhoneWithCountry().replaceAll(regPhone, "").length() != 9) {
      message.append(" Phone number length is to be 9 after the country code ");
      message.append(phone.getPhoneWithCountry()).append(",");
    }
    if (phone.getPhoneWithCountry().matches("[^0-9]")) {
      message.append("Phone's number must only contains digits. ");
      message.append(phone.getPhoneWithCountry()).append(",");
    }
    return message.toString();
  }
}
