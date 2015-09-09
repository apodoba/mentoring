package com.apodoba.anticorruption;

import java.util.Comparator;

import com.apodoba.domain.Ticket;

public class TicketSortByHall implements Comparator<Ticket>{

	@Override
	public int compare(Ticket o1, Ticket o2) {
		return o1.getHall().compareToIgnoreCase(o2.getHall());
	}

}
