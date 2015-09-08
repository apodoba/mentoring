package com.apodoba.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apodoba.domain.Ticket;

@Repository
@Transactional
public class TicketDaoImpl implements TicketDao{

	@Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<Long>> getCinemaScheduler() {
		Map<String, List<Long>> result = new HashMap<String, List<Long>>();
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT film, hall, session FROM cinema.ticket group by film, hall, session;");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Object> data = query.list();
        
        for(Object object : data){
           Map<Object, Object> row = (Map<Object, Object>)object;
           String film = (String) row.get("film");
           Date time = (Date) row.get("session");
           
           if(!result.containsKey(film)){
        	   result.put(film, new ArrayList<Long>());
           }
           List<Long> schedule = result.get(film);
           schedule.add(time.getTime());
           
        }
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketsByFilmAndTime(String film, Date time) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("film", film));
		criteria.add(Restrictions.eq("time", time));
		return criteria.list();
	}

	@Override
	public boolean reserveTicket(long ticketId) {
		Ticket ticket = (Ticket) sessionFactory.getCurrentSession().get(Ticket.class, ticketId);
		if(ticket.getStatus() == 0){
			ticket.setStatus(1);
			sessionFactory.getCurrentSession().merge(ticket);
			return true;
		}
		return false;
	}

	@Override
	public boolean confirmReservationTicket(long ticketId) {
		Ticket ticket = (Ticket) sessionFactory.getCurrentSession().get(Ticket.class, ticketId);
		if(ticket.getStatus() == 1){
			ticket.setStatus(2);
			sessionFactory.getCurrentSession().merge(ticket);
			return true;
		}
		return false;
	}
}
