package com.creators.notebook.backend.instance.model.data;

import com.creators.notebook.backend.Item.model.data.ItemDto;
import com.creators.notebook.backend.page.model.data.PageDto;
import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstanceDto {
  private long instanceId;
  private ItemDto itemDto;
  private PageDto pageDto;
  private Map<String, Object> instanceData;
}
