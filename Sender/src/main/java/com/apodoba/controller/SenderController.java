package com.apodoba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apodoba.domain.CinemaSchedule;
import com.apodoba.domain.Ticket;

@Controller
@RequestMapping(value = "/")
public class SenderController {
	
	@Autowired
	private CinemaSchedule cinemaSchedule;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
//		model.addAttribute("newUser", new Ticket());
		cinemaSchedule.getAllFilmSchedules();
		return "home";
	}

	@RequestMapping(value = "send", method = RequestMethod.GET)
	public String sendMessage(Model model) {
		model.addAttribute("newUser", new Ticket());
		//userService.sendMessage();
		return "home";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("newUser") Ticket user) {
		//userService.addUser(user);
		return "redirect:/";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("id") Long id) {
		//userService.deleteUser(id);
		return "redirect:/";
	}
}
