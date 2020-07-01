package com.blackcowmoo.moomark.config.auth;

import java.util.Map;

import com.blackcowmoo.moomark.model.AuthProvider;
import com.blackcowmoo.moomark.model.Role;
import com.blackcowmoo.moomark.model.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String name;
  private String email;
  private String picture;
  private AuthProvider authProvider;

  @Builder
  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
      String picture, AuthProvider authProvider) {
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.authProvider = authProvider;
  }

  public static OAuthAttributes of(AuthProvider provider, String registrationId, String userNameAttributeName,
      Map<String, Object> attributes) {
    return OAuthAttributes.builder().name((String) attributes.get("name")).email((String) attributes.get("email"))
        .picture((String) attributes.get("picture")).attributes(attributes).nameAttributeKey(userNameAttributeName)
        .authProvider(provider).build();
  }

  public User toEntity() {
    return User.builder().name(name).email(email).picture(picture).role(Role.GUEST).authProvider(this.authProvider)
        .build();
  }
}
