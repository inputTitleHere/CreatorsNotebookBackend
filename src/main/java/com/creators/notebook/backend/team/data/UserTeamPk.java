package com.creators.notebook.backend.team.data;

import com.creators.notebook.backend.user.model.data.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserTeamPk implements Serializable {
  private UUID userUuid;
  private UUID teamUuid;
}
