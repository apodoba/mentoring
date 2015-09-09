package com.apodoba.anticorruption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import com.apodoba.domain.FilmSchedule;
import com.apodoba.domain.Ticket;
import com.apodoba.jms.MessageSender;

@Component
public class AntiCorruptionLayer {

	@Autowired
	MessageSender messageSender;
	
	@Autowired 
	private ApplicationContext applicationContext;

	public List<FilmSchedule> getCinemaSchedule() {
		List<FilmSchedule> allFilmSchedules = new ArrayList<FilmSchedule>();
		try {
			Map<String, List<Long>> cinemaSchedule = messageSender.sendScheduleMessage();
			for(String film: cinemaSchedule.keySet()){
				for(long time: cinemaSchedule.get(film)){
					allFilmSchedules.add(getFilmSchedule(film, time));
				}
			}
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return allFilmSchedules;
	}
	
	public List<Ticket> getFilmTickets(String film, Date time) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		try {
			List<Map<String, Object>> allTickets = messageSender.sendGetTicketMessage(film, time);
			for(Map<String, Object> ticketContent: allTickets){
				tickets.add(getFilmTickets(ticketContent));
			}
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return tickets;
	}

	private FilmSchedule getFilmSchedule(String film, long time) {
		FilmSchedule filmSchedule = applicationContext.getBean(FilmSchedule.class);
		filmSchedule.setFilm(film);
		filmSchedule.setTime(new Date(time));
		return filmSchedule;
	}
	
	private Ticket getFilmTickets(Map<String, Object> ticketContent) {
		Ticket ticket = applicationContext.getBean(Ticket.class);
		ticket.setFilm((String) ticketContent.get("film"));
		ticket.setHall((String) ticketContent.get("hall"));
		ticket.setId((long) ticketContent.get("id"));
		ticket.setPlace((int) ticketContent.get("place"));
		ticket.setPrice(BigDecimal.valueOf((double) ticketContent.get("price")));
		ticket.setStatus((int) ticketContent.get("status"));
		ticket.setTime(new Date((long) ticketContent.get("time")));
		
		return ticket;
	}
}
