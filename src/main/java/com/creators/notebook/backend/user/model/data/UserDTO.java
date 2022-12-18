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
  private UserPrivilegeEnum userPrivilegeEnum;
  private String userEmail;
  private LocalDateTime userJoinedAt;
  private LocalDateTime userDeletedAt;

}
