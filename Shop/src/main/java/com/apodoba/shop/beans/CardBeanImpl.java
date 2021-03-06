package com.apodoba.shop.beans;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.bank.Transaction;

@Stateless
public class CardBeanImpl implements CardBean {

	@PersistenceContext(unitName = "bank")
	private EntityManager entityManagerBank;

	@Override
	public void saveTransaction(Transaction transaction) {
		entityManagerBank.persist(transaction);
	}

	@Override
	public Card getCard(long number, int cvv, int endYear) {
		try {
			Card card = (Card) entityManagerBank.createQuery("SELECT card FROM Card card WHERE card.number = :number "
					+ "AND card.cvv = :cvv AND card.endYear = :endYear")
					.setParameter("number", number).setParameter("cvv", cvv)
					.setParameter("endYear", endYear).getSingleResult();
			return card;
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public void decreaseBalance(Card card, BigDecimal count) {
		BigDecimal newCount = card.getCount().subtract(count);
		card.setCount(newCount);
		entityManagerBank.merge(card);
	}

}
