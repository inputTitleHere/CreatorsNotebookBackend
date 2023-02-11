package com.creators.notebook.backend.Item.model.data;

import javax.persistence.AttributeConverter;

public class ItemTypeEnumConverter implements AttributeConverter<ItemTypeEnum, String> {
  @Override
  public String convertToDatabaseColumn(ItemTypeEnum attribute) {
    return attribute.toString();
  }

  @Override
  public ItemTypeEnum convertToEntityAttribute(String dbData) {
    return ItemTypeEnum.valueOf(dbData);
  }
}
