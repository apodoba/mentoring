package com.apodoba.shop.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.apodoba.shop.domain.User;

@Stateless
public class PurchaseBean implements Purchase {

	@PersistenceContext(unitName = "shop")
	private EntityManager entityManager;

	public PurchaseBean() {
	}

	@Override
	public void saveUser(User user) {
		entityManager.persist(user);

	}

}
