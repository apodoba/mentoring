package com.apodoba.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apodoba.dao.TicketDao;
import com.apodoba.domain.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDao ticketDao;

	@Override
	public Map<String, List<Long>> getCinemaScheduler() {
		return ticketDao.getCinemaScheduler();
	}

	@Override
	public List<Ticket> getTicketsByFilmAndTime(String film, Date time) {
		return ticketDao.getTicketsByFilmAndTime(film, time);
	}

	@Override
	public boolean reserveTicket(long ticketId) {
		return ticketDao.reserveTicket(ticketId);
	}

	@Override
	public boolean confirmReservationTicket(long ticketId) {
		return ticketDao.confirmReservationTicket(ticketId);
	}


}
