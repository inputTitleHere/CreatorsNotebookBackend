package com.creators.notebook.backend.project.model.service;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;

import java.util.UUID;

public interface ProjectService {
  public ProjectEntity save(ProjectDto projectDto);

  public ProjectEntity findById(UUID projectId);
}
