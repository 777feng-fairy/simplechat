package com.pqv.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @EnableWebsocetMessageBorker  开启消息队列
 * @author 白衣雪
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    /**
     * 对外暴露一个websocket的接口（前端websocket服务连接的地址）
     * 允许跨域
     * 前端链接sockjs协议
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/wschat").setAllowedOrigins("*").withSockJS();
    }
    
    /**
     * 创建消息队列（基于内存的消息代理）“/topic" "/queue"
     * 
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /* 基于内存消息队列
         * registry.enableSimpleBroker("/topic","/queue");
         * registry.setUserDestinationPrefix("/user");
         */
        //基于rabbit消息队列
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("127.0.0.1")
                .setRelayPort(61613)
                .setVirtualHost("/feng777")
                .setClientLogin("feng777")
                .setClientPasscode("feng777")
                .setSystemLogin("feng777")
                .setSystemPasscode("feng777");
                
        
    }
    
}
