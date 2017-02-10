package org.sang.config;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Log logger;
	private static final ArrayList<WebSocketSession> users;

	static {
		users = new ArrayList<>();
		logger = LogFactory.getLog(SystemWebSocketHandler.class);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
			throws Exception {
		logger.debug("websocket connection closed......");
		System.out.println("websocket connection closed......");
		users.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String userName = (String) session.getAttributes().get(
				"websocket_username");
		logger.debug(session.getAttributes());
		logger.debug(userName + " connect to the websocket success......");
		System.out.println(session.getAttributes());
		System.out.println(userName + " connect to the websocket success......");
		users.add(session);
		if (userName != null) {
			// 查询未读消息
			// int count = webSocketService.getUnReadNews((String)
			// session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
			sendMessageToUsers(new TextMessage(userName + " join"));
			session.sendMessage(new TextMessage(+users.size() + "users in"));
		}
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		sendMessageToUsers(message);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1)
			throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		logger.error("websocket connection  error and closed......", arg1);
		System.out.println("websocket connection  error and closed......");
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(WebSocketMessage<?> message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				logger.error(
						"send message to "
								+ user.getAttributes().get(
										"websocket_username")
								+ " error!", e);
			}
		}
	}

	/**
	 * 给某个用户发送消息
	 *
	 * @param userName
	 * @param message
	 */
	/*public void sendMessageToUser(String userName, TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME)
					.equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					logger.error(
							"send message to "
									+ user.getAttributes().get(
											Constants.WEBSOCKET_USERNAME)
									+ " error!", e);
				}
				break;
			}
		}
	}*/
}
