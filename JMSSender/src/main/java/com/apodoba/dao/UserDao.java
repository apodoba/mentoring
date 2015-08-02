package com.apodoba.dao;

import java.util.List;

import com.apodoba.domain.User;

public interface UserDao {

	List<User> getAllUsers();
	
	void deleteUser(long id);
	
	void updateUser(User user);
	
	void addUser(User user);
	
	User getUser(long id);
	
}
