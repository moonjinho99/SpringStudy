package com.example.stomptest.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotificationController {
	
	@RequestMapping(value = "/")
	public String page()
	{
		return "/notificate";
	}
	
	@MessageMapping("/sendNotification")
	@SendTo("/topic/notifications")
	public String send(String message) {
		System.out.println("메시지 : "+message);
		return message;
	}
	
}
