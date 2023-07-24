package com.example.demo.validator;

import com.example.demo.model.EmployeeForm;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {
  public String validateForm(EmployeeForm employeeForm) {
    String message = "";
    if (employeeForm.getFirstname().isEmpty()) {
      message += "firstname invalid";
    }
    return message;
  }
}
