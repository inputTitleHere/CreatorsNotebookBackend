package com.creators.notebook.backend.project.model.service;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;

import java.util.UUID;

public interface ProjectService {
  ProjectEntity save(ProjectDto projectDto);

  ProjectEntity findById(UUID projectId);

  void delete(ProjectDto projectDto,UUID userUuid) throws IllegalAccessException;
}
