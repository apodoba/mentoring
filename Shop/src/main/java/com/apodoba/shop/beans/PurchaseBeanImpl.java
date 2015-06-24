package com.apodoba.shop.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.bank.Transaction;
import com.apodoba.shop.domain.shop.Audit;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;
import com.apodoba.shop.exception.CardNotExistException;
import com.apodoba.shop.exception.NotEnoughMoneyException;

@Stateless
public class PurchaseBeanImpl implements PurchaseBean {

	@EJB
	private CardBean cardBean;

	@EJB
	private ShopBean shopBean;

	@Override
	public void savePurchase(User user, Service service, Card card, BigDecimal payment) throws CardNotExistException, NotEnoughMoneyException {
		Card realCard = cardBean.getCard(card.getNumber(), card.getCvv(), card.getEndYear());
		
		if(realCard == null){
			throw new CardNotExistException();
		}
		if(realCard.getCount().compareTo(payment)<0){
			throw new NotEnoughMoneyException();
		}
		
		Transaction transaction = new Transaction();
		transaction.setCard(realCard);
		transaction.setCount(payment);
		transaction.setDate(new Date());
		transaction.setAdditionalInfo(service.getDescription());
		
		Audit audit = new Audit();
		audit.setCount(payment);
		audit.setDate(new Date());
		audit.setService(service);
		audit.setUser(user);
		
		cardBean.decreaseBalance(realCard, payment);
		cardBean.saveTransaction(transaction);
		shopBean.saveAudit(audit);
		
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
