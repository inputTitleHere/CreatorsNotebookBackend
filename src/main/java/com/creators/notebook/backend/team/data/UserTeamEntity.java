package com.creators.notebook.backend.team.data;

import com.creators.notebook.backend.team.data.TeamAuth;
import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.user.model.data.UserEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;


@Entity
@IdClass(UserTeamPk.class)
@Data
@Builder
@Table(name = "user_team")
@AllArgsConstructor
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class UserTeamEntity {
  @Id
  @Column(name = "user_uuid")
  private UUID userUuid;

  @Id
  @Column(name = "team_uuid")
  private  UUID teamUuid;

  // @ForeignKey는 외래키의 이름을 지정한다.
  @ManyToOne
  @JoinColumn(name = "user_uuid", foreignKey = @ForeignKey(name = "fk_user_uuid"),insertable = false, updatable = false)
  private UserEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "team_uuid", foreignKey = @ForeignKey(name="fk_team_uuid"),insertable = false, updatable = false)
  private TeamEntity teamEntity;

  // Creator, Admin, User
  @Column(name = "team_auth", columnDefinition = "varchar(10)")
  private TeamAuth teamAuth;


}
