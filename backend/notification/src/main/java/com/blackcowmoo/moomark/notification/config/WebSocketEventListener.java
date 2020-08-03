package com.blackcowmoo.moomark.notification.config;

import com.blackcowmoo.moomark.notification.model.MessageType;
import com.blackcowmoo.moomark.notification.model.NotificationMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    logger.info("Received a new web socket connection");
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if (username != null) {
      logger.info("User Disconnected : " + username);

      NotificationMessage notificationMessage = new NotificationMessage();
      notificationMessage.setMessageType(MessageType.TOPIC);
      notificationMessage.setMessage(username);

      messagingTemplate.convertAndSend("/topic/public", notificationMessage);
    } else {
      logger.info("User Disconnected : " + "guest");

      NotificationMessage notificationMessage = new NotificationMessage();
      notificationMessage.setMessageType(MessageType.TOPIC);
      notificationMessage.setMessage("guest");

      messagingTemplate.convertAndSend("/topic/public", notificationMessage);
    }
  }
}
