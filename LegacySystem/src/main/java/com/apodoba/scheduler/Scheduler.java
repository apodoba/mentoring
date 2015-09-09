package com.apodoba.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.apodoba.service.TicketService;

@Component
public class Scheduler {
	
	@Autowired
	TicketService ticketService;

	@Scheduled(initialDelay=1000, fixedRate=120000)
	public void updateExchangeRates() {
		ticketService.unBlockTickets();
	}
}
