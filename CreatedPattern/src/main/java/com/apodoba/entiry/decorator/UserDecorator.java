package com.apodoba.entiry.decorator;

import org.apache.log4j.Logger;

import com.apodoba.entiry.User;

public class UserDecorator implements User{
	
	final static Logger logger = Logger.getLogger(UserDecorator.class);
	
	private User user;
	
	public UserDecorator(User user) {
		this.user = user;
	}
	
	@Override
	public int getId() {
		int id = user.getId();
		logger.info("Get user id " + id);
		return id;
	}

	@Override
	public void setId(int id) {
		user.setId(id);
	}

	@Override
	public String getName() {
		String name = user.getName();
		logger.info("Get user name " + name);
		return name;
	}

	@Override
	public void setName(String name) {
		user.setName(name);
	}

	@Override
	public String getAddress() {
		String address = user.getAddress();
		logger.info("Get user address " + address);
		return address;
	}

	@Override
	public void setAddress(String address) {
		user.setAddress(address);
		
	}
}
