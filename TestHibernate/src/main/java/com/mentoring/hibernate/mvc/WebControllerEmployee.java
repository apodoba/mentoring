package com.mentoring.hibernate.mvc;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mentoring.hibernate.domain.Employee;
import com.mentoring.hibernate.domain.PersonalInfo;
import com.mentoring.hibernate.domain.Project;
import com.mentoring.hibernate.domain.Unit;
import com.mentoring.hibernate.repo.EmployeeDao;
import com.mentoring.hibernate.repo.PersonalInfoDao;
import com.mentoring.hibernate.repo.ProjectDao;
import com.mentoring.hibernate.repo.UnitDao;

@Controller
@RequestMapping(value = "employee/")
public class WebControllerEmployee {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private UnitDao unitDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String addNewEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		employee.setRegistrationDate(new Date());
			
		PersonalInfo personalInfo = employee.getPersonalInfo();
		personalInfo.setEmployee(employee);
			
		employeeDao.addEmployee(employee);
//		personalInfoDao.addPersonalInfo(personalInfo);
		return "redirect:/";
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String deleteProject(@ModelAttribute("id") Long id, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			employeeDao.deleteEmployee(id);
			return "redirect:/";
		} else {
			return "index";
		}
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("id") Long id, BindingResult result, Model model) {
		model.addAttribute("editUnit", unitDao.getAllUnits().size()>0  ? unitDao.getAllUnits().get(0) : new Unit());
		model.addAttribute("editProject", projectDao.getAllProjects().size()>0  ? projectDao.getAllProjects().get(0) : new Project());
		model.addAttribute("editEmployee", employeeDao.getEmployee(id));
		
		System.out.println("Get employee "+employeeDao.getEmployee(id) + " "+id);
		
		Utils.addAttributes(model, projectDao, unitDao, employeeDao);
		return "index";
	}
	
	@RequestMapping(value="editEmployee", method = RequestMethod.POST)
	public String editProject(@Valid @ModelAttribute("editEmployee") Employee employee, BindingResult result, Model model) {
		employeeDao.editEmployee(employee);
		return "redirect:/";
	}
}
