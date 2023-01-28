package com.creators.notebook.backend.user.model.service;

import com.creators.notebook.backend.user.model.data.UserEntity;

import java.util.UUID;

public interface UserService {
  public UserEntity findByUuid(UUID id);

  public UserEntity register(UserEntity ue);

  UserEntity login(UserEntity userEntity);

  UserEntity findByEmail(String userEmail);

  UserEntity loadDashboard(UUID id);
}
