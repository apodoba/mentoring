package com.apodoba.shop.beans;

import javax.ejb.Local;

import com.apodoba.shop.domain.bank.Card;

@Local
public interface CardBean {

	public void save(Card card);
}
