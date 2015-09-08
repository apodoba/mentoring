package com.apodoba.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.apodoba.domain.Ticket;

public interface TicketService {

	Map<String, List<Long>> getCinemaScheduler();
	
	List<Ticket> getTicketsByFilmAndTime(String film, Date time);
	
	boolean reserveTicket(long ticketId); 
	
	boolean confirmReservationTicket(long ticketId); 
	
}
