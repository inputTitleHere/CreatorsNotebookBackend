package com.creators.notebook.backend.page.model.data;


import com.creators.notebook.backend.exception.NoItemException;
import com.creators.notebook.backend.instance.model.data.InstanceEntity;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
@Table(name = "page")
public class PageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "page_uuid", columnDefinition = "uuid")
  private UUID pageUuid;

  @Column(name = "page_title", columnDefinition = "text")
  private String pageTitle;

//    페이지 타입 말고 사용자 마음대로 지정하도록 해보자.
//    @Column(name = "page_type")
//    private PageTypeEnum pageTypeEnum;


  @ManyToOne
  @JoinColumn(name = "project_uuid", referencedColumnName = "project_uuid", foreignKey = @ForeignKey(name = "fk_project_uuid"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ProjectEntity projectEntity;

  // Instance
  @JsonManagedReference
  @OneToMany(mappedBy = "instanceId", fetch = FetchType.LAZY)
  private List<InstanceEntity> instanceEntities;

  public PageEntity(PageDto pageDto) throws NoItemException {
    this.pageUuid = pageDto.getPageUuid();
    this.pageTitle = pageDto.getPageTitle();
    this.projectEntity = new ProjectEntity(pageDto.getProjectDto());
    this.instanceEntities = pageDto.getInstanceDtos().stream().map((dto) -> {
              try {
                return new InstanceEntity(dto);
              } catch (NoItemException e) {
                return null;
              }
            }
    ).collect(Collectors.toList());
  }


}


