package com.apodoba.jms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.apodoba.domain.Ticket;
import com.apodoba.service.TicketService;

@Component
public class JMSMessageListener implements MessageListener {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private TicketService ticketService;

	@Override
	public void onMessage(Message m) {
		MapMessage message = (MapMessage) m;
		try {
			String receiveCommand = (String) message.getMapNames().nextElement();
			Object response = execute(message);
			
			MessageProducer producer = null;
			jmsTemplate.setDefaultDestination(message.getJMSReplyTo());

			Session session = jmsTemplate.getConnectionFactory().createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(message.getJMSReplyTo());
			
			final MapMessage responseMessage = session.createMapMessage();
			responseMessage.setObject(receiveCommand, response);
			responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
			responseMessage.setJMSReplyTo(message.getJMSReplyTo());
			
			producer.send(message.getJMSReplyTo(), responseMessage);
		} catch (JMSException e1) {
			e1.printStackTrace();
		} catch (JmsException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Object execute(MapMessage message) throws JMSException {
		String receiveCommand = (String) message.getMapNames().nextElement();
		Map<String, Object> params = (Map<String, Object>) message.getObject(receiveCommand);

		Object response = null;
		switch (Commands.valueOf(receiveCommand.toUpperCase())) {
		case BLOCK:
			long id = (long) params.get("id");
			response = ticketService.reserveTicket(id);
			break;
		case CONFIRM:
			id = (long) params.get("id");
			response = ticketService.confirmReservationTicket(id);
			break;
		case SCHEDULE:
			response = ticketService.getCinemaScheduler();
			break;
		case TICKETS:
			String film = (String) params.get("film");
			Date time = (Date) params.get("time");
			List<Ticket> tickets = ticketService.getTicketsByFilmAndTime(film, time);
			response = new ArrayList<Map<String, Object>>();
			
			Map<String, Object> transport = null;
			for(Ticket ticket: tickets){
				transport = new HashMap<String, Object>();
				transport.put("film", ticket.getFilm());
				transport.put("id", ticket.getId());
				transport.put("time", ticket.getTime().getTime());
				transport.put("hall", ticket.getHall());
				transport.put("place", ticket.getPlace());
				transport.put("price", ticket.getPrice());
				transport.put("status", ticket.getStatus());
				((List<Map<String, Object>>)response).add(transport);
			}
			
			break;
		}

		return response;
	}

}