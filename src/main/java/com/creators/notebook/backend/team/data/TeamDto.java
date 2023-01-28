package com.creators.notebook.backend.team.data;

import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.user.model.data.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class TeamDto {
  private UUID teamUuid;
  private String teamName;
  private boolean teamPrivate;
  private List<ProjectEntity> projects;
  private List<UserDTO> userTeam;
}
