package com.creators.notebook.backend.index.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class IndexController {

  @GetMapping("/connection.test")
  public ResponseEntity<?> connectionTest(){
    log.debug("Connection is Set. Request is acknowledged");
    return ResponseEntity.ok("Connection is Set. Server Responded");
  }
}
