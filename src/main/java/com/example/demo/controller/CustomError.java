package com.example.demo.controller;

import java.rmi.ServerException;

public class CustomError extends RuntimeException {
  public CustomError(String message) {
    super(message);
  }
}
