package com.creators.notebook.backend.team.data;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.creators.notebook.backend.user.model.data.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
  private UUID teamUuid;
  private String teamName;
  private boolean teamPrivate;
  private String teamDescription;
  private TeamAuth teamAuth;

  private List<ProjectDto> projects;
  private List<UserDTO> userTeam;
}
