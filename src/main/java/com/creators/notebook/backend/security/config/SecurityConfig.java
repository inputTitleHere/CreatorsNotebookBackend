package com.creators.notebook.backend.security.config;

import com.creators.notebook.backend.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtTokenProvider jwtTokenProvider;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .httpBasic().disable()
            .cors().disable()
            .csrf().disable()
            .authorizeRequests()
              .antMatchers("/").permitAll()
              .antMatchers("/user/**").anonymous()
              .antMatchers("/img/**","/lib/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterAfter(jwtAuthenticationFilter, CorsFilter.class)
            .build();
  }

//  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers();
//    return (web) -> web.ignoring().antMatchers("/**");
  }

  /**
   * CORS의 설정을 spring security에게 적용한다.
   *
   * @return
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("*");
    config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
    config.addAllowedHeader("*");
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  /**
   * Password Encoder Bcrypt
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
