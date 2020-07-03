package com.blackcowmoo.moomark.service;

import com.blackcowmoo.moomark.model.entity.User;
import com.blackcowmoo.moomark.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public User getUserById(long userId) {
    return userRepository.getOne(userId);
  }

  public boolean updateUserNickname(long userId, String nickname) {
    try {
      User willUpdateUser = userRepository.getOne(userId);
      willUpdateUser.updateNickname(nickname);
      userRepository.save(willUpdateUser);
    } catch (Exception e) {
      log.error(e);
      return false;
    }
    return true;
  }
}
