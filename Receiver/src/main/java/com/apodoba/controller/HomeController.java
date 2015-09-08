package com.apodoba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	
//	@Autowired
//	private CustomerDao customerDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		//model.addAttribute("customers", customerDao.getAllCustomers());
		return "home";
	}
}
