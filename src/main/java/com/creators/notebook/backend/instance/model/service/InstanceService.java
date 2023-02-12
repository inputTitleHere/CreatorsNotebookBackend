package com.creators.notebook.backend.instance.model.service;

import com.creators.notebook.backend.instance.model.data.InstanceEntity;

import java.util.UUID;

public interface InstanceService {
  InstanceEntity save(InstanceEntity instanceEntity);

  InstanceEntity findById(UUID instanceUuid);
}
