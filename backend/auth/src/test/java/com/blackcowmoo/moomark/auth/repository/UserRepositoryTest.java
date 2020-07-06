package com.blackcowmoo.moomark.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NoSuchElementException;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.Role;
import com.blackcowmoo.moomark.auth.model.entity.User;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @After
  public void cleanup() {
    userRepository.deleteAll();
  }

  @Test
  public void userRepositoryTest() {
    // given
    String name = "tester";
    String email = "tester@test.com";
    Role role = Role.GUEST;
    AuthProvider authProvider = AuthProvider.GOOGLE;

    userRepository.save(User.builder().name(name).email(email).authProvider(authProvider).role(role).build());

    // when

    // then
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException());
    assertThat(user.getAuthProvider()).isEqualTo(authProvider);
    assertThat(user.getName()).isEqualTo(name);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getRole()).isEqualTo(role);
  }
}
