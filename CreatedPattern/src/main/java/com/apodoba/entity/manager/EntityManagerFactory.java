package com.apodoba.entity.manager;

import java.io.IOException;


public interface EntityManagerFactory{
	
	public EntityManager getEntityManager(String mapping) throws IOException;

}
