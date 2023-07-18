package com.springboot.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 接點註冊
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/websocket");
        registry.addEndpoint("/websocket").withSockJS();
    }

    /**
     * 配置消息代理
     * enableSimpleBroker - service to client prefix
     * setApplicationDestinationPrefixes - client to service prefix
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");

    }

}
