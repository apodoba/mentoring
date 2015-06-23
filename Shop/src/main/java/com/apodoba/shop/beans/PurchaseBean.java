package com.apodoba.shop.beans;

import javax.ejb.Remote;

import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.shop.User;

@Remote
public interface PurchaseBean {

	public void saveUser(User user, Card card) throws Exception;
}
