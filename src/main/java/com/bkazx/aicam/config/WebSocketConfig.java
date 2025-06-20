package com.bkazx.aicam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.bkazx.aicam.websocket.CustomHandshakeHandler;
import com.bkazx.aicam.websocket.SecurityHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
      config.enableSimpleBroker("/topic");
      config.setApplicationDestinationPrefixes("/app");
      config.setUserDestinationPrefix("/user");
    }
  
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
      registry.addEndpoint("/ws")
              .addInterceptors(new SecurityHandshakeInterceptor())
              .setHandshakeHandler(new CustomHandshakeHandler())
              .setAllowedOrigins("*");;
    }

    @Override
	  public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				// if (StompCommand.CONNECT.equals(accessor.getCommand())) {
          System.out.println("configureClientInboundChannel" + accessor.getCommand().name());
          // accessor.get
					// Access authentication header(s) and invoke accessor.setUser(user)
				// }
				return message;
			}
		});
	}
}
