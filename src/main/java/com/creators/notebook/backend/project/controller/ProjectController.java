package com.creators.notebook.backend.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

  // JWT Auth 테스팅용
  @GetMapping("/proj.test")
  public ResponseEntity<?> projectTest(@AuthenticationPrincipal String userId){
    log.debug("Proj.test userID : {}",userId);
    return ResponseEntity.ok(String.format("TEST UserID : %s",userId));
  }
}
