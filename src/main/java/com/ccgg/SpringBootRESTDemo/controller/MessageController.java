package com.ccgg.SpringBootRESTDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccgg.SpringBootRESTDemo.beans.Message;
import com.ccgg.SpringBootRESTDemo.beans.User;
import com.ccgg.SpringBootRESTDemo.dao.MessageDao;
import com.ccgg.SpringBootRESTDemo.dao.UserDao;
import com.ccgg.SpringBootRESTDemo.http.Response;
import com.ccgg.SpringBootRESTDemo.service.MessageService;
import com.ccgg.SpringBootRESTDemo.service.UserService;

@RestController()
@RequestMapping("/message")
public class MessageController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;

	@Autowired
	MessageDao messageDao;
	
	@Autowired
	MessageService messageService;

	//post
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping
	public Response addMessage(@RequestBody Message message,Authentication authentication) {
		return messageService.addMessage(message,authentication);
	}
	
	//get all the user info 
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/{messageId}")
	public List<String> getMessages(@PathVariable int messageId) {
		return messageService.getMessage(messageId);
	}
	/*
	public List<Message> getMessages(){
		return messageDao.findAll();
	}
	*/
	
	//put
	//edit the content of comment
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping
	public Response changeMessage(@RequestBody Message message) {
		return messageService.editMessage(message);
	}
	
	//delete
	@DeleteMapping("/{messageId}")
	public Response deleteMessage(@PathVariable int messageId) {
		return messageService.deleteMessage(messageId);
	}
}
