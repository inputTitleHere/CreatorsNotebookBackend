package com.creators.notebook.backend.page.model.dto;


import com.creators.notebook.backend.project.model.data.ProjectEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "page")
@Getter
@Setter
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "page_uuid", columnDefinition = "uuid")
    private UUID pageUuid;

    @Column(name = "page_type")
    private PageTypeEnum pageTypeEnum;

    @ManyToOne
    @JoinColumn(name = "project_uuid", referencedColumnName = "project_id", foreignKey = @ForeignKey(name = "fk_project_uuid"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity projectEntity;




}
