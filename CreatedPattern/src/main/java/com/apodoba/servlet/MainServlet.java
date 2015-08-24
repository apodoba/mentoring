package com.apodoba.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apodoba.entiry.Service;
import com.apodoba.entiry.User;
import com.apodoba.process.Process;
import com.apodoba.utils.Action;
import com.apodoba.utils.EntityType;

@WebServlet(name = "mainServlet", urlPatterns = { "" })
public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7668362139348678006L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Process process = new Process();

		if(request.getParameter("object") != null && request.getParameter("action") != null){
			Object entity = defineObject(request);
			processAction(entity, request, process);
		}

		setServicesAttribute(process, request);
		request.getRequestDispatcher("/start.jsp").forward(request, response);
	}

	private void setServicesAttribute(Process process, HttpServletRequest request) {
		try {
			request.setAttribute("services", process.getAllServices());
			request.setAttribute("users", process.getAllUsers());
		} catch (Exception e) {
			request.setAttribute("error", "Could not get services");
			e.printStackTrace();
		}
	}

	private Object defineObject(HttpServletRequest request) {
		Object object = null;
		String objectType = request.getParameter("object");
		EntityType entityType = EntityType.valueOf(objectType.toUpperCase());

		switch (entityType) {
		case SERVICE:
			object = constructService(request);
			break;
		case USER:
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
		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("id").isEmpty() ? "0" : request.getParameter("id")));
		user.setLastName(request.getParameter("lastName"));
		user.setFirstName(request.getParameter("firstName"));
		user.setAddress(request.getParameter("address"));
		return user;
	}

	private void processAction(Object entity, HttpServletRequest request, Process process) {
		String action = request.getParameter("action");
		Action actionType = Action.valueOf(action.toUpperCase());
		try {
			switch (actionType) {
			case ADD:
				process.add(entity);
				break;
			case DELETE:
				process.delete(entity);
				break;
			case UPDATE:
				process.update(entity);
				break;
			}
		} catch (Exception e) {
			request.setAttribute("error", "Could not process operation");
			e.printStackTrace();
		}
	}
}
