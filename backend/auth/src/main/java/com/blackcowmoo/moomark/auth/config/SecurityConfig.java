package com.blackcowmoo.moomark.auth.config;

import com.blackcowmoo.moomark.auth.model.Role;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Profile({ "production", "default" })
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
