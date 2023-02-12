package com.creators.notebook.backend.Item.model.service;

import com.creators.notebook.backend.Item.model.data.ItemEntity;

import java.util.UUID;

public interface ItemService {
  ItemEntity createItem(ItemEntity itemEntity);

  ItemEntity findById(UUID itemUuid);

  void updateItem(ItemEntity itemEntity);

  void deleteItem(ItemEntity itemEntity);
}
