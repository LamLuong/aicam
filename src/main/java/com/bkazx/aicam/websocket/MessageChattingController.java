package com.bkazx.aicam.websocket;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@AllArgsConstructor

public class MessageChattingController {

    @Autowired
    SimpMessagingTemplate simpleMessTemp;

    @MessageMapping("/health-check")
    @SendTo("/topic/health-check")
    public String healCheck(@Payload String message) throws Exception {
      log.info("receive message {}", message);
      Thread.sleep(1000);
      return "Server reply for: " + message;
    }

    @MessageMapping("/chat.private")
    public void forwardMessageToDestination(@Payload ChatMessage message, Principal user) throws Exception {
      log.info("chat.private receive message {}", message.getSendAt());
      simpleMessTemp.convertAndSendToUser(
                message.getName(),
                "/topic/messages",
                message.getMessage() + "hehehe"
        );
    }
}
