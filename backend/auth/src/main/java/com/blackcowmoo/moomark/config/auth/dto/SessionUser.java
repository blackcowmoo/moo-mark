package com.blackcowmoo.moomark.config.auth.dto;

import java.io.Serializable;

import com.blackcowmoo.moomark.model.entity.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SessionUser implements Serializable {
  private static final long serialVersionUID = 1L;
  private long id;
  private String name;
  private String email;
  private String picture;
  private String nickname;

  public SessionUser(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.picture = user.getPicture();
    this.nickname = user.getNickname();
  }

}
