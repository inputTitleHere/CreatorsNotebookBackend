package com.creators.notebook.backend.project.model.service;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.project.model.data.ProjectVisibility;
import com.creators.notebook.backend.project.model.respository.ProjectRepository;
import com.creators.notebook.backend.team.data.TeamAuth;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.data.UserTeamPk;
import com.creators.notebook.backend.team.repository.UserTeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final UserTeamRepository userTeamRepository;

  @Override
  public ProjectEntity save(ProjectDto projectDto) {
    LocalDateTime now = LocalDateTime.now();
    ProjectEntity projectEntity = ProjectEntity.builder()
            .projectName(projectDto.getProjectName())
            .projectDescription(projectDto.getProjectDescription())
            .projectCreatedAt(now)
            .projectUpdatedAt(now)
            .projectVisibility(ProjectVisibility.PRIVATE)
            .teamEntity(TeamEntity.builder().teamUuid(projectDto.getTeamUuid()).build())
            .build();
    ProjectEntity result = projectRepository.save(projectEntity);
    return result;
  }

  @Override
  public ProjectEntity findById(UUID projectId) {
    return projectRepository.findById(projectId).orElse(null);
  }

  @Override
  public void delete(ProjectDto projectDto, UUID userUuid) throws IllegalAccessException {
    try {
      UserTeamEntity userTeamEntity = userTeamRepository.findById(new UserTeamPk(userUuid, projectDto.getTeamUuid())).orElseThrow(IllegalAccessError::new);
      // 삭제시도 사용자가 팀 정보상 CREATOR 에 해당되는 경우만 삭제 가능(프런트에서 한번 더 거를 예정)
      if(userUuid.equals(userTeamEntity.getUserUuid()) && projectDto.getTeamUuid().equals(userTeamEntity.getTeamUuid())){
        TeamAuth auth = userTeamEntity.getTeamAuth();
        if(auth.equals(TeamAuth.CREATOR) || auth.equals(TeamAuth.ADMIN)){
          // 삭제 하기
          projectRepository.deleteById(projectDto.getProjectId());
        }
      }
    } catch (NoSuchElementException e) {
      throw new IllegalAccessException("삭제 권한이 없습니다.");
    }
  }
}
