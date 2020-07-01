package com.blackcowmoo.moomark.controller;

import javax.servlet.http.HttpSession;

import com.blackcowmoo.moomark.config.auth.dto.SessionUser;
import com.blackcowmoo.moomark.model.entity.User;
import com.blackcowmoo.moomark.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping
  public User getUserInfo(HttpSession session) {
    return userRepository.getOne(((SessionUser) session.getAttribute("user")).getId());
  }

  @PutMapping
  public ResponseEntity<String> modUserInfo(@RequestBody String nickName, HttpSession session) {
    try {
      User willUpdateUser = userRepository.getOne(((SessionUser) session.getAttribute("user")).getId());
      willUpdateUser.updateNickname(nickName);
      userRepository.save(willUpdateUser);
    } catch (Exception e) {
      return new ResponseEntity<>("Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>("ok", HttpStatus.OK);
  }
}
