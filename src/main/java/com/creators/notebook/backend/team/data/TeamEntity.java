package com.creators.notebook.backend.team.data;

import com.creators.notebook.backend.bridge.userteam.UserTeamEntity;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
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

  @OneToMany(mappedBy = "projectId")
  private List<ProjectEntity> projects = new ArrayList<>();

  @OneToMany(mappedBy = "teamEntity")
  private List<UserTeamEntity> userTeam = new ArrayList<>();



}
