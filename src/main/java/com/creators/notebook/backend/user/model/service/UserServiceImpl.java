package com.creators.notebook.backend.user.model.service;


import com.creators.notebook.backend.project.model.data.ProjectDto;
import com.creators.notebook.backend.team.data.TeamAuth;
import com.creators.notebook.backend.team.data.TeamDto;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.service.TeamService;
import com.creators.notebook.backend.user.model.data.UserDTO;
import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcrypt;

  private final TeamService teamService;

  /**
   * Id를 기반으로 유저 객체를 찾아온다.
   *
   * @param uuid
   * @return
   */
  @Override
  public UserEntity findByUuid(UUID uuid) {

    UserEntity userEntity = userRepository.findById(uuid).orElse(null);
    return userEntity;
  }

  /**
   * 로그인 메소드. Id와 password를 기반으로 데이터베이스 확인.
   *
   * @param userEntity
   * @return boolean. true if User Exists. false if User not Exists
   */
  @Override
  public UserEntity login(UserEntity userEntity) {
    UserEntity user = userRepository.findByUserEmail(userEntity.getUserEmail());
    log.debug("User Found :: {}", user);
//    UserEntity usertemp = findByUuid(userEntity.getUserUuid());
    // 아이디가 없으면 false 반환
    if (user == null) {
      return null;
    }
    // 비밀번호가 맞으면 true / 틀리면 false 반환
    if (bcrypt.matches(userEntity.getUserPassword(), user.getUserPassword())) {
      return user;
    } else {
      return null;
    }
  }

  @Override
  public UserEntity register(UserEntity userEntity) {
    if (userEntity.getUserPassword() == null) {
      throw new IllegalArgumentException("비밀번호는 비어있으면 안됩니다. 이 오류는 잘못된 API접근에 의한 것입니다.");
    }
    userEntity.setUserPassword(bcrypt.encode(userEntity.getUserPassword()));
    UserEntity newUser = userRepository.save(userEntity);

    // TeamEntity, UserTeamEntity, UserEntity
    TeamEntity newTeam = TeamEntity.builder()
            .teamName("나의 프로젝트")
            .teamDescription("나만의 개인 프로젝트들입니다.")
            .teamPrivate(true)
            .build();
    TeamEntity createdPrivateTeam = teamService.createTeam(newTeam);
    UserTeamEntity ute = UserTeamEntity.builder()
            .userUuid(newUser.getUserUuid())
            .teamUuid(createdPrivateTeam.getTeamUuid())
            .teamAuth(TeamAuth.CREATOR)
            .build();
//    UserTeamEntity ute = UserTeamEntity.builder()
//            .userEntity(newUser)
//            .teamEntity(createdPrivateTeam)
//            .teamAuth(TeamAuth.CREATOR)
//            .build();
    log.debug("UTE = {}", ute);
    UserTeamEntity userTeamRelation = teamService.mapTeam(ute);

    return newUser;
  }

  @Override
  public UserEntity findByEmail(String userEmail) {
    return userRepository.findByUserEmail(userEmail);
  }

  @Override
  public UserDTO loadDashboard(UUID id) {
    UserEntity user = userRepository.findByUserUuid(id);
    List<TeamDto> teams = new ArrayList<>();
    user.getUserTeamEntities().stream().forEach((item) -> {
      TeamEntity te = item.getTeamEntity();
      teams.add(TeamDto.builder()
              .teamUuid(te.getTeamUuid())
              .teamName(te.getTeamName())
              .teamPrivate(te.isTeamPrivate())
              .teamDescription(te.getTeamDescription())
              .projects(te.getProjects().stream().map((proj) -> ProjectDto.builder()
                      .projectUuid(proj.getProjectUuid())
                      .projectName(proj.getProjectName())
                      .projectDescription(proj.getProjectDescription())
                      .projectCreatedAt(proj.getProjectCreatedAt())
                      .projectUpdatedAt(proj.getProjectUpdatedAt())
                      .build()
              ).collect(Collectors.toList()))
              .teamAuth(item.getTeamAuth())
              .build()
      );
    });
    UserDTO response = UserDTO.builder()
            .userUuid(user.getUserUuid())
            .userName(user.getUserName())
            .userPrivilegeEnum(user.getUserPrivilegeEnum())
            .teamDtos(teams)
            .build();

    return response;
  }


}
