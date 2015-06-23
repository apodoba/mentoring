package test.client;

import java.math.BigDecimal;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.apodoba.shop.beans.PurchaseBean;
import com.apodoba.shop.domain.bank.Card;
import com.apodoba.shop.domain.shop.User;

public class EJBApplicationClient {

	public static void main(String[] args) {
		try {
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "remote://localhost:4447");
			env.put("jboss.naming.client.ejb.context", true);

			Context ctx = new InitialContext(env);
			PurchaseBean transactionalService = (PurchaseBean) ctx
					.lookup("Shop/PurchaseBeanImpl!com.apodoba.shop.beans.PurchaseBean");

			User user = new User();
			user.setFirstName("Firstname");
			user.setLastName("Lastname");
			user.setPassword("password");
			user.setMail("mail.ru");

			Card card = new Card();
			card.setCount(new BigDecimal(10));
			card.setCvv(987);
			card.setEndYear(2016);
			card.setNumber(8909765456782341l);

			System.out.println("EJB says: saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
