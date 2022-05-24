package com.skilldistillery.plantdaddyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.skilldistillery.plantdaddyapp.services.UserService;

@Controller
@CrossOrigin({"*", "http://localhost"})
public class UserController {
  @Autowired
  private UserService userService;
}