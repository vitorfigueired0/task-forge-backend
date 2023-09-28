package com.vitorfigueired0.taskforge.controller;

import com.vitorfigueired0.taskforge.service.user.UserService;
import com.vitorfigueired0.taskforge.service.user.form.RegisterForm;
import com.vitorfigueired0.taskforge.service.user.form.UserForm;
import com.vitorfigueired0.taskforge.service.user.form.ValidateCodeForm;
import com.vitorfigueired0.taskforge.service.user.view.LoginView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterForm registerForm) {
    userService.register(registerForm);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/validateCode")
  public ResponseEntity<?> validateCode(@Valid @RequestBody ValidateCodeForm validateCodeForm) {
    userService.validateCode(validateCodeForm);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginView> login(@Valid @RequestBody UserForm userForm) {
    LoginView login = userService.login(userForm);
    return new ResponseEntity<>(login, HttpStatus.OK);
  }
}
