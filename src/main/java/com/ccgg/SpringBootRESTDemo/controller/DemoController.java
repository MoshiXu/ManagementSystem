package com.ccgg.SpringBootRESTDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccgg.SpringBootRESTDemo.beans.User;
import com.ccgg.SpringBootRESTDemo.http.Response;
import com.ccgg.SpringBootRESTDemo.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	UserService userService;

	
	
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
	
}
