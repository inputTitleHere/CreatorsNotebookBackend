package com.creators.notebook.backend.user.controller;

import com.creators.notebook.backend.security.config.JwtTokenProvider;
import com.creators.notebook.backend.team.data.TeamAuth;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.service.TeamService;
import com.creators.notebook.backend.user.model.data.UserDTO;
import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.data.UserResponseDTO;
import com.creators.notebook.backend.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor // 이것을 아래의 private final 필드와 결합하여 자동으로 spring framework가 넣어주게 한다.
public class UserController {

  private final UserService userService;
  private final TeamService teamService;
  private final JwtTokenProvider tokenProvider;

  /**
   * Id, password를 받는다.
   * 아이디가 존재하고 비밀번호가 맞는다면 true를 반환. 그 외의 경우 false 반환.
   *
   * @param userDTO
   * @return boolean true or false
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
    UserEntity user = userService.login(new UserEntity(userDTO));
    if (user != null) {
      final String token = tokenProvider.createToken(user);
      // 쿠키로 설정하기.
//      Cookie cookie = new Cookie("token",token);

      UserResponseDTO userResponseDTO = UserResponseDTO.builder().token(token).userUuid(user.getUserUuid()).userName(user.getUserName()).userPrivilegeEnum(user.getUserPrivilegeEnum()).build();
      return ResponseEntity.ok().body(userResponseDTO);
    } else {
      return ResponseEntity.badRequest().body("로그인 정보가 잘못되었습니다.");
    }
    // 비밀번호가 맞으면. JWT 발급.
//    if(result) return ResponseEntity.status(200).body("Login Success");
//    else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failure");
  }

  /**
   * 회원가입 API
   * 회원가입시 개인 팀을 생성한다.
   *
   * @param userDTO
   * @return
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
    log.debug("User DTO = {}", userDTO);
    UserEntity ue = new UserEntity(userDTO);
    log.debug("User Entity = {}", ue);
    Map<String, Object> msg = new HashMap<>();

    try {
      UserEntity registeredUser = userService.register(ue);


    } catch (Exception e) {
      log.error(e.getMessage());
      msg.put("msg", "계정 생성에 문제가 있었습니다.");
      throw e;
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(msg);
  }

  @GetMapping("/test")
  public ResponseEntity<?> testUser() {
    log.debug("USER TEST METHOD");
    return ResponseEntity.ok("USER TEST COMPLETE");
  }


}
