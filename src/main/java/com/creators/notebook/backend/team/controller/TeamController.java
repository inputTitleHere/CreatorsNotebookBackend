package com.creators.notebook.backend.team.controller;

import com.creators.notebook.backend.team.data.TeamDto;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
  private final TeamService teamService;

  @PostMapping("/create")
  public ResponseEntity<?> createTeam(@RequestBody TeamDto teamDto,@AuthenticationPrincipal UUID user){
    log.debug("Team Create : Team name -> {}", teamDto.getTeamName());

    Map<String, String> response = new HashMap<>();
    response.put("msg","CREATED");
    return ResponseEntity.ok(response);
  }




}
