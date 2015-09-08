package com.apodoba.domain;

import java.math.BigDecimal;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import com.apodoba.jms.Commands;
import com.apodoba.jms.MessageSender;

@Component
public class Ticket {

	@Autowired
	private MessageSender messageSender;

	private Long id;
	private String hall;
	private String film;
	private BigDecimal price;
	private int place;
	private int status;
	private int time;

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
		return false;
	}

	public boolean confirm() {
		try {
			return messageSender.sendChangeStatusMessage(Commands.CONFIRM.name(), id);
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}