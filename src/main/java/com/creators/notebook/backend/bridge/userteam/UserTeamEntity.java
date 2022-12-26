package com.creators.notebook.backend.bridge.userteam;

import com.creators.notebook.backend.team.data.TeamEntity;
import com.creators.notebook.backend.user.model.data.UserEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "user_team")
@DynamicUpdate
@DynamicInsert
public class UserTeamEntity {
  @Id
  @Column(name = "user_team_bridge_no")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long userTeamBridgeNo;

  // @ForeignKey는 외래키의 이름을 지정한다.
  @ManyToOne
  @JoinColumn(name = "user_uuid", foreignKey = @ForeignKey(name = "fk_user_uuid"), referencedColumnName = "user_uuid")
  private UserEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "team_uuid")
  private TeamEntity teamEntity;

  @Column(name = "team_auth", columnDefinition = "varchar(10)")
  private TeamAuth teamAuth;



}
