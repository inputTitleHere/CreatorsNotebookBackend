package com.creators.notebook.backend.project.controller;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.team.data.TeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

  @PostMapping("/new")
  public ResponseEntity<?> createNewProject(@RequestBody TeamDto teamDto, @RequestBody ProjectDto projectDto, @AuthenticationPrincipal UUID userId){




    return ResponseEntity.ok(null);
  }



}
