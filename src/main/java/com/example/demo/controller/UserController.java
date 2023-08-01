package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping("/login")
  public String showLoginForm(Model model) {
    model.addAttribute("user", User.builder().build());
    return "login";
  }

  @PostMapping("login")
  public String authenticate(@ModelAttribute User user, HttpSession session, BindingResult bindingResult) {
    User authUser = service.authenticate(user.getUsername(), user.getPassword());
    if (bindingResult.hasErrors() || authUser == null) {
      return "redirect:/login";
    }
    session.setAttribute("user",authUser);
    return "redirect:/employee";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    //session.invalidate();
    session.removeAttribute("user");
    return "redirect:/login";
  }
}
