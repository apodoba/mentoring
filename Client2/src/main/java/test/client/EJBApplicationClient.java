package test.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.apodoba.shop.beans.Purchase;
import com.apodoba.shop.domain.User;

public class EJBApplicationClient {

	public static void main(String[] args) {
		try {
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "remote://localhost:4447");
			env.put("jboss.naming.client.ejb.context", true);

			Context ctx = new InitialContext(env);
			Purchase hello = (Purchase) ctx.lookup("Shop/PurchaseBean!com.apodoba.shop.beans.Purchase");

			User user = new User();
			user.setFirstName("Firstname");
			user.setLastName("Lastname");
			user.setLogin("login");
			user.setPassword("password");
			user.setMail("mail.ru");
			hello.saveUser(user);

			System.out.println("EJB says: saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
