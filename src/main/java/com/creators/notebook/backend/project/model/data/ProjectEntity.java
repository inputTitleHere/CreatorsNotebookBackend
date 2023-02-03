package com.creators.notebook.backend.project.model.data;

import com.creators.notebook.backend.team.data.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "project")
@Data
@Builder
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", columnDefinition = "uuid")
    private UUID projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_created_at", columnDefinition = "timestamp with time zone default current_timestamp")
    private LocalDateTime projectCreatedAt;

    @Column(name = "project_updated_at", columnDefinition = "timestamp with time zone default current_timestamp")
    private LocalDateTime projectUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "team_uuid")
    private TeamEntity teamEntity;

}
