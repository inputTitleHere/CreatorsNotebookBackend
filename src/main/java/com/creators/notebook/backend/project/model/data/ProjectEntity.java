package com.creators.notebook.backend.project.model.data;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "cndb",name="Project")
@Data
@DynamicUpdate
@DynamicInsert
public class ProjectEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="proj_id",columnDefinition = "uuid")
  private UUID projId;

  @Column(name="proj_name", nullable = false)
  private String projName;

  @Column(name = "proj_created_at", columnDefinition = "timestamp with time zone default current_timestamp")
  private LocalDateTime projCreatedAt;

  @Column(name = "proj_updated_at", columnDefinition = "timestamp with time zone")
  private LocalDateTime projUpdatedAt;



}
