package com.ccgg.SpringBootRESTDemo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccgg.SpringBootRESTDemo.beans.Message;
import com.ccgg.SpringBootRESTDemo.dao.MessageDao;
import com.ccgg.SpringBootRESTDemo.dao.UserDao;
import com.ccgg.SpringBootRESTDemo.http.Response;
import com.ccgg.SpringBootRESTDemo.service.MessageService;

@RestController()
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	MessageService messageService;
	
	//get username by messageId
	//return string in spring controller
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PostMapping("/{messageId}")
	public String getUsername(@PathVariable int messageId) {
		return messageService.getUsername(messageId);
	}
	
	@GetMapping("/time")
	public String sayHello() {
		return "Hello"+", time is: "+LocalDateTime.now();
	}
	
	
}
