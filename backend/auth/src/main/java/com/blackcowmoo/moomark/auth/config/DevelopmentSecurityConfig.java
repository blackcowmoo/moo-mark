package com.blackcowmoo.moomark.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.Role;
import com.blackcowmoo.moomark.auth.model.dto.SessionUser;
import com.blackcowmoo.moomark.auth.model.entity.User;
import com.blackcowmoo.moomark.auth.repository.UserRepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Profile("development")
public class DevelopmentSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserRepository userRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/anyone", "login/*").permitAll()
        .antMatchers("/api/v1/**").hasRole("GUEST").anyRequest().authenticated().and().formLogin()
        .defaultSuccessUrl("/api/v1/user").successHandler(new TestLoginSuccessHandler()).and().logout();
  }

  // @Autowired
  // public void configureGlobal(AuthenticationManagerBuilder auth) throws
  // Exception {
  // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
  // }
  class TestLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
      // TODO Auto-generated method stub
      HttpSession session = request.getSession();
      User user = User.builder().name("테스터").email("test@test.com").picture(null).role(Role.GUEST)
          .authProvider(AuthProvider.EMPTY).build();
      user = userRepository.save(user);
      session.setAttribute("user", new SessionUser(user));
    }
  }
}
