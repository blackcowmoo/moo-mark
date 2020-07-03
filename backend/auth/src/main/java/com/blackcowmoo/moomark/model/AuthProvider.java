package com.blackcowmoo.moomark.model;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthProvider {

  GOOGLE("Google", "구글"), GITHUB("GitHub", "깃허브"), KAKAO("PROVIDER_KAKAO", "카카오"), NAVER("PROVIDER_NAVER", "네이버"),
  EMPTY("No Data", "없음");

  private final String key;
  private final String value;

  public static AuthProvider getAuthProviderValue(String provider) {
    return Arrays.stream(AuthProvider.values()).filter(authProvider -> authProvider.hasAuthProvider(provider)).findAny()
        .orElse(EMPTY);
  }

  public boolean hasAuthProvider(String provider) {
    return key.equals(provider);
  }
}
