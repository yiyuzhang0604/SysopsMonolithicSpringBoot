package com.sysops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/")
	public String getWelcome() {
		logger.info("UserController.loginPage()");
		return "<h1>Welcome all!</h1>";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "<h1>Welcome admin!</h1>";
	}

	@GetMapping("/user")
	public String getUser() {
		return "<h1>Welcome user!</h1>";
	}
}
