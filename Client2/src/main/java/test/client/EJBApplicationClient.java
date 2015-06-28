package test.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.mx.util.MBeanServerLocator;

import com.apodoba.shop.beans.PurchaseBean;
import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;
import com.apodoba.shop.jmx.JMXExampleMBean;

public class EJBApplicationClient {

	public static void main2(String[] args) {
		try {
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "remote://localhost:4447");
			env.put("jboss.naming.client.ejb.context", true);

			Context ctx = new InitialContext(env);
			PurchaseBean transactionalService = (PurchaseBean) ctx
					.lookup("Shop-0.0.1-SNAPSHOT/PurchaseBeanImpl!com.apodoba.shop.beans.PurchaseBean");

			User user = transactionalService.getUser("arinapodoba@gmail.com",
					"1111");
			List<Service> services = transactionalService.getAllServices();

			Card card = new Card();
			card.setNumber(2147483647);
			card.setCvv(233);
			card.setEndYear(2018);

			BigDecimal payment = new BigDecimal(10);

			transactionalService.savePurchase(user, services.get(0), card,
					payment);

			System.out.println("EJB says: saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			MBeanServer mbeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer();

			 JMXExampleMBean cal = (JMXExampleMBean) MBeanProxyExt.create(
					 JMXExampleMBean.class,
					"com.apodoba.shop.jmx:service=HelloWorld", mbeanServer);
			 
			System.out.println("Amount of Interests: " + cal.projectName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
