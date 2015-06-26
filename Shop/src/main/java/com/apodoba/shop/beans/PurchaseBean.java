package com.apodoba.shop.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;
import com.apodoba.shop.exception.CardNotExistException;
import com.apodoba.shop.exception.NotEnoughMoneyException;

@Remote
public interface PurchaseBean {

	public void savePurchase(User user, Service service, Card card, BigDecimal payment) throws CardNotExistException, NotEnoughMoneyException;

	public User getUser(String mail, String password);

	public List<Service> getAllServices();
	
	public User getUserByEmail(String mail);
	
	public Service getServiceById(int id);
}
