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
import com.mentoring.hibernate.form.ProjectEmployeeForm;
import com.mentoring.hibernate.form.UnitEmployeeForm;
import com.mentoring.hibernate.form.Utils;
import com.mentoring.hibernate.repo.EmployeeDao;
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
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String addNewEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		employee.setRegistrationDate(new Date());
			
		PersonalInfo personalInfo = employee.getPersonalInfo();
		personalInfo.setEmployee(employee);
			
		employeeDao.addEmployee(employee);
		return "redirect:/";
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String deleteEmployee(@ModelAttribute("id") Long id, BindingResult result, Model model) {
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
		
		Utils.addAttributes(model, projectDao, unitDao, employeeDao);
		return "index";
	}
	
	@RequestMapping(value="editEmployee", method = RequestMethod.POST)
	public String editEmployee(@Valid @ModelAttribute("editEmployee") Employee employee, BindingResult result, Model model) {
		if(employee.getId() == null){
			employeeDao.addEmployee(employee);
		}else {
			employeeDao.editEmployee(employee);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="assign/unit", method = RequestMethod.POST)
	public String addEmployeeToUnit(@Valid @ModelAttribute("unitEmployeeForm") UnitEmployeeForm unitEmployeeForm, BindingResult result, Model model) {
		employeeDao.assignEmployeeToUnit(unitEmployeeForm.getEmployeeId(), unitEmployeeForm.getUnitId());
		return "redirect:/";
	}
	
	@RequestMapping(value="assign/project", method = RequestMethod.POST)
	public String addEmployeeToProject(@Valid @ModelAttribute("projectEmployeeForm") ProjectEmployeeForm projectEmployeeForm, BindingResult result, Model model) {
		employeeDao.assignEmployeeToProject(projectEmployeeForm.getEmployeeId(), projectEmployeeForm.getProjectId());
		return "redirect:/";
	}
}
