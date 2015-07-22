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
import com.mentoring.hibernate.form.Utils;
import com.mentoring.hibernate.repo.EmployeeDao;
import com.mentoring.hibernate.repo.ProjectDao;
import com.mentoring.hibernate.repo.UnitDao;

@Controller
@RequestMapping(value = "unit/")
public class WebControllerUnit {

	@Autowired
	private UnitDao unitDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String addNewProject(@Valid @ModelAttribute("newUnit") Unit newUnit, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			unitDao.addUnit(newUnit);
			return "redirect:/";
		} else {
			return "index";
		}
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String deleteProject(@ModelAttribute("id") Long id, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			unitDao.deleteUnit(id);
			return "redirect:/";
		} else {
			return "index";
		}
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("id") Long id, BindingResult result, Model model) {
		model.addAttribute("editUnit", unitDao.getUnit(id));
		model.addAttribute("editProject", projectDao.getAllProjects().size()>0  ? projectDao.getAllProjects().get(0) : new Project());
		model.addAttribute("editEmployee", employeeDao.getAllEmployees().size() >0  ? employeeDao.getAllEmployees().get(0) : new Employee());
		
		Utils.addAttributes(model, projectDao, unitDao, employeeDao);
		return "index";
	}
	
	@RequestMapping(value="editUnit", method = RequestMethod.POST)
	public String editProject(@Valid @ModelAttribute("editUnit") Unit editUnit, BindingResult result, Model model) {
		if(editUnit.getId() == null){
			unitDao.addUnit(editUnit);
		}else {
			unitDao.editUnit(editUnit);
		}	
		return "redirect:/";
	}
}
