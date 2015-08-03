package com.apodoba.jms;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JMSDeleteMessageListener implements MessageListener {

	private static final Logger logger = Logger.getLogger(JMSDeleteMessageListener.class);

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		try {
			logger.info("Command " + message.getStringProperty("command"));
			Map<String, Object> userMap = (Map<String, Object>) message.getObjectProperty("user");
			logger.info("User: id=" + userMap.get("id") + " firstName="+ userMap.get("firstName") + " lastName="+ userMap.get("lastName")
					 + " count="+ userMap.get("balance"));
		} catch (JMSException e) {
			logger.error(e);
		}
	}
}