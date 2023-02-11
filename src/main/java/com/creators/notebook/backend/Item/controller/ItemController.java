package com.creators.notebook.backend.Item.controller;

import com.creators.notebook.backend.Item.model.data.ItemDto;
import com.creators.notebook.backend.Item.model.data.ItemEntity;
import com.creators.notebook.backend.Item.model.data.ItemTypeEnum;
import com.creators.notebook.backend.Item.model.service.ItemService;
import com.creators.notebook.backend.exception.NoItemException;
import com.creators.notebook.backend.project.model.data.ProjectEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  @PostMapping("/new")
  public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) {
    ItemEntity itemEntity = itemService.createItem(
            ItemEntity.builder()
                    .itemData(itemDto.getItemData())
                    .itemTypeEnum(ItemTypeEnum.valueOf(itemDto.getItemTypeString()))
                    .projectEntity(
                            ProjectEntity.builder().projectUuid(itemDto.getProjectDto().getProjectUuid()).build()
                    )
                    .build()
    );

    return ResponseEntity.ok(itemEntity);
  }

  @GetMapping("/{itemUuid}")
  public ResponseEntity<?> getItem(@PathVariable("itemUuid") UUID itemUuid) {
    ItemEntity itemEntity = itemService.findById(itemUuid);
    return ResponseEntity.ok(null);
  }

  @PutMapping("/{itemUuid}")
  public ResponseEntity<?> updateItem(@PathVariable("itemUuid") UUID itemUuid, @RequestBody ItemDto itemDto) {
    try {
      itemService.updateItem(new ItemEntity(itemDto));
      return ResponseEntity.ok(null);
    }catch (NoItemException e){
      return ResponseEntity.badRequest().build();
    }
  }


}
