package test.client;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.apodoba.shop.beans.PurchaseBean;
import com.apodoba.shop.domain.shop.Service;

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
					.lookup("Shop-0.0.1-SNAPSHOT/PurchaseBeanImpl!com.apodoba.shop.beans.PurchaseBean");

//			System.out.println(transactionalService.getUser("arinapodoba@gmail.com", "1111"));
//			System.out.println(transactionalService.getUser("arinapoasdasddoba@gmail.com", "111asd1"));
			
			List<Service> services = transactionalService.getAllServices();
			for(Service service: services){
				System.out.println(service.toString());
			}
			
			System.out.println("EJB says: saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
