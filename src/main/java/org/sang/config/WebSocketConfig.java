package org.sang.config;

import org.sang.config.session.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(),"/webSocketServer")
				.addInterceptors(new WebSocketHandshakeInterceptor());
		 
        registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer")
        		.addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();
	}
	
	//服务器对订阅了该服务的用户进行处理的类
	@Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }
}









/*package org.sang.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.sang.config.session.WebSocketHandshakeInterceptor;


*//**
 * Created by sang on 16-12-22.
 *//*
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    	//添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
        stompEndpointRegistry.addEndpoint("/endpointSang")
        	.addInterceptors(new WebSocketHandshakeInterceptor())
        	.withSockJS();
//        @ServerEndpoint(value="/websocket",configurator=GetHttpSessionConfigurator.class)
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	//这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址，实现推送的功能
        registry.enableSimpleBroker("/topic");
    }

}
*/

