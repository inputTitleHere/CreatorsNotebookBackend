package com.creators.notebook.backend.user.model.repository;

import com.creators.notebook.backend.user.model.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  public UserEntity findByUserEmail(String email);

  public UserEntity findByUserUuid(UUID id);
}
