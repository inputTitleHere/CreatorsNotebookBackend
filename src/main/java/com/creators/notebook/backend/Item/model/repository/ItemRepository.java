package com.creators.notebook.backend.Item.model.repository;

import com.creators.notebook.backend.Item.model.data.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

}
