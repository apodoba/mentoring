package com.apodoba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apodoba.domain.User;
import com.apodoba.service.UserService;

@Controller
@RequestMapping(value = "/")
public class SenderController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("newUser", new User());
		model.addAttribute("editUser", userService.getAllUsers().size()>0  ? userService.getAllUsers().get(0) : new User());
		return "home";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("newUser") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

	@RequestMapping(value = "editUser", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("editUser") User editUser) {
		if (editUser.getId() == null) {
			userService.addUser(editUser);
		} else {
			userService.updateUser(editUser);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("id") Long id, Model model) {
		model.addAttribute("editUser", userService.getUser(id));
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("newUser", new User());
		return "home";
	}

}
