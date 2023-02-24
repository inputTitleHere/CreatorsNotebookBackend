package com.creators.notebook.backend.filter;

import com.creators.notebook.backend.security.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  /**
   * 매번 요청이 들어올 때마다 JWT를 확인한다.
   *
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // 요청에서 토큰 추출
    try {
      String token = parseBearerToken(request);
      log.debug("JWT Filter is running");
      if (token != null && !token.equalsIgnoreCase("null")) {
        String parsedToken = jwtTokenProvider.validateTokenAndGetUserId(token);
        if (parsedToken != null) {
          UUID userId = UUID.fromString(parsedToken);
          log.debug("Authenticated User = {}", userId);
        // SecurityContextHolder에 등록.
          AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContext securityContext = SecurityContextHolder.getContext();
          securityContext.setAuthentication(authentication);
//        SecurityContextHolder.setContext(securityContext); // ThreadLocal에 저장된다.
        }
      }
    } catch (Exception e) {
      log.error("Failed to set user Authentication", e);
    }
    filterChain.doFilter(request, response);
  }

  /**
   * request에서 JWT토큰 추출.
   *
   * @param request
   * @return String BearerToken or null
   */
  private String parseBearerToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7); // "Bearer "(공백포함) 7글자 자르기.
    }
    return null;
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring("Bearer ".length());
    }
    return null;
  }

}
