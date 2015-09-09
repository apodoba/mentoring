package com.apodoba.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.apodoba.anticorruption.AntiCorruptionLayer;
import com.apodoba.anticorruption.TicketSortByHall;
import com.apodoba.exception.HallNotAvailable;

@Component
@Scope("prototype")
public class FilmSchedule {

	@Autowired
	AntiCorruptionLayer antiCorruptionLayer;

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

	public List<Ticket> getTickets() throws HallNotAvailable {
		tickets = antiCorruptionLayer.getFilmTickets(film, time);
		if(tickets.isEmpty()){
			throw new HallNotAvailable();
		}
		
		Collections.sort(tickets, new TicketSortByHall());
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public AntiCorruptionLayer getAntiCorruptionLayer() {
		return antiCorruptionLayer;
	}

	public void setAntiCorruptionLayer(AntiCorruptionLayer antiCorruptionLayer) {
		this.antiCorruptionLayer = antiCorruptionLayer;
	}

}
