package com.apodoba.shop.beans;

import javax.ejb.Local;

import com.apodoba.shop.domain.shop.User;

@Local
public interface ShopBean {

	public void save(User user);
}

