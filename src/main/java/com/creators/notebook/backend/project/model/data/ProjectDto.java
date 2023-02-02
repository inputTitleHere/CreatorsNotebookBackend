package com.creators.notebook.backend.project.model.data;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProjectDto {
    private UUID projectId;
    private String projectName;
    private String projectDescription;
    private LocalDateTime projectCreatedAt;
    private LocalDateTime projectUpdatedAt;


}
