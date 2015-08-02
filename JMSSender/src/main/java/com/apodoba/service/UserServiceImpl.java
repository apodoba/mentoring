package com.apodoba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apodoba.dao.UserDao;
import com.apodoba.domain.User;
import com.apodoba.jms.MessageSender;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	

	@Autowired
	MessageSender messageSender;

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void deleteUser(long id) {
		messageSender.sendMessage("delete", userDao.getUser(id));
		userDao.deleteUser(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
		messageSender.sendMessage("update", user);
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
		messageSender.sendMessage("add", user);
	}

	@Override
	public User getUser(long id) {
		return userDao.getUser(id);
	}

}
