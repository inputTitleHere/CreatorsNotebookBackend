package com.creators.notebook.backend.user.controller;

import com.creators.notebook.backend.user.model.data.UserDTO;
import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.data.UserPrivilegeEnum;
import com.creators.notebook.backend.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor // 이것을 아래의 private final 필드와 결합하여 자동으로 spring framework가 넣어주게 한다.
public class UserController {

  private final UserService userService;

  /**
   * Id, password를 받는다.
   * 아이디가 존재하고 비밀번호가 맞는다면 true를 반환. 그 외의 경우 false 반환.
   * @param userDTO
   * @return boolean true or false
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
    boolean result = userService.login(new UserEntity(userDTO));
    // 비밀번호가 맞으면. JWT 발급.
    if(result) return ResponseEntity.status(200).body("Login Success");
    else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failure");
  }

  /**
   *
   * @param userDTO
   * @return
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
    log.debug("User DTO = {}",userDTO);
    UserEntity ue = new UserEntity(userDTO);
    log.debug("User Entity = {}", ue);
    boolean result = false;
    try{
      result = userService.register(ue);
    }catch (Exception e){
      log.error(e.getMessage());
      Map<String, String> msg = new HashMap<>();
      msg.put("error", "계정을 생성할 수 없습니다.");
      msg.put("msg",e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }
    return ResponseEntity.ok(result);
  }

}
