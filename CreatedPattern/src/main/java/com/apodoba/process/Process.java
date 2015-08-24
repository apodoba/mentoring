package com.apodoba.process;

import java.util.List;

import com.apodoba.entiry.Service;
import com.apodoba.entiry.User;
import com.apodoba.entity.manager.EntityManager;

public class Process {

	private final EntityManager entityManager;
	
	public Process() {
		this.entityManager = ProcessEntityMananger.getEntityManager();
	}

	public List<Service> getAllServices() throws Exception {
		List<Service> list = entityManager.selectAll(Service.class);
		return list;
	}
	
	public List<User> getAllUsers() throws Exception {
		List<User> list = entityManager.selectAll(User.class);
		return list;
	}
	
	public void add(Object entity) throws Exception{
		entityManager.insert(entity);
	}
	
	public void update(Object entity) throws Exception{
		entityManager.update(entity);
	}
	
	public void delete(Object entity) throws Exception{
		entityManager.delete(entity);
	}
}
