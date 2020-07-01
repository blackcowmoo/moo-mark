package com.blackcowmoo.moomark.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.blackcowmoo.moomark.model.AuthProvider;
import com.blackcowmoo.moomark.model.Role;
import com.blackcowmoo.moomark.model.entity.User;

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
    List<User> userList = userRepository.findAll();

    // then
    User user = userList.get(0);
    assertThat(user.getAuthProvider()).isEqualTo(authProvider);
    assertThat(user.getName()).isEqualTo(name);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getRole()).isEqualTo(role);
  }
}
