package com.blackcowmoo.moomark.auth.service;

import java.util.Optional;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.entity.User;
import com.blackcowmoo.moomark.auth.util.EnvironmentUtil;

public interface UserService {

  User getUserById(long id);

  boolean updateUserNickname(long userId, String nickname);

  void updateUser(User user);

  Optional<User> login(String email, String password, AuthProvider authProvider);

  User signUp(User user);

  default String getEnv() {
    return EnvironmentUtil.getActiveProfile();
  }
}
