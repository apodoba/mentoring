package com.apodoba.shop.client;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
		User user = userMB.getUser();
		Service service = purchaseBean.getServiceById(serviceForPay);
		Card card = new Card();
		card.setNumber(cardNumber);
		card.setCvv(cvv);
		card.setEndYear(year);

		try {
			purchaseBean.savePurchase(user, service, card, paymentCount);
			sendInfoMessageToUser("Successful payment");
		} catch (CardNotExistException e) {
			sendErrorMessageToUser("Such card not exist");
		} catch (NotEnoughMoneyException e) {
			sendErrorMessageToUser("Not enough money on card");
		}
	}

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message));
	}

	private void sendErrorMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
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
