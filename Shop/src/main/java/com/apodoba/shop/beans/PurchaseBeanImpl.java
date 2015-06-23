package com.apodoba.shop.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.shop.User;

@Stateless
public class PurchaseBeanImpl implements PurchaseBean {

	@EJB
	private CardBean cardBean;

	@EJB
	private ShopBean shopBean;

	@Override
	public void saveUser(User user, Card card) {
		shopBean.save(user);
		cardBean.save(card);
	}

}
