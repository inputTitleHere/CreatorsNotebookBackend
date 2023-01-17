package com.creators.notebook.backend.team.service;

import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.user.model.data.UserEntity;

public interface TeamService {

  public TeamEntity createTeam(TeamEntity newTeam);


  UserTeamEntity mapTeam(UserTeamEntity ute);
}
