package com.example.module4_rest.service;

import com.example.module4_rest.model.User;
import com.example.module4_rest.repository.UserRepository;
import com.example.module4_rest.util.CustomPasswordEncoder;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final CustomPasswordEncoder passwordEncoder;


	@Override
	public User addUser(User user) {
		if (isUserExistsInDb(user)) {
			throw new IllegalStateException(String.format("User %s already exist in Db ",
				user.getName()));
		}
		User userWithEncodedPassword = encodePassword(user);

		userRepository.addUser(userWithEncodedPassword);

		user.getPermissions().stream().forEach(permission -> {
			userRepository.addUserPermission(user.getName(), user.getEmail(), permission.name());
		});

		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}


	private Boolean isUserExistsInDb(User user) {
		User userByEmail = userRepository.getUserByEmail(user.getEmail());
		return userByEmail != null;
	}

	@Override
	public List<User> getBlockedUsers() {
		return userRepository.getBlockedUsers();
	}

	private User encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);

		return user;
	}
}
