package com.apodoba.process;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apodoba.entiry.adapter.EmployeeAdapter;
import com.apodoba.entiry.adapter.UserAdapter;
import com.apodoba.entiry.decorator.UserDecorator;
import com.apodoba.entity.Employee;
import com.apodoba.entity.EmployeeImpl;
import com.apodoba.entity.Service;
import com.apodoba.entity.User;
import com.apodoba.entity.UserImpl;
import com.apodoba.entity.manager.EntityManager;
import com.apodoba.utils.Action;
import com.apodoba.utils.EntityType;

public class Process {

	private final EntityManager entityManager;
	
	public Process() {
		this.entityManager = ProcessEntityMananger.getEntityManager();
	}

	public List<Service> getAllServices() throws Exception {
		List<Service> list = entityManager.selectAll(Service.class);
		return list;
	}
	
	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		for(User user : entityManager.selectAll(UserImpl.class)){
			UserAdapter userAdapter = new UserAdapter(new UserDecorator(user));
			employees.add(userAdapter);
		}
		return employees;
	}
	
	public void process(HttpServletRequest request){
		Object entity = defineObject(request);
		processAction(entity, request);
	}
	
	private Object defineObject(HttpServletRequest request) {
		Object object = null;
		String objectType = request.getParameter("object");
		EntityType entityType = EntityType.valueOf(objectType.toUpperCase());

		switch (entityType) {
		case SERVICE:
			object = constructService(request);
			break;
		case EMPLOYEE:
			object = constructUser(request);
			break;
		}
		return object;
	}

	private Service constructService(HttpServletRequest request) {
		Service service = new Service();
		service.setId(Integer.parseInt(request.getParameter("id").isEmpty() ? "0" : request.getParameter("id")));
		service.setName(request.getParameter("name"));
		return service;
	}
	
	private User constructUser(HttpServletRequest request) {
		Employee employee = new EmployeeImpl();
		employee.setId(Integer.parseInt(request.getParameter("id").isEmpty() ? "0" : request.getParameter("id")));
		employee.setLastName(request.getParameter("lastName"));
		employee.setFirstName(request.getParameter("firstName"));
		employee.setCity(request.getParameter("city"));
		employee.setFlat(request.getParameter("flat"));
		employee.setStreet(request.getParameter("street"));
		
		EmployeeAdapter employeeAdapter = new EmployeeAdapter(employee);
		
		return employeeAdapter;
	}
	
	

	private void processAction(Object entity, HttpServletRequest request) {
		String action = request.getParameter("action");
		Action actionType = Action.valueOf(action.toUpperCase());
		try {
			switch (actionType) {
			case ADD:
				add(entity);
				break;
			case DELETE:
				delete(entity);
				break;
			case UPDATE:
				update(entity);
				break;
			}
		} catch (Exception e) {
			request.setAttribute("error", "Could not process operation");
			e.printStackTrace();
		}
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
