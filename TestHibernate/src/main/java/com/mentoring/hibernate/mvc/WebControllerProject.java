package com.mentoring.hibernate.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mentoring.hibernate.domain.Employee;
import com.mentoring.hibernate.domain.Project;
import com.mentoring.hibernate.domain.Unit;
import com.mentoring.hibernate.repo.EmployeeDao;
import com.mentoring.hibernate.repo.ProjectDao;
import com.mentoring.hibernate.repo.UnitDao;

@Controller
@RequestMapping(value = "/")
public class WebControllerProject {
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private UnitDao unitDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		System.out.println("GET");
		Utils.addEditableAttributes(model, projectDao, unitDao, employeeDao);
		Utils.addAttributes(model, projectDao, unitDao, employeeDao);
		
		return "index";
	}

	@RequestMapping(value="project/add", method = RequestMethod.POST)
	public String addNewProject(@Valid @ModelAttribute("newProject") Project newProject, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			projectDao.addProject(newProject);
			return "redirect:/";
		} else {
			return "index";
		}
	}
	
	@RequestMapping(value="project/delete", method = RequestMethod.POST)
	public String deleteProject(@ModelAttribute("id") Long id, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			projectDao.deleteProject(id);
			return "redirect:/";
		} else {
			return "index";
		}
	}
	
	@RequestMapping(value="project/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("id") Long id, BindingResult result, Model model) {
		model.addAttribute("editProject", projectDao.getProject(id));
		model.addAttribute("editUnit", unitDao.getAllUnits().size()>0  ? unitDao.getAllUnits().get(0) : new Unit());
		model.addAttribute("editEmployee", new Employee());
		
		Utils.addAttributes(model, projectDao, unitDao, employeeDao);
		return "index";
	}
	
	@RequestMapping(value="project/editProject", method = RequestMethod.POST)
	public String editProject(@Valid @ModelAttribute("editProject") Project editProject, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			projectDao.editProject(editProject);
			return "redirect:/";
		} else {
			return "index";
		}
	}
	
}
