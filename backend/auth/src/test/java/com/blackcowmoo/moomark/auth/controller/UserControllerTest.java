package com.blackcowmoo.moomark.auth.controller;

import javax.persistence.NoResultException;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.Role;
import com.blackcowmoo.moomark.auth.model.entity.User;
import com.blackcowmoo.moomark.auth.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
  // TODO 추후 세큐리티 컨택스트 로딩후 테스트
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserRepository userRepository;

  @Before
  public void addUser() throws Exception {
    userRepository.save(User.builder().name("tester").email("test@test.com").authProvider(AuthProvider.GOOGLE)
        .picture("https://1h3.test.com").role(Role.GUEST).build());
  }

  @Test
  @WithMockUser(roles = "GUEST", username = "tester")
  public void userRole() throws Exception {
    userRepository.findByEmail("test@test.com").orElseThrow(() -> new NoResultException());
  }
}
