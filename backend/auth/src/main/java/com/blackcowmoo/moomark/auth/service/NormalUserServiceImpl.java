package com.blackcowmoo.moomark.auth.service;

import java.util.Optional;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.entity.User;
import com.blackcowmoo.moomark.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class NormalUserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User getUserById(long id) {
    // TODO Auto-generated method stub
    return userRepository.getOne(id);
  }

  @Override
  public Optional<User> login(String email, String password, AuthProvider authProvider) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User signUp(User user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void updateUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean updateUserNickname(long userId, String nickname) {
    // TODO Auto-generated method stub
    return false;
  }

}
