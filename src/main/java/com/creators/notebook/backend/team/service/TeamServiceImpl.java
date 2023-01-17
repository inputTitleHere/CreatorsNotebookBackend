package com.creators.notebook.backend.team.service;

import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.repository.TeamRepository;
import com.creators.notebook.backend.team.repository.UserTeamRepository;
import com.creators.notebook.backend.user.model.data.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{

  private final TeamRepository teamRepository;
  private final UserTeamRepository userTeamRepository;

  @Override
  public TeamEntity createTeam(TeamEntity teamEntity) {
    TeamEntity te = teamRepository.save(teamEntity);
    return te;
  }

  @Override
  public UserTeamEntity mapTeam(UserTeamEntity ute) {
    UserTeamEntity bridge = userTeamRepository.save(ute);
    return bridge;
  }
}
