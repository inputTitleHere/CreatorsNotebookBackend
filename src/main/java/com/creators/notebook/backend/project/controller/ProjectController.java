package com.creators.notebook.backend.project.controller;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.project.model.service.ProjectService;
import com.creators.notebook.backend.utils.SimpleMsgResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

  /**
   * projectDTO : {
   * project name,
   * }
   */
  @PostMapping("/new")
  public ResponseEntity<?> createNewProject(@RequestBody ProjectDto projectDto) {
    log.debug("team UUID to Connect : {}", projectDto.getTeamUuid());
    try {
      ProjectEntity pe = projectService.save(projectDto);
      log.debug("New Project UUID =>  {}", pe.getProjectId());
      return ResponseEntity.ok(pe);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(SimpleMsgResponse.builder().msg("Failed to create new project").build());
    }
  }

  /**
   * 팀 사항이나 공개 여부에 따라 반환여부 설정.
   *
   */
  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProject(@PathVariable UUID projectId) {
    log.debug("Received Proj ID = {}", projectId);
    ProjectEntity projectEntity = projectService.findById(projectId);
    // 프로젝트가 없으면 null 및 404 반환
    if (projectEntity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(projectEntity);
  }


  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteProject(@RequestBody ProjectDto projectDto, @AuthenticationPrincipal UUID userUuid) {
    try {
      projectService.delete(projectDto, userUuid);
      return ResponseEntity.ok(null);
    } catch (IllegalAccessException e) {
      return new ResponseEntity<>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
    }
  }


}
