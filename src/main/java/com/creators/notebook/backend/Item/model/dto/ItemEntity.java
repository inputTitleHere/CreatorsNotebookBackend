package com.creators.notebook.backend.Item.model.dto;

import com.creators.notebook.backend.project.model.data.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonTypeId;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name="item")
@TypeDef(name = "json", typeClass = JsonType.class)
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "item_uuid", columnDefinition = "uuid")
  private UUID itemUuid;

  @Column(name = "item_type")
  private ItemTypeEnum itemTypeEnum;

  @Type(type = "json")
  @Column(name = "item_data", columnDefinition = "jsonb")
  private Map<String ,Object> itemData;

  @ManyToOne
  @JoinColumn(name="project_uuid",referencedColumnName = "project_uuid")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ProjectEntity projectEntity;

}
