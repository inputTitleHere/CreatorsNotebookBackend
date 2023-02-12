package com.creators.notebook.backend.instance.model.repository;

import com.creators.notebook.backend.instance.model.data.InstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstanceRepository extends JpaRepository<InstanceEntity, UUID> {

}
