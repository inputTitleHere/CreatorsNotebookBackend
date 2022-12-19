package com.creators.notebook.backend.securityConfig;

import com.creators.notebook.backend.user.model.data.UserEntity;
import com.creators.notebook.backend.user.model.data.UserPrivilegeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JwtTokenProvider {
  // 비밀키 -> TODO 나중에 환경변수 주입으로 변경?
  private static final String SECRET_KEY = "a;dlkfj23e0924ndiopq";
  // Token 키
  private final Key JWT_SECRET_KEY;
  // JWT 토큰 가용시간.(밀리초)
  private static final int JWT_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;


  public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.JWT_SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * 신규 JWT 토큰 생성 -> 교재참고(react.js, 스프링부트, aws으로 배우는 웹개발 101)
   * @param user
   * @return 새로이 생성한 String 형식의 JWT
   */
  public String createToken(UserEntity user){
    Date expireAt = new Date(new Date().getTime()+JWT_EXPIRATION_TIME);
    return Jwts.builder()
            .signWith(JWT_SECRET_KEY, SignatureAlgorithm.HS512)
            .setSubject(user.getUserId()) // payload에 들어갈 내용
            .setIssuer("Creators Notebook") // iss
            .setIssuedAt(new Date()) // iat
            .setExpiration(expireAt)
            .compact();
  }

  /**
   * 블로그 보고 따라하려고 시도한 부분. https://gksdudrb922.tistory.com/217
   * @param authentication
   * @return
   */
  public TokenInfo generateToken(Authentication authentication) {
    // 권한 가져오기
    String authorities = authentication.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    Date now = new Date();
    Date expireAt = new Date(now.getTime() + JWT_EXPIRATION_TIME);

    // Access Token
    String accessToken = Jwts.builder()
            .setSubject(authentication.getName())
            .claim("auth",authorities)
            .setExpiration(expireAt)
            .signWith(JWT_SECRET_KEY,SignatureAlgorithm.HS256)
            .compact();

    // Refresh Token
    String refreshToken = Jwts.builder()
            .setExpiration(expireAt)
            .signWith(JWT_SECRET_KEY,SignatureAlgorithm.HS256)
            .compact();

    return TokenInfo.builder()
            .grantType("Bearer")
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();

//    return Jwts.builder()
//            .setSubject((String) authentication.getPrincipal()) // 사용자.
//            .setIssuedAt(now)
//            .setExpiration(expireAt)
//            .signWith(JWT_SECRET_KEY, SignatureAlgorithm.HS256)
//            .compact();
  }

  /**
   * JWT유효성 검사.
   *
   * @param token
   * @return boolean result of parsed token
   */
  public boolean validateToken(String token) {
    log.debug("Validating JWT Token");
    try {
      JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(JWT_SECRET_KEY).build();
      jwtParser.parseClaimsJwt(token);
      return true;
    } catch (Exception e) {
      log.error("Invalid JWT token");
      return false;
    }
  }

  /**
   * JWT에서 유저이름 추출.
   * parseClaimsJwt가 base64로 디코딩 & 파싱. 헤더와 페이로드를 setSigningKey으로 넘어온 시크릿키와 token 서명 비교.
   * userId를 꺼내기 위해 getBody사용
   * @param token
   * @return 유저ID반환.
   */
  public String getUserIdFromJWT(String token) {
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(JWT_SECRET_KEY).build()
            .parseClaimsJwt(token)
            .getBody();
    return claims.getSubject();
  }

  /**
   * accessToken에서 Claim을 추출.
   * @param accessToken
   * @return
   */
  private Claims parseClaims(String accessToken){
    try{
      return Jwts.parserBuilder().setSigningKey(JWT_SECRET_KEY).build().parseClaimsJwt(accessToken).getBody();
    }catch(ExpiredJwtException e){
      return e.getClaims();
    }
  }

}
