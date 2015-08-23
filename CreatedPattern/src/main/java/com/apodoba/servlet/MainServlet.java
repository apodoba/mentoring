package com.apodoba.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apodoba.entiry.Service;
import com.apodoba.entity.manager.EntityManager;
import com.apodoba.process.ProcessEntityMananger;

@WebServlet(name = "mainServlet", urlPatterns = {"/","/main" })
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(200);
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		
		EntityManager entityManager = ProcessEntityMananger.getEntityManager();
		
		/*Service entity = new Service();
		entity.setName("water");
		
		try {
			entityManager.insert(entity);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Service entity2 = new Service();
		entity.setId(3);
		
		try {
			entityManager.delete(entity2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		Service entity3 = new Service();
		
		try {
			System.out.println(entityManager.selectAll(entity3).size());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
		dispatcher.forward(request,response);
	}
}
