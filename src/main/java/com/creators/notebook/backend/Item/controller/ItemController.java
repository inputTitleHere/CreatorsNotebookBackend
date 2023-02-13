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
    try {
      ItemEntity itemEntity = itemService.createItem(new ItemEntity(itemDto));
      itemDto.setItemUuid(itemEntity.getItemUuid());
      return ResponseEntity.ok(itemDto);
    } catch (NoItemException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{itemUuid}")
  public ResponseEntity<?> getItem(@PathVariable("itemUuid") UUID itemUuid) {
    ItemEntity itemEntity = itemService.findById(itemUuid);
    ItemDto itemDto = ItemDto.builder()
            .itemUuid(itemEntity.getItemUuid())
            .itemTypeString(itemEntity.getItemTypeEnum().toString())
            .itemData(itemEntity.getItemData())
            .build();

    return ResponseEntity.ok(itemDto);
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto) {
    try {
      itemService.updateItem(new ItemEntity(itemDto));
      return ResponseEntity.ok().build();
    } catch (NoItemException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteItem(@RequestBody ItemDto itemDto) {
    try {
      itemService.deleteItem(new ItemEntity(itemDto));
      return ResponseEntity.ok().build();
    } catch (NoItemException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
