package com.apodoba.shop.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.apodoba.shop.domain.bank.Transaction;
import com.apodoba.shop.domain.shop.Audit;

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


}
