package com.apodoba.shop.beans;

import javax.ejb.Remote;

import com.apodoba.shop.domain.User;

@Remote
public interface Purchase {

	public void saveUser(User user);
}
