package com.apodoba.jms;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.apodoba.domain.User;

@Component
public class MessageSender {

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("jmsTemplate2")
	private JmsTemplate jsmLogTemplate2;

	public void sendMessage(final String command, final User user) {
		jmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("id", user.getId());
				userMap.put("firstName", user.getFirstName());
				userMap.put("lastName", user.getLastName());
				userMap.put("balance", user.getBalance().doubleValue());
				MapMessage message = session.createMapMessage();
				message.setObject(command, userMap);
				return message;
			}
		});
	}

	public void sendToLogger(final String command, final User user) {
		jsmLogTemplate2.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createMessage();
				message.setStringProperty("command", command);
				
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("id", user.getId());
				userMap.put("firstName", user.getFirstName());
				userMap.put("lastName", user.getLastName());
				userMap.put("balance", user.getBalance().doubleValue());
				
				message.setObjectProperty("user", userMap);
				return message;
			}
		});
	}
}
