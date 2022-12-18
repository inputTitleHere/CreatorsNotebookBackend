package com.creators.notebook.backend.user.model.service;


import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;

  @Override
  public UserEntity findById(String id) {
    return userRepository.findById(id).orElse(null);
  }
}
