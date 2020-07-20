package com.blackcowmoo.moomark.auth.service;

import java.util.Optional;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.entity.User;
import com.blackcowmoo.moomark.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Profile("production")
public class OAuhUserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;

  @Override
  public User getUserById(long userId) {
    return userRepository.getOne(userId);
  }

  @Override
  public void updateUser(User user) {
    userRepository.save(user);
  }

  @Override
  public boolean updateUserNickname(long userId, String nickname) {
    try {
      User willUpdateUser = userRepository.getOne(userId);
      willUpdateUser.updateNickname(nickname);
      updateUser(willUpdateUser);
    } catch (Exception e) {
      log.error(e);
      return false;
    }
    return true;
  }

  @Override
  public User signUp(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> login(String email, String password, AuthProvider authProvider) {
    return userRepository.findByEmailAndAuthProvider(email, authProvider);
  }

  public User loginOrSignUp(User user) {
    Optional<User> newOrExistUser = login(user.getEmail(), null, user.getAuthProvider());
    return newOrExistUser.orElse(signUp(user));
  }

}
