package com.creators.notebook.backend.user.controller;

import com.creators.notebook.backend.user.model.data.UserDTO;
import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor // 이것을 아래의 private final 필드와 결합하여 자동으로 spring framework가 넣어주게 한다.
public class UserController {

  private final UserService userService;
  private final BCryptPasswordEncoder bcrypt;

  /**
   * Id, password를 받는다.
   * 아이디가 존재하고 비밀번호가 맞는다면 true를 반환. 그 외의 경우 false 반환.
   * @param userDTO
   * @return boolean true or false
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
    String id = userDTO.getUserId();
    UserEntity user = userService.findById(id);
    // 아이디가 없으면 false 반환
    if(user==null){
      return ResponseEntity.ok(false);
    }
    // 비밀번호가 틀리면 false 반환
    boolean pwdMatch = bcrypt.matches(userDTO.getUserPassword(), user.getUserPassword());
    if(!pwdMatch){
      return ResponseEntity.ok(false);
    }
    // 비밀번호가 맞으면. JWT 발급.
    return ResponseEntity.status(200).body("Login Success");
  }

  /**
   *
   * @param userDTO
   * @return
   */
  @PostMapping("/Register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
    



    return ResponseEntity.ok("Bleh");
  }

}
