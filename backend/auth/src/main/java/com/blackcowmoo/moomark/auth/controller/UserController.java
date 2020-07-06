package com.blackcowmoo.moomark.auth.controller;

import javax.servlet.http.HttpSession;

import com.blackcowmoo.moomark.auth.model.dto.SessionUser;
import com.blackcowmoo.moomark.auth.model.dto.UserDto;
import com.blackcowmoo.moomark.auth.service.UserService;
import com.blackcowmoo.moomark.auth.util.ModelMapperUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final HttpSession httpSession;

  private SessionUser getSessionUser() {
    return (SessionUser) httpSession.getAttribute("user");
  }

  private long getSessionUserId() {
    return getSessionUser().getId();
  }

  @GetMapping
  public UserDto getUserInfo() {
    return ModelMapperUtils.getModelMapper().map(userService.getUserById(getSessionUserId()), UserDto.class);
  }

  @PutMapping
  public ResponseEntity<Boolean> modUserInfo(@RequestBody String nickname) {

    return ResponseEntity.ok(userService.updateUserNickname(getSessionUserId(), nickname));
  }
}
