package com.example.module4_rest.service;

import com.example.module4_rest.model.User;
import java.util.List;

public interface UserService {
	User addUser(User user);

	User getUserByEmail(String email);

	List<User> getBlockedUsers();
}
