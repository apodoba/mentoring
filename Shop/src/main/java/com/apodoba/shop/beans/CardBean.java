package com.apodoba.shop.beans;

import javax.ejb.Local;

import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.bank.Transaction;

@Local
public interface CardBean {

	public void saveTransaction(Transaction transaction);
	
	public Card getCard(long number, int cvv, int endYear);
}
