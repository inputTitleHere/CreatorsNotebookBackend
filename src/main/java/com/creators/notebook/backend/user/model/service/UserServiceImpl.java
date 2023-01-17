package com.creators.notebook.backend.user.model.service;


import com.creators.notebook.backend.team.data.TeamAuth;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.service.TeamService;
import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcrypt;

  private final TeamService teamService;
  /**
   * Id를 기반으로 유저 객체를 찾아온다.
   * @param uuid
   * @return
   */
  @Override
  public UserEntity findByUuid(UUID uuid) {
    return userRepository.findById(uuid).orElse(null);
  }

  /**
   * 로그인 메소드. Id와 password를 기반으로 데이터베이스 확인.
   * @param userEntity
   * @return boolean. true if User Exists. false if User not Exists
   */
  @Override
  public UserEntity login(UserEntity userEntity) {
    //TODO :: find by email으로 변경.
    UserEntity user = userRepository.findByUserEmail(userEntity.getUserEmail());
    log.debug("User Found :: {}",user);
//    UserEntity usertemp = findByUuid(userEntity.getUserUuid());
    // 아이디가 없으면 false 반환
    if(user==null){
      return null;
    }
    // 비밀번호가 맞으면 true / 틀리면 false 반환
    if(bcrypt.matches(userEntity.getUserPassword(), user.getUserPassword())){
      return user;
    }else{
      return null;
    }
  }

  @Override
  public UserEntity register(UserEntity userEntity) {
    if(userEntity.getUserPassword()==null){
      throw new IllegalArgumentException("비밀번호는 비어있으면 안됩니다. 이 오류는 잘못된 API접근에 의한 것입니다.");
    }
    userEntity.setUserPassword(bcrypt.encode(userEntity.getUserPassword()));
    UserEntity newUser = userRepository.save(userEntity);

    // TeamEntity, UserTeamEntity, UserEntity
    TeamEntity newTeam = TeamEntity.builder()
            .teamName("나의 프로젝트")
            .teamPrivate(true)
            .build();
    TeamEntity createdPrivateTeam = teamService.createTeam(newTeam);
    UserTeamEntity ute = UserTeamEntity.builder()
            .userUuid(newUser.getUserUuid())
            .teamUuid(createdPrivateTeam.getTeamUuid())
            .teamAuth(TeamAuth.CREATOR)
            .build();
    log.debug("UTE = {}",ute);
    UserTeamEntity userTeamRealation = teamService.mapTeam(ute);

    return newUser;
  }


}
