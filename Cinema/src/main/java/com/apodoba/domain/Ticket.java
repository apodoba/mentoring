package com.apodoba.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.apodoba.jms.Commands;
import com.apodoba.jms.MessageSender;

@Component
@Scope("prototype")
public class Ticket {

	@Autowired
	private MessageSender messageSender;

	private Long id;
	private String hall;
	private String film;
	private BigDecimal price;
	private int place;
	private int status;
	private Date time;

	public boolean reserve() {
		try {
			return messageSender.sendChangeStatusMessage(Commands.BLOCK.name(), id);
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean pay() {
		try {
			boolean processPayment = messageSender.sendChangeStatusMessage(Commands.CHECK_STATUS.name(), id);
			if(processPayment){
				final String uri = "http://localhost:8080/PaymentSystem/pay";
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
				ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
				if("ok".equals(result.getBody())){
					confirm();
					return true;
				}else{
					rollbackPayment();
				}
			}
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private boolean confirm() {
		try {
			return messageSender.sendChangeStatusMessage(Commands.CONFIRM.name(), id);
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean rollbackPayment() {
		try {
			return messageSender.sendChangeStatusMessage(Commands.ROLLBACK_PAYMENT.name(), id);
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}