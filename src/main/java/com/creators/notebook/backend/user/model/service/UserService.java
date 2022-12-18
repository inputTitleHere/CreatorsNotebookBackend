package com.creators.notebook.backend.user.model.service;

import com.creators.notebook.backend.user.model.data.UserEntity;

public interface UserService {
  UserEntity findById(String id);
}
