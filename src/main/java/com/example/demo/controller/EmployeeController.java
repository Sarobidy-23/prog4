package com.example.demo.controller;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeForm;
import com.example.demo.service.EmployeeService;
import com.example.demo.validator.EmployeeValidator;
import jakarta.validation.Valid;
import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class EmployeeController implements WebMvcConfigurer {
  private final EmployeeService service;
  private final EmployeeMapper mapper;
  @GetMapping("employee")
  public String List(Model model,
                     @RequestParam(defaultValue = "0", required = false)int page,
                     @RequestParam(defaultValue = "15", required = false)int pageSize) {
    List<EmployeeEntity> employees = service.getAll(page, pageSize);
    model.addAttribute("employees", employees);
    return "employee";
  }
  @GetMapping("employee/create")
  public String Create(Model model) {
    model.addAttribute("employee", EmployeeForm.builder().build());
    return "create";
  }

  @GetMapping("employee/details")
  public String details(@RequestParam(name = "employeeId") String id, Model model) {
    EmployeeEntity employee = service.findById(id);
    model.addAttribute("employee", employee);
    return "detail";
  }

  @PostMapping("employee/new")
  public String postCreate(@Valid @ModelAttribute EmployeeForm form, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getAllErrors()+"okok");
      model.addAttribute("employee", EmployeeForm.builder().build());
      model.addAttribute("errors", bindingResult.getAllErrors());
      return "create";
    }
    try {
      EmployeeEntity toSave = mapper.toEntity(form);
      service.save(toSave);
    }  catch (IOException e) {
      throw new RuntimeException(e);
    }
    return "redirect:/employee";
  }

  @PostMapping("employee/filter")
  public String filter(Model model,
                       @RequestParam(defaultValue = "0", required = false)int page,
                       @RequestParam(defaultValue = "15", required = false)int pageSize,
                       @RequestParam(required = false, name = "sex")String sex,
                       @RequestParam(required = false, name = "firstname")String firstname,
                       @RequestParam(required = false, name = "lastname")String lastname,
                       @RequestParam(required = false, name = "office")String office) {
    List<EmployeeEntity> resultFilter = service.getWithFilter(firstname, lastname, sex, office, page, pageSize);
    model.addAttribute("employees", resultFilter);
    return "employee";
  }
}
