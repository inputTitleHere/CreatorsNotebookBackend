package com.creators.notebook.backend.user.model.data;

import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Database : Postgres
 * Schema   : public
 * Table    : User
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties("userTeamEntities")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_uuid")
  private UUID userUuid;

  @Column(name = "user_email", nullable = false, unique = true)
  private String userEmail;
  @Column(name = "user_password", nullable = false)
  private String userPassword;

  @Column(name = "user_Name", nullable = false, length = 30)
  private String userName;

  @Column(name = "user_privilege", columnDefinition = "char(2) CHECK (user_privilege in ('AD','T0','T1')) default 'T0'")
  @Convert(converter = UserPrivilegeEnumConverter.class)
  private UserPrivilegeEnum userPrivilegeEnum;


  @Column(name = "user_joined_at", columnDefinition = "timestamp with time zone default current_timestamp")
  private LocalDateTime userJoinedAt;

  @Column(name = "user_deleted_at", columnDefinition = "timestamp with time zone")
  private LocalDateTime userDeletedAt;

  // MappedBy는 연결시킬 다른 엔티티의 변수명(java)를 지칭.
  @JsonManagedReference
  @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
  private List<UserTeamEntity> userTeamEntities = new ArrayList<>();


  /**
   * UserDTO to UserEntity Converter as a Constructor
   *
   * @param UserDTO userDto
   */
  public UserEntity(UserDTO userDTO) {
    this.userPassword = userDTO.getUserPassword();
    this.userName = userDTO.getUserName();
    this.userPrivilegeEnum = userDTO.getUserPrivilegeEnum();
    this.userEmail = userDTO.getUserEmail();
    this.userJoinedAt = userDTO.getUserJoinedAt();
    this.userDeletedAt = userDTO.getUserDeletedAt();
  }

}
