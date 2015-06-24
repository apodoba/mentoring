package com.apodoba.shop.beans;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.apodoba.shop.domain.shop.Audit;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;

@Stateless
public class ShopBeanImpl implements ShopBean {

	@PersistenceContext(unitName = "shop")
	private EntityManager entityManagerShop;

	@Override
	public void saveAudit(Audit audit) {
		entityManagerShop.persist(audit);
	}

	@Override
	public User getUser(String mail, String password) {
		User user = null;
		try {
			user = (User) entityManagerShop
					.createQuery("SELECT user FROM User user WHERE user.mail = :mail AND user.password = :password")
					.setParameter("mail", mail)
					.setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Service> getAllServices() {
		Query query = entityManagerShop.createQuery("SELECT services FROM Service services");
		List<Service> results = null;
		try {
			results = query.getResultList();
		} catch (NoResultException e) {
			results = Collections.emptyList();
		}
		return results;
	}

}
