package com.creators.notebook.backend.project.model.service;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.project.model.data.ProjectVisibility;
import com.creators.notebook.backend.project.model.respository.ProjectRepository;
import com.creators.notebook.backend.team.data.TeamEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
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
        ProjectEntity result = projectRepository.findById(projectId).orElse(null);
        return result;
    }
}
