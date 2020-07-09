package com.blackcowmoo.moomark.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public class EnvironmentUtil {
  @Autowired
  private static Environment env;
  @Value("${spring.profiles.active}")
  private static String activeProfile;

  public static Environment getEnv() {
    return env;
  }

  public static String getActiveProfile() {
    return activeProfile;
  }
}
