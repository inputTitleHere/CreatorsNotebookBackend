package com.creators.notebook.backend.user.model.data;

import com.creators.notebook.backend.team.data.TeamDto;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private UUID userUuid;
  private String userPassword;
  private String userName;
  private UserPrivilegeEnum userPrivilegeEnum;
  private String userEmail;
  private LocalDateTime userJoinedAt;
  private LocalDateTime userDeletedAt;
  private List<TeamDto> teamDtos;

}
