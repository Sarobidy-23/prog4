package com.example.demo.validator;

import com.example.demo.model.EmployeeForm;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {
  public String validateForm(EmployeeForm employeeForm) {
    String message = "";
    if (employeeForm.getFirstname().isEmpty()) {
      message += "firstname invalid";
    }
    if (employeeForm.getLastname().isEmpty()) {
      message += "lastname invalid";
    }
    if (!isAlphanumeric(employeeForm.getCnaps()) || employeeForm.getCnaps().isEmpty()) {
      message += "invalid cnaps number";
    }
    if (employeeForm.getPhone().isEmpty()) {
      message += "invalid phone";
    }
    if (!isEmailTruthy(employeeForm.getEmail())) {
      message += "invalid email";
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
