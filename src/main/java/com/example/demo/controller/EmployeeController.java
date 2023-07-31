package com.example.demo.controller;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeFilter;
import com.example.demo.model.EmployeeForm;
import com.example.demo.service.EmployeeService;
import com.example.demo.validator.EmployeeValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.PrintWriter;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class EmployeeController implements WebMvcConfigurer {
  private final EmployeeService service;
  private final EmployeeMapper mapper;
  private final EmployeeValidator validator;
  @GetMapping("employee")
  public String List(Model model,
                     @RequestParam(defaultValue = "0", required = false)int page,
                     @RequestParam(defaultValue = "15", required = false)int pageSize,
                     @ModelAttribute EmployeeFilter filter) {
    List<EmployeeEntity> resultFilter = service.getWithFilter(filter, page, pageSize);
    model.addAttribute("employees", resultFilter);
    model.addAttribute("employeeFilter", EmployeeFilter.builder().build());
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
  @GetMapping("employee/edit")
  public String edit(@RequestParam(name = "employeeId")String id, Model model) {
    EmployeeEntity employee = service.findById(id);
    EmployeeForm toUpdate = mapper.toForm(employee);
    model.addAttribute("employee", toUpdate);
    return "edit";
  }

  @GetMapping("employee/export")
  @ResponseBody
  public void exportCSV(HttpServletResponse response) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=employeeList.csv");
    List<EmployeeEntity> entities = service.getAll(1,15);
    try {
      CSVExporter(response.getWriter(), entities);
    } catch (Exception err) {
      throw new RuntimeException(err.getMessage());
    }
  }

  @PostMapping("employee/edit")
  public String editAction(@Valid @ModelAttribute EmployeeForm form, BindingResult bindingResult,
                           HttpServletRequest request, Model model, @RequestParam("id")String id) {
    System.out.println(request.getParameter("countryCode"));
    try {
      EmployeeEntity employee = service.findById(id);
      EmployeeEntity toSaved = mapper.toUpdate(form, employee);
      EmployeeForm toUpdate = mapper.toForm(employee);
      return saveForm(toSaved, bindingResult, model, toUpdate, "edit");
    } catch (Exception error) {
      throw new RuntimeException(error.getMessage());
    }
  }

  @PostMapping("employee/new")
  public String postCreate(@Valid @ModelAttribute EmployeeForm form, BindingResult bindingResult, Model model) {
    EmployeeForm initial = EmployeeForm.builder().build();
    try {
      EmployeeEntity toSaved = mapper.toEntity(form);
      return saveForm(toSaved, bindingResult, model, initial, "create");
    } catch (Exception error) {
      throw  new RuntimeException(error.getMessage());
    }
  }

  private String saveForm(EmployeeEntity toSaved,
                          BindingResult bindingResult, Model model, EmployeeForm initial,
                          String viewTemplate) {
    String errors = validator.validateForm(toSaved);
    if (!errors.isEmpty()) {
      ObjectError newError = new ObjectError("Global", errors);
      bindingResult.addError(newError);
    }
    if (bindingResult.hasErrors()) {
      model.addAttribute("employee", initial);
      model.addAttribute("errors", bindingResult.getAllErrors());
      return viewTemplate;
    }
    service.save(toSaved);
    return "redirect:/employee";
  }

  public void CSVExporter(PrintWriter writer, List<EmployeeEntity> employeeList) {
    String separator = ",";
    writer.println("Firstname, Lastname, Sex, Address, Phone, CNAPS number, Children in charge, Email, Post in entreprise," +
        "Matricule, CIN number, CIN date delivrance, CIN place delivrance, Hire date, Hire end date");
    for (EmployeeEntity employee : employeeList) {
      writer.println(
          employee.getFirstname() + separator +
              employee.getLastname() + separator +
              employee.getSex() + separator +
              employee.getAddress() + separator +
              employee.getPhone() + separator +
              employee.getCnaps() + separator +
              employee.getChildren() + separator +
              employee.getEmail() + separator +
              employee.getPost() + separator +
              employee.getMatricule() + separator +
              employee.getCinNumber() + separator +
              employee.getCinDate() + separator +
              employee.getCinLocation() + separator +
              employee.getEntranceDate() + separator +
              employee.getExitDate() + separator
      );
    }
  }

}
