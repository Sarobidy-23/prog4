package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptorHandler implements HandlerInterceptor {
  private List<String> exclusions = new ArrayList<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestUrl = request.getRequestURI();

    if (exclusions.contains(requestUrl)) {
      return true;
    }

    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("user") == null) {
      response.sendRedirect( "/login");
      return false;
    }
    return true;
  }

  public void setExclusions(List<String> exclusions) {
    this.exclusions = exclusions;
  }
}
