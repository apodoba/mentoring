package com.apodoba.shop.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.apodoba.shop.domain.bank.Card;

@Stateless
public class CardBeanImpl implements CardBean{

	@PersistenceContext(unitName = "bank")
	private EntityManager entityManagerBank;
	
	@Override
	public void save(Card card) {
		entityManagerBank.persist(card);
	}

}
