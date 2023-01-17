package com.creators.notebook.backend.team.controller;

import com.creators.notebook.backend.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
  private final TeamService teamService;

  @PostMapping("/create")
  public ResponseEntity<?> createTeam(){


    return ResponseEntity.ok("Created");
  }




}
