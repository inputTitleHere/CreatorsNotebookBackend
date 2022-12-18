package com.creators.notebook.backend.user.model.data;

import javax.persistence.AttributeConverter;

public class UserPrivilegeEnumConverter implements AttributeConverter<UserPrivilegeEnum,String> {
  @Override
  public String convertToDatabaseColumn(UserPrivilegeEnum attribute) {
    return attribute.toString();
  }

  @Override
  public UserPrivilegeEnum convertToEntityAttribute(String dbData) {
    return UserPrivilegeEnum.valueOf(dbData);
  }
}
