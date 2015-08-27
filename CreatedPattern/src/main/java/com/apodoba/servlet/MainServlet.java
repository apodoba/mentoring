package com.apodoba.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apodoba.process.Process;

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
			process.process(request);
		}

		setServicesAttribute(process, request);
		request.getRequestDispatcher("/start.jsp").forward(request, response);
	}

	private void setServicesAttribute(Process process, HttpServletRequest request) {
		try {
			request.setAttribute("services", process.getAllServices());
			request.setAttribute("employees", process.getAllEmployees());
		} catch (Exception e) {
			request.setAttribute("error", "Could not get services");
			e.printStackTrace();
		}
	}

	
}
