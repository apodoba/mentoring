package com.apodoba.shop.beans;

import javax.ejb.Remote;

import com.apodoba.shop.domain.bank.Transaction;
import com.apodoba.shop.domain.shop.Audit;

@Remote
public interface PurchaseBean {

	public void savePurchase(Audit audit, Transaction transaction);
}
