package com.apodoba.jms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	public boolean sendChangeStatusMessage(final String command, final long ticketId) throws JmsException, JMSException {
		MessageCreator messageCreator = new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", ticketId);
				MapMessage message = session.createMapMessage();
				message.setObject(command, params);
				message.setJMSCorrelationID(UUID.randomUUID().toString());
				return message;
			}
		};
		System.out.println("Sender: sende");
		MapMessage receive = (MapMessage)jmsQueueTemplate.sendAndReceive(messageCreator);
		boolean result = (boolean)receive.getObject(command);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	//TODO return real objects
	public List<Map<String, Object>> sendGetTicketMessage(final String film, final Date time) throws JmsException, JMSException {
		MessageCreator messageCreator = new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("film", film);
				params.put("time", time);
				MapMessage message = session.createMapMessage();
				message.setObject(Commands.TICKETS.name(), params);
				message.setJMSCorrelationID(UUID.randomUUID().toString());
				return message;
			}
		};
		System.out.println("Sender: sende");
		MapMessage receive = (MapMessage)jmsQueueTemplate.sendAndReceive(messageCreator);
		List<Map<String, Object>> result = (List<Map<String, Object>>)receive.getObject(Commands.TICKETS.name());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	//TODO return real objects
	public Map<String, List<Date>> sendScheduleMessage() throws JmsException, JMSException {
		MessageCreator messageCreator = new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setObject(Commands.SCHEDULE.name(), new HashMap<String, Object>());
				message.setJMSCorrelationID(UUID.randomUUID().toString());
				return message;
			}
		};
		System.out.println("Sender: sende");
		MapMessage receive = (MapMessage)jmsQueueTemplate.sendAndReceive(messageCreator);
		Map<String, List<Date>> result = (Map<String, List<Date>>)receive.getObject(Commands.SCHEDULE.name());
		return result;
	}
}
