package com.example.demo.controller;

import com.example.demo.mapper.CompanyMapper;
import com.example.demo.model.CompanyEntity;
import com.example.demo.model.CompanyForm;
import com.example.demo.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CompanyController implements WebMvcConfigurer {
  private final CompanyService service;
  private final CompanyMapper mapper;

  @GetMapping("company")
  public String save(Model model) {
    model.addAttribute("company", CompanyForm.builder().build());
    return "company";
  }

  @PostMapping("company/create")
  public String saveF(@Valid @ModelAttribute(name = "company") CompanyForm form) {
    try {
      CompanyEntity entity = mapper.toEntity(form);
      service.save(entity);
    } catch (Exception err) {
      throw new RuntimeException(err.getMessage());
    }
    return "redirect:/company";
  }
}
