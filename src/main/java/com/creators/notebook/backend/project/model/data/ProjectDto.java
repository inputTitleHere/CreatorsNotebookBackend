package com.creators.notebook.backend.project.model.data;

import com.creators.notebook.backend.team.data.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private UUID projectId;
    private String projectName;
    private String projectDescription;
    private LocalDateTime projectCreatedAt;
    private LocalDateTime projectUpdatedAt;
    private UUID teamUuid;



}
