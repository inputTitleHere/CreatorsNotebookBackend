package com.creators.notebook.backend.team.data;

import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "team")
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "team_uuid")
  private UUID teamUuid;

  @Column(name = "team_name")
  private String teamName;

  /**
   * 개인용 : true
   * 팀용 : false
   * 초기 계정 생성시 true으로 설정하여 신규 팀을 생성한다. - 초대 불가.
   * 이후 팀 생성 버튼으로는 false으로 생성한다. - 초대 가능.
   */
  @Column(name = "team_private")
  private boolean teamPrivate;

  @Column(name = "team_description")
  private String teamDescription;

  // User에 대한 논리적 외래키 존재. 물리적으로는 만들지 않겠다.
  @OneToMany(mappedBy = "projectId", fetch = FetchType.LAZY)
  private List<ProjectEntity> projects = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "teamUuid", fetch = FetchType.LAZY)
  private List<UserTeamEntity> userTeam = new ArrayList<>();



}
