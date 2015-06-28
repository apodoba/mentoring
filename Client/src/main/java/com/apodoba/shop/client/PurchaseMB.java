package com.apodoba.shop.client;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.apodoba.shop.beans.PurchaseBean;
import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;
import com.apodoba.shop.exception.CardNotExistException;
import com.apodoba.shop.exception.NotEnoughMoneyException;

@ManagedBean
@RequestScoped
public class PurchaseMB {

	@EJB
	private PurchaseBean purchaseBean;

	@ManagedProperty(value = "#{userMB}")
	private UserMB userMB;

	private int serviceForPay;
	private BigDecimal paymentCount;
	private long cardNumber;
	private int cvv;
	private int year;

	public Map<String, Integer> getServices() {
		Map<String, Integer> services = new HashMap<String, Integer>();
		for (Service service : purchaseBean.getAllServices()) {
			services.put(service.getName(), service.getId());
		}

		return services;
	}

	public void payForService() {
		if(paymentCount.compareTo(new BigDecimal(0))<=0){
			Util.sendErrorMessageToUser("Payment could not be empty", FacesContext.getCurrentInstance());
			return;
		}
		User user = userMB.getUser();
		Service service = purchaseBean.getServiceById(serviceForPay);
		Card card = new Card();
		card.setNumber(cardNumber);
		card.setCvv(cvv);
		card.setEndYear(year);

		try {
			purchaseBean.savePurchase(user, service, card, paymentCount);
			Util.sendInfoMessageToUser("Successful payment", FacesContext.getCurrentInstance());
		} catch (CardNotExistException e) {
			Util.sendErrorMessageToUser("Such card not exist", FacesContext.getCurrentInstance());
		} catch (NotEnoughMoneyException e) {
			Util.sendErrorMessageToUser("Not enough money on card", FacesContext.getCurrentInstance());
		}
	}
	
	public int getServiceForPay() {
		return serviceForPay;
	}

	public void setServiceForPay(int serviceForPay) {
		this.serviceForPay = serviceForPay;
	}

	public BigDecimal getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(BigDecimal paymentCount) {
		this.paymentCount = paymentCount;
	}

	public PurchaseBean getPurchaseBean() {
		return purchaseBean;
	}

	public void setPurchaseBean(PurchaseBean purchaseBean) {
		this.purchaseBean = purchaseBean;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public UserMB getUserMB() {
		return userMB;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

}
