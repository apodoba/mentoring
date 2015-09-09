package com.apodoba.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apodoba.domain.CinemaSchedule;
import com.apodoba.domain.FilmSchedule;
import com.apodoba.domain.Ticket;
import com.apodoba.exception.CinemaNotAvailable;
import com.apodoba.exception.HallNotAvailable;

@Controller
@RequestMapping(value = "/")
public class SenderController {
	
	@Autowired
	private CinemaSchedule cinemaSchedule;
	
	@Autowired 
	private ApplicationContext applicationContext;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		try {
			List<FilmSchedule> filmSchedules = cinemaSchedule.getAllFilmSchedules();
			model.addAttribute("filmSchedules", filmSchedules);
			model.addAttribute("error", "");
		} catch (CinemaNotAvailable e) {
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "cinema";
	}

	@RequestMapping(value = "book", method = RequestMethod.POST)
	public String bookTicket(@ModelAttribute("id") long ticketId, @ModelAttribute("film") String film, @ModelAttribute("time") String time, Model model, RedirectAttributes redirectAttributes) {
		Ticket ticket = applicationContext.getBean(Ticket.class);
		ticket.setId(ticketId);
		boolean result = ticket.reserve();
		
		redirectAttributes.addAttribute("film", film);
		redirectAttributes.addAttribute("time", time);
		redirectAttributes.addAttribute("result", result ? "Reserved successfull" : "Could not reserve ticket");
		return "redirect:/places";
	}
	
	@RequestMapping(value = "pay", method = RequestMethod.POST)
	public String payForTicket(@ModelAttribute("id") long ticketId, @ModelAttribute("film") String film, @ModelAttribute("time") String time, Model model, RedirectAttributes redirectAttributes) {
		Ticket ticket = applicationContext.getBean(Ticket.class);
		ticket.setId(ticketId);
		boolean result = ticket.pay();
		
		redirectAttributes.addAttribute("film", film);
		redirectAttributes.addAttribute("time", time);
		redirectAttributes.addAttribute("result", result ? "Payment successfull" : "Could not pay for ticket");
		return "redirect:/places";
	}

	@RequestMapping(value = "places", method = {RequestMethod.POST, RequestMethod.GET})
	public String deleteUser(@ModelAttribute("film") String film, @ModelAttribute("time") String time, @ModelAttribute("result") String result, Model model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		
		FilmSchedule filmSchedule = applicationContext.getBean(FilmSchedule.class);
		filmSchedule.setFilm(film);
		filmSchedule.setTime(format.parse(time));
		try {
			Map<String, List<Ticket>> hallTickets = new HashMap<String, List<Ticket>>();
			for(Ticket ticket: filmSchedule.getTickets()){
				if(!hallTickets.keySet().contains(ticket.getHall())){
					hallTickets.put(ticket.getHall(), new ArrayList<Ticket>());
				}
				hallTickets.get(ticket.getHall()).add(ticket);
			}
			model.addAttribute("halls", hallTickets);
			model.addAttribute("result", (result == null || result.isEmpty()) ? "" : result);
		} catch (HallNotAvailable e) {
			model.addAttribute("result", e.getMessage());
			e.printStackTrace();
		}
		return "film";
	}
}
