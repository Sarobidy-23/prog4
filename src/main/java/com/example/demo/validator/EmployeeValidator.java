package com.example.demo.validator;

import com.example.demo.model.EmployeeEntity;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeValidator {
  private final PhoneValidator phoneValidator;
  public String validateForm(EmployeeEntity employeeForm) {
    String message = "";
    if (employeeForm.getFirstname().isEmpty()) {
      message += " firstname invalid, ";
    }
    if (employeeForm.getLastname().isEmpty()) {
      message += " lastname invalid, ";
    }
    if (!isAlphanumeric(employeeForm.getCnaps()) || employeeForm.getCnaps().isEmpty()) {
      message += " invalid cnaps number ";
    }
    if (!isEmailTruthy(employeeForm.getEmail())) {
      message += " invalid email, ";
    }
    String phoneValidate = employeeForm.getPhones().stream().map(phoneValidator::validatePhone).toString();
    if (!phoneValidate.isEmpty()) {
      message += phoneValidate;
    }
    return message;
  }

  private Boolean isAlphanumeric(String string) {
    return validateByRegex(string, "^[a-zA-Z0-9]*$");
  }

  private Boolean isEmailTruthy(String email) {
    return validateByRegex(email, "^(.+)@(\\S+)$") ;
  }

  private Boolean validateByRegex(String value, String regexPattern) {
    Pattern pattern = Pattern.compile(regexPattern);
    return pattern.matcher(value).find();
  }
}
