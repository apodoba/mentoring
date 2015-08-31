package com.apodoba.process;

import java.io.IOException;

import com.apodoba.entity.manager.EntityManager;
import com.apodoba.entity.manager.EntityManagerFactory;
import com.apodoba.entity.manager.SimpleEntityManagerFactory;

public class ProcessEntityMananger {

	public static EntityManager getEntityManager(){
		try {
			EntityManagerFactory factory = new SimpleEntityManagerFactory();
			EntityManager entityManager = factory.getEntityManager("mapping.properties");
			return entityManager;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
