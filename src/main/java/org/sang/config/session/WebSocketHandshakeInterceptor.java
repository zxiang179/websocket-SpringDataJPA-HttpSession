package org.sang.config.session;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
	 
//    private static Log logger = LogFactory.getLog(HandshakeInterceptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
                > attributes) throws Exception {
//    	logger.debug("beforeHandshake start.....");
    	System.out.println("beforeHandshake start.....");
//    	logger.debug(request.getClass().getName());
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                /*//使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute(Constants.SESSION_USERNAME);
                logger.info(userName+" login");
                attributes.put(Constants.WEBSOCKET_USERNAME,userName);*/
            	System.out.println("httpsession is not null");
            }else{
//            	logger.debug("httpsession is null");
            	System.out.println("httpsession is null");
            }
        }
        return true;
    }
 
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}