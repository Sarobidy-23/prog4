package com.example.demo.controller;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.CompanyEntity;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeFilter;
import com.example.demo.model.EmployeeForm;
import com.example.demo.service.CompanyService;
import com.example.demo.service.EmployeeService;
import com.example.demo.validator.EmployeeValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.PrintWriter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@Slf4j
public class EmployeeController implements WebMvcConfigurer {
  private final CompanyService companyService;
  private final EmployeeService service;
  private final EmployeeMapper mapper;
  private final EmployeeValidator validator;
  @GetMapping("employee")
  public String List(Model model,
                     @RequestParam(defaultValue = "0", required = false)int page,
                     @RequestParam(defaultValue = "15", required = false)int pageSize,
                     @ModelAttribute EmployeeFilter filter,
                     HttpSession session) {
    System.out.println(filter);
    session.setAttribute("employeeFilter", filter);
    CompanyEntity company = companyService.getOne();
    model.addAttribute("company", company);
    List<EmployeeEntity> resultFilter = service.getWithFilter(filter, page, pageSize);
    model.addAttribute("employees", resultFilter);
    model.addAttribute("employeeFilter", EmployeeFilter.builder().build());
    return "employee";
  }
  @GetMapping("employee/create")
  public String Create(Model model) {
    CompanyEntity company = companyService.getOne();
    model.addAttribute("company", company);
    model.addAttribute("buttonLabel", "Create");
    model.addAttribute("employee", EmployeeForm.builder().build());
    model.addAttribute("title", "Add employee");
    model.addAttribute("endpoint", "new");
    return "employeeForm";
  }

  @GetMapping("employee/details")
  public String details(@RequestParam(name = "employeeId") String id, Model model) {
    EmployeeEntity employee = service.findById(id);
    CompanyEntity company = companyService.getOne();
    model.addAttribute("company", company);
    model.addAttribute("employee", employee);
    return "detail";
  }
  @GetMapping("employee/edit")
  public String edit(@RequestParam(name = "employeeId")String id, Model model) {
    CompanyEntity company = companyService.getOne();
    model.addAttribute("company", company);
    EmployeeEntity employee = service.findById(id);
    EmployeeForm toUpdate = mapper.toForm(employee);
    model.addAttribute("employee", toUpdate);
    model.addAttribute("buttonLabel", "Update");
    model.addAttribute("title", "Edit employee");
    model.addAttribute("endpoint", "edit");
    return "employeeForm";
  }

  @GetMapping("employee/export")
  @ResponseBody
  public void exportCSV(HttpServletResponse response,  HttpSession session,
                        @RequestParam(defaultValue = "0", required = false)int page,
                        @RequestParam(defaultValue = "15", required = false)int pageSize) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=employeeList.csv");
    EmployeeFilter filter = (EmployeeFilter) session.getAttribute("employeeFilter");
    List<EmployeeEntity> entities = service.getWithFilter(filter,page,pageSize);
    log.info(entities.toString());
    try {
      CSVExporter(response.getWriter(), entities);
    } catch (Exception err) {
      throw new CustomError(err.getMessage());
    }
  }

  @PostMapping("employee/edit")
  public String editAction(@Valid @ModelAttribute EmployeeForm form, @RequestParam("id")String id) {
    try {
      EmployeeEntity employee = service.findById(id);
      EmployeeEntity toSaved = mapper.toUpdate(form, employee);
      return saveForm(toSaved);
    } catch (Exception error) {
      throw new CustomError(error.getMessage());
    }
  }

  @PostMapping("employee/new")
  public String postCreate(@Valid @ModelAttribute EmployeeForm form, BindingResult bindingResult, Model model) {
    try {
      EmployeeEntity toSaved = mapper.toEntity(form);
      return saveForm(toSaved);
    } catch (Exception exception) {
      throw new CustomError(exception.getMessage());
    }
  }

  private String saveForm(EmployeeEntity toSaved) {
  try {
    String errors = validator.validateForm(toSaved);
      if (!errors.isEmpty()) {
        throw new CustomError(errors);
    }
    service.save(toSaved);
  } catch (Exception exception) {
    throw new CustomError(exception.getMessage());
  }
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
              employee.getPhones() + separator +
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
