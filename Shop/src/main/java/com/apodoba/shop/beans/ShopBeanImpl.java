package com.apodoba.shop.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.apodoba.shop.domain.shop.User;

@Stateless
public class ShopBeanImpl implements ShopBean{

	@PersistenceContext(unitName = "shop")
	private EntityManager entityManagerShop;
	
	@Override
	public void save(User user) {
		entityManagerShop.persist(user);
	}

}

