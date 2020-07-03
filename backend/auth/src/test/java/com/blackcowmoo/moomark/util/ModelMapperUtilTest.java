package com.blackcowmoo.moomark.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import com.blackcowmoo.moomark.model.AuthProvider;
import com.blackcowmoo.moomark.model.Role;
import com.blackcowmoo.moomark.model.dto.UserDto;
import com.blackcowmoo.moomark.model.entity.User;
import com.blackcowmoo.moomark.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ModelMapperUtilTest {

  @Autowired
  UserRepository userRepository;
  String name = "tester";
  String email = "tester@test.com";
  Role role = Role.GUEST;
  AuthProvider authProvider = AuthProvider.GOOGLE;

  @Before
  public void cleanup() {

    userRepository.save(User.builder().name(name).email(email).authProvider(authProvider).role(role).build());

  }

  @Test
  public void modelMapperUtilTest() {
    // given
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException());
    UserDto userDto = ModelMapperUtils.getModelMapper().map(user, UserDto.class);

    assertThat(user.getAuthProvider()).isEqualTo(userDto.getAuthProvider());
    assertThat(user.getName()).isEqualTo(userDto.getName());
    assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
    assertThat(user.getRole()).isEqualTo(userDto.getRole());
  }
}
