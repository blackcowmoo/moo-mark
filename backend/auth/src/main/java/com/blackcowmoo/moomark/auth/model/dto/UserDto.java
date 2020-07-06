package com.blackcowmoo.moomark.auth.model.dto;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Setter
@ToString(exclude = "id")
public class UserDto {
  private long id;
  private String name;
  private String email;
  private String picture;
  private String nickname;
  private Role role;
  private AuthProvider authProvider;

}
