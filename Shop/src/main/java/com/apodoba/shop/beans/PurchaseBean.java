package com.apodoba.shop.beans;

import java.util.List;

import javax.ejb.Remote;

import com.apodoba.shop.domain.bank.Transaction;
import com.apodoba.shop.domain.shop.Audit;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;

@Remote
public interface PurchaseBean {

	public void savePurchase(Audit audit, Transaction transaction);

	public User getUser(String mail, String password);

	public List<Service> getAllServices();
}
