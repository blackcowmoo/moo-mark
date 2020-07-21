// package com.blackcowmoo.moomark.notification;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.messaging.simp.stomp.StompSession;
// import org.springframework.messaging.simp.stomp.StompSessionHandler;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.web.socket.messaging.WebSocketStompClient;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = WebEnvironment.MOCK)
// public class WebSocketTest {

// @Autowired
// private WebSocketStompClient webSocketStompClient;

// @Test
// public void connectWebSocket() {
// StompSessionHandler sessionHandler = new CustmStompSessionHandler();
// StompSession stompSession = webSocketStompClient.connect("",
// sessionHandler).get();
// }
// }
