package com.apodoba.jms;

import java.math.BigDecimal;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apodoba.dao.CustomerDao;
import com.apodoba.domain.Customer;

@Component
public class JMSMessageListener implements MessageListener {
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public void onMessage(Message m) {
		if (m instanceof MapMessage) {
			try {
				MapMessage message = (MapMessage) m;
				execute(message);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void execute(MapMessage message) throws JMSException {
		String receiveCommand = (String) message.getMapNames().nextElement();
		Map<String, Object> userMap = (Map<String, Object>) message.getObject(receiveCommand);
		
		Customer customer = new Customer();
		customer.setId((Long) userMap.get("id"));
		customer.setName((String) userMap.get("firstName"));
		customer.setSurname((String) userMap.get("lastName"));
		customer.setCount(BigDecimal.valueOf((double) userMap.get("balance")));

		switch (Commands.valueOf(receiveCommand.toUpperCase())) {
		case ADD:
			customerDao.addCustomer(customer);
			break;
		case UPDATE:
			customerDao.updateCustomer(customer);
			break;
		case DELETE:
			customerDao.deleteCustomer(customer);
			break;
		}
	}
}