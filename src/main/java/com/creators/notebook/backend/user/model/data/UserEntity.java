package com.creators.notebook.backend.user.model.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Database : Postgres
 * Schema   : public
 * Table    : User
 */
@Entity
@Table(schema = "public",name = "User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  @Id
  @Column(name = "user_id", length = 30)
  private String userId;

  @Column(name = "user_password", nullable = false)
  private String userPassword;

  @Column(name = "user_Name", nullable = false, length = 30)
  private String userName;

  @Column(name = "user_privilege", nullable = false, columnDefinition = "char(2) CHECK (user_privilege in ('AD','T0','T1')) default 'T0'")
  private UserPrivilege userPrivilege;

  @Column(name = "user_email", nullable = false)
  private String userEmail;

  @Column(name = "user_joined_at", columnDefinition = "timestamp with time zone default current_timestamp")
  private LocalDateTime userJoinedAt;

  @Column(name = "user_deleted_at", columnDefinition = "timestamp")
  private LocalDateTime userDeletedAt;

  /**
   * UserDTO to UserEntity Converter as a Constructor
   * @param UserDTO userDto
   */
  public UserEntity (UserDTO userDTO){
    this.userId=userDTO.getUserId();
    this.userPassword=userDTO.getUserPassword();
    this.userName=userDTO.getUserName();
    this.userPrivilege=userDTO.getUserPrivilege();
    this.userEmail=userDTO.getUserEmail();
    this.userJoinedAt=userDTO.getUserJoinedAt();
    this.userDeletedAt=userDTO.getUserDeletedAt();
  }

}
