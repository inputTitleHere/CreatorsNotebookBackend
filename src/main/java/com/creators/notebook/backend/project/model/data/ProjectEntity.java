package com.creators.notebook.backend.project.model.data;

import com.creators.notebook.backend.team.data.TeamEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "project")
@Getter
@Setter
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

    @Column(name = "project_visibility")
    private ProjectVisibility projectVisibility;

    @ManyToOne
    @JoinColumn(name = "team_uuid",referencedColumnName = "team_uuid",foreignKey = @ForeignKey(name = "fk_team_uuid"))
    private TeamEntity teamEntity;

}
