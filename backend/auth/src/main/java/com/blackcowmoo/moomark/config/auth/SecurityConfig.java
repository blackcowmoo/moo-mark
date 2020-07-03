package com.blackcowmoo.moomark.config.auth;

import com.blackcowmoo.moomark.model.Role;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/anyone", "login/*").permitAll()
        .antMatchers("/api/v1/**").hasRole(Role.GUEST.name()).anyRequest().authenticated().and().logout()
        .logoutUrl("/logout").logoutSuccessUrl("/hello").and().oauth2Login().userInfoEndpoint()
        .userService(customOAuth2UserService).and().defaultSuccessUrl("/api/v1/user").failureUrl("/error/fail");
  }
}
