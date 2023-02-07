package com.creators.notebook.backend.Item.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@Table(name="item")
@DynamicUpdate
@DynamicInsert
public class ItemDto {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "item_uuid", columnDefinition = "uuid")
  private UUID itemUuid;




}
