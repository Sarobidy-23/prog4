package com.example.demo.validator;

import com.example.demo.model.EmployeeEntity;
import java.util.List;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeValidator {
  private final PhoneValidator phoneValidator;
  public String validateForm(EmployeeEntity employeeForm) {
    StringBuilder message = new StringBuilder();
    if (employeeForm.getFirstname().isEmpty()) {
      message.append(" firstname invalid, ");
    }
    if (employeeForm.getLastname().isEmpty()) {
      message.append(" lastname invalid, ");
    }
    if (!isAlphanumeric(employeeForm.getCnaps()) || employeeForm.getCnaps().isEmpty()) {
      message.append(" invalid cnaps number ");
    }
    if (!isEmailTruthy(employeeForm.getEmail())) {
      message.append(" invalid email, ");
    }
    List<String> phoneValidate = employeeForm.getPhones().stream().map(phoneValidator::validatePhone).toList();
    for(String error: phoneValidate) {
      message.append(error);
    }
    if (employeeForm.getPhones().isEmpty()) {
      message.append(" employee need one or more phone ");
    }
    return message.toString();
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
