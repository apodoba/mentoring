package com.apodoba.service;

import java.util.List;

import com.apodoba.domain.User;

public interface UserService {

	List<User> getAllUsers();

	void deleteUser(long id);

	void updateUser(User user);

	void addUser(User user);
	
	User getUser(long id);
}
