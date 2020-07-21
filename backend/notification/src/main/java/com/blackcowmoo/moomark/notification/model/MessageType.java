package com.blackcowmoo.moomark.notification.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
  TOPIC("TOPIC","주제"),
  RECOMMEND("RECOMMEND", "추천");
  
  private final String key;
  private final String title;
}
