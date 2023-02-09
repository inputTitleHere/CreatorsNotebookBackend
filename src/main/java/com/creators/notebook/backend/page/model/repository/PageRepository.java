package com.creators.notebook.backend.page.model.repository;

import com.creators.notebook.backend.page.model.data.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PageRepository extends JpaRepository<PageEntity, UUID> {

}
