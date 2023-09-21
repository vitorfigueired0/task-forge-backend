package com.vitorfigueired0.taskforge.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("version")
public class ApplicationVersionController {

  @Value("${spring.application.version}")
  private String version;

  @GetMapping
  public ResponseEntity<String> getVersion(){
    return new ResponseEntity<>(version, HttpStatus.OK);
  }
}
