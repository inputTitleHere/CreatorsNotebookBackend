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
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
  public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
  log.debug("USER 정보 : {}",userDTO);
    UserEntity user = userService.login(UserEntity.builder().userEmail(userDTO.getUserEmail()).userPassword(userDTO.getUserPassword()).build());
    if (user != null) {
      // 쿠키로 설정하기.
//      Cookie cookie = new Cookie("token",token);

      final String token = tokenProvider.createToken(user);
      UserResponseDTO userResponseDTO = UserResponseDTO.builder().token(token).userUuid(user.getUserUuid()).userName(user.getUserName()).userPrivilegeEnum(user.getUserPrivilegeEnum()).build();
      return ResponseEntity.ok().body(userResponseDTO);
    } else {
      Map<String,String> result = new HashMap<>();
      result.put("msg","로그인 정보가 잘못되었습니다.");
      return ResponseEntity.badRequest().body(result);
    }
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

    UserEntity findUserByEmail = userService.findByEmail(ue.getUserEmail());

    // 등록된 이메일이면 (프런트단에서 원래 자를것) 등록하지 않음
    if (findUserByEmail != null) {
      msg.put("err", "이미 등록된 이메일입니다.");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
    }
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
    UUID user = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.debug("User uuid = {} ", user);
    Map<String, Object> testData = new HashMap<>();
    testData.put("msg","USER TEST COMPLETE");
    return ResponseEntity.ok(testData);
  }
  @CrossOrigin
  @GetMapping("/test/timed")
  public ResponseEntity<?> testUserTimed() throws Exception{
    log.debug("Wait 0.5 seconds");
    Thread.sleep(500);
    Map<String, String> response = new HashMap<>();
    response.put("msg","Waited 0.5 seconds");
    return ResponseEntity.ok(response);
  }


}
