package com.creators.notebook.backend.project.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private UUID projectUuid;
    private String projectName;
    private String projectDescription;
    private LocalDateTime projectCreatedAt;
    private LocalDateTime projectUpdatedAt;
    private ProjectVisibility projectVisibility;
    private UUID teamUuid;



}
