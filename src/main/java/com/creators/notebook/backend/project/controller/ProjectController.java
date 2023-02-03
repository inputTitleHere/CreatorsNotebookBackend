package com.creators.notebook.backend.project.controller;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.project.model.service.ProjectService;
import com.creators.notebook.backend.team.data.TeamDto;
import com.creators.notebook.backend.team.data.TeamEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

  // JWT Auth 테스팅용
  @GetMapping("/proj.test")
  public ResponseEntity<?> projectTest(@AuthenticationPrincipal String userId){
    log.debug("Proj.test userID : {}",userId);
    return ResponseEntity.ok(String.format("TEST UserID : %s",userId));
  }

  /**
   * projectDTO : {
   *     project name,
   * }
   */
  @PostMapping("/new")
  public ResponseEntity<?> createNewProject(@RequestBody TeamDto teamDto, @RequestBody ProjectDto projectDto, @AuthenticationPrincipal UUID userId){
    log.debug("team UUID to Connect : {}",teamDto.getTeamUuid());
    LocalDateTime now = LocalDateTime.now();
    ProjectEntity projectEntity = ProjectEntity.builder()
            .projectName(projectDto.getProjectName())
            .projectDescription(projectDto.getProjectDescription())
            .projectCreatedAt(now)
            .projectUpdatedAt(now)
            .teamEntity(TeamEntity.builder().teamUuid(teamDto.getTeamUuid()).build())
            .build();

    ProjectEntity pe = projectService.save(projectEntity);

    return ResponseEntity.ok(pe);
  }



}
