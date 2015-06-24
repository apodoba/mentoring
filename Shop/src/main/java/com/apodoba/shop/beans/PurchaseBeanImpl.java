package com.apodoba.shop.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.apodoba.shop.domain.bank.Transaction;
import com.apodoba.shop.domain.shop.Audit;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;

@Stateless
public class PurchaseBeanImpl implements PurchaseBean {

	@EJB
	private CardBean cardBean;

	@EJB
	private ShopBean shopBean;

	@Override
	public void savePurchase(Audit audit, Transaction transaction) {
		shopBean.saveAudit(audit);
		cardBean.saveTransaction(transaction);
	}

	@Override
	public User getUser(String mail, String password) {
		return shopBean.getUser(mail, password);
	}

	@Override
	public List<Service> getAllServices() {
		return shopBean.getAllServices();
	}


}
