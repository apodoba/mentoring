package com.apodoba.entity.manager;

import java.io.IOException;
import java.util.Properties;

import com.apodoba.connection.ConnectionPull;


public class SimpleEntityManagerFactory implements EntityManagerFactory{

	@Override
	public EntityManager getEntityManager(String mapping) throws IOException  {
		EntityManager entityManeger = null;
		
		Properties properties = new Properties();
		properties.load(ConnectionPull.class.getResourceAsStream("/connection.properties"));
		
		if("mysql".equals(properties.get("database"))){
			entityManeger = new MySqlEntityManager(mapping);
		}
		return entityManeger;
	}


}
