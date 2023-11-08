package com.example.module4_rest.controller;

import com.example.module4_rest.model.User;
import com.example.module4_rest.service.UserService;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@AllArgsConstructor
public class ApplicationController {

	private final UserService userService;

	@GetMapping("/info")
	public String getRandomInfo() {

		return "Application 1";
	}

	@GetMapping("/about")
	public String getAbout() {
		return "need to donate to the developer $$$";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "need to donate to the developer $$$";
	}

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping("/getUser/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return userService.getUserByEmail(email);
	}

	@GetMapping("/getBlockedUsers")
	public List<User> getBlockedUsers() {
		return userService.getBlockedUsers();
	}
}
