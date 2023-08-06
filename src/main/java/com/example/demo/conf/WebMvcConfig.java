package com.example.demo.conf;

import com.example.demo.controller.LoginInterceptorHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
  private final LoginInterceptorHandler loginInterceptorHandler;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptorHandler)
        .addPathPatterns("/*")
        .addPathPatterns("/**")
        .excludePathPatterns("/login");
  }
}
