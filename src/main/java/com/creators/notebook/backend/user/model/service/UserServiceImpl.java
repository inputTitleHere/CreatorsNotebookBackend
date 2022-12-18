package com.creators.notebook.backend.user.model.service;


import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcrypt;

  /**
   * Id를 기반으로 유저 객체를 찾아온다.
   * @param id
   * @return
   */
  @Override
  public UserEntity findById(String id) {
    return userRepository.findById(id).orElse(null);
  }

  /**
   * 로그인 메소드. Id와 password를 기반으로 데이터베이스 확인.
   * @param userEntity
   * @return boolean. true if User Exists. false if User not Exists
   */
  @Override
  public boolean login(UserEntity userEntity) {
    UserEntity user = findById(userEntity.getUserId());
    // 아이디가 없으면 false 반환
    if(user==null){
      return false;
    }
    // 비밀번호가 맞으면 true / 틀리면 false 반환
    return bcrypt.matches(userEntity.getUserPassword(), user.getUserPassword());
  }

  @Override
  public boolean register(UserEntity userEntity) {
    if(userEntity.getUserPassword()==null){
      throw new IllegalArgumentException("비밀번호는 비어있으면 안됩니다. 이 오류는 잘못된 API접근에 의한 것입니다.");
    }
    userEntity.setUserPassword(bcrypt.encode(userEntity.getUserPassword()));
    UserEntity result = userRepository.save(userEntity);

    return true;
  }


}
