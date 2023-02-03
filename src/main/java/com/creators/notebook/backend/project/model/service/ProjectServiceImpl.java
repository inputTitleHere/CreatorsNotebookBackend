package com.creators.notebook.backend.project.model.service;

import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.project.model.respository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {
        ProjectEntity result = projectRepository.save(projectEntity);
        return result;
    }
}
