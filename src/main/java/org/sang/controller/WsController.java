package org.sang.controller;


import javax.servlet.http.HttpServletRequest;

import org.sang.dao.PersonRepository;
import org.sang.entity.RequestMessage;
import org.sang.entity.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sang on 16-12-22.
 */
@Controller
@RequestMapping(value="/websocket")
public class WsController {
	
	@Autowired
    PersonRepository personRepository;
	
	//http://localhost:8080/websocket/login?name=zx
	@RequestMapping("/login")
	public String login(HttpServletRequest request,
			@RequestParam("name") String name) {
		System.out.println(name);
		System.out.println(personRepository.findByName("zx").getAddress());
		request.getSession(true).setAttribute("session_username", name);
		return "websocket";
		// /WEB-INF/views/websocket.jsp
	}
	
    /*@MessageMapping("/welcome")
    //@MessageMapping注解和我们之前使用的@RequestMapping类似
    @SendTo("/topic/getResponse")
    //@SendTo注解表示当服务器有消息需要推送的时候，会对订阅了@SendTo中路径的浏览器发送消息。
    public ResponseMessage say(RequestMessage message,HttpServletRequest request) {
    	System.out.println(request.getSession(true));
    	
        System.out.println("接受到浏览器发送来的消息："+message.getName());
        System.out.println(personRepository.findByName("xc").getAddress());
        System.out.println("=============");
        return new ResponseMessage("welcome," + message.getName() + " !"+personRepository.findByName("xc").getAddress());
    }*/
}
