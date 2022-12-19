package com.creators.notebook.backend.user.model.service;

import com.creators.notebook.backend.user.model.data.UserEntity;

public interface UserService {
  public UserEntity findById(String id);

  public boolean register(UserEntity ue);

  UserEntity login(UserEntity userEntity);
}
