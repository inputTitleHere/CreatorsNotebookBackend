package com.creators.notebook.backend.user.model.data;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

  private UUID userUuid;
  private String userPassword;
  private String userName;
  private UserPrivilegeEnum userPrivilegeEnum;
  private String userEmail;
  private LocalDateTime userJoinedAt;
  private LocalDateTime userDeletedAt;
  private String token;
}
