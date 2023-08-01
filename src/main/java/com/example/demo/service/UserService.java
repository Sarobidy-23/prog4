package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;

  public User authenticate(String usernanme, String password) {
    Optional<User> user = repository.findByUsername(usernanme);
     try {
       if (user.isPresent() && user.get().getPassword().equals(password)) {
         return user.get();
       }
    } catch (Exception error) {
       throw  new RuntimeException(error.getMessage());
     }
    return null;
  }
}
