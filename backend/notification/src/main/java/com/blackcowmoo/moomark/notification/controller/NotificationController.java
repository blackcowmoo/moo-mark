package com.blackcowmoo.moomark.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
  @Autowired
  private SimpMessageSendingOperations messageTemplate;

  @MessageMapping("/message")
  @SendToUser("/queue/reply")
  public String sendNotification(@Payload String message) {
    // messageTemplate.convertAndSendToUser(message, "/queue/reply", "guest");
    return message;
  }
}
