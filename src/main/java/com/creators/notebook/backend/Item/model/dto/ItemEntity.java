package com.creators.notebook.backend.Item.model.dto;

import com.fasterxml.jackson.annotation.JsonTypeId;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name="item")
@TypeDef(name = "json", typeClass = JsonType.class)
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "item_uuid", columnDefinition = "uuid")
  private UUID itemUuid;

  @Type(type = "json")
  @Column(name = "item_data", columnDefinition = "jsonb")
  private Map<String ,Object> itemData;


}
