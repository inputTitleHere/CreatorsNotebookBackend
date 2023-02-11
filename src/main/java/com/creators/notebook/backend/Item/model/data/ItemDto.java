package com.creators.notebook.backend.Item.model.data;

import com.creators.notebook.backend.project.model.data.ProjectDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDto {
  private UUID itemUuid;
  private ItemTypeEnum itemTypeEnum;
  private String itemTypeString;
  private Map<String, Object> itemData;
  private ProjectDto projectDto;
}
