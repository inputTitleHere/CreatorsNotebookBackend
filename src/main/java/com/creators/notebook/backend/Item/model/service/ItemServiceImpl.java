package com.creators.notebook.backend.Item.model.service;

import com.creators.notebook.backend.Item.model.data.ItemEntity;
import com.creators.notebook.backend.Item.model.data.ItemTypeEnum;
import com.creators.notebook.backend.Item.model.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

  private ItemRepository itemRepository;

  @Override
  public ItemEntity createItem(ItemEntity itemEntity) {
    ItemEntity result = itemRepository.save(itemEntity);
    return result;
  }

  @Override
  public ItemEntity findById(UUID itemUuid) {
    ItemEntity result = itemRepository.findById(itemUuid).orElse(null);
    return result;
  }

  @Override
  public void updateItem(ItemEntity itemEntity) {
    itemRepository.save(itemEntity);
  }
}
