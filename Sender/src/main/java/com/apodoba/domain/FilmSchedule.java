package com.apodoba.domain;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import com.apodoba.jms.MessageSender;

@Component
public class FilmSchedule {
	
	@Autowired
	MessageSender messageSender;

	private String film;
	private Date time;
	private List<Ticket> tickets;

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<Ticket> getTickets() {
		try {
			messageSender.sendGetTicketMessage(film, time);
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
