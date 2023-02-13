package com.creators.notebook.backend.instance.model.service;

import com.creators.notebook.backend.instance.model.data.InstanceEntity;
import com.creators.notebook.backend.instance.model.repository.InstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class InstanceServiceImpl implements InstanceService{
  private final InstanceRepository instanceRepository;
  @Override
  public InstanceEntity save(InstanceEntity instanceEntity) {
    return instanceRepository.save(instanceEntity);
  }

  @Override
  public InstanceEntity findById(UUID instanceUuid) {
    return instanceRepository.findById(instanceUuid).orElse(null);
  }

  @Override
  public void updateInstance(InstanceEntity instanceEntity) {
    instanceRepository.save(instanceEntity);
  }

  @Override
  public void deleteInstance(UUID instanceUuid) {
    instanceRepository.deleteById(instanceUuid);
  }
}
