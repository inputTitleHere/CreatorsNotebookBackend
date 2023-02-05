package com.creators.notebook.backend.team.repository;

import com.creators.notebook.backend.team.data.UserTeamEntity;
import com.creators.notebook.backend.team.data.UserTeamPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeamEntity, UserTeamPk> {
}
