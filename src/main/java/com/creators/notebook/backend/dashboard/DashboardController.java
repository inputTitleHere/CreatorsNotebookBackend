package com.creators.notebook.backend.dashboard;

import com.creators.notebook.backend.team.data.TeamDto;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.data.UserTeamPk;
import com.creators.notebook.backend.team.repository.UserTeamRepository;
import com.creators.notebook.backend.user.model.data.UserDTO;
import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/dashboard")
@Slf4j
@RequiredArgsConstructor
public class DashboardController {

  private final UserService userService;

  @GetMapping() // URL : ~/dashboard
  public ResponseEntity<?> dashboard(@AuthenticationPrincipal UUID id) {
    log.debug("DASHBOARD USER = {}", id);
//    UserEntity user = userService.loadDashboard(id);
    UserDTO response = userService.loadDashboard(id);

    return ResponseEntity.ok(response);
  }

}
