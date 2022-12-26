package com.creators.notebook.backend.security.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * JWT의 Access Token과 Refresh Token을 담기 위한 DTO 객체.
 */
@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
  private String grantType;
  private String accessToken;
  private String refreshToken;
}
