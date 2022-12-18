package com.creators.notebook.backend.user.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private String userId;
  private String userPassword;
  private String userName;
  private UserPrivilege userPrivilege;
  private String userEmail;
  private LocalDateTime userJoinedAt;
  private LocalDateTime userDeletedAt;

  /**
   * 이 변수는 올바르게 웹사이트로 진행한 접근인지 확인하는 변수.
   * 이 변수가 없으면 올바르지 않은 접근으로 판단.
   */
  private boolean isProper;

}
