package com.mentoring.hibernate.form;

import org.springframework.ui.Model;

import com.mentoring.hibernate.domain.Employee;
import com.mentoring.hibernate.domain.Project;
import com.mentoring.hibernate.domain.Unit;
import com.mentoring.hibernate.repo.EmployeeDao;
import com.mentoring.hibernate.repo.ProjectDao;
import com.mentoring.hibernate.repo.UnitDao;

public class Utils {

	public static void addAttributes(Model model, ProjectDao projectDao, UnitDao unitDao, EmployeeDao employeeDao){
		model.addAttribute("newProject", new Project());
		model.addAttribute("unitEmployeeForm", new UnitEmployeeForm());
		model.addAttribute("projectEmployeeForm", new ProjectEmployeeForm());
		model.addAttribute("newUnit", new Unit());
		model.addAttribute("employee", new Employee());
		model.addAttribute("projects", projectDao.getAllProjects());	
		model.addAttribute("units", unitDao.getAllUnits());
		model.addAttribute("employees", employeeDao.getAllEmployees());
	}
	
	public static void addEditableAttributes(Model model, ProjectDao projectDao, UnitDao unitDao, EmployeeDao employeeDao){
		if(!model.containsAttribute("editProject")){
			model.addAttribute("editProject", projectDao.getAllProjects().size()>0  ? projectDao.getAllProjects().get(0) : new Project());
		}
		if(!model.containsAttribute("editUnit")){
			model.addAttribute("editUnit", unitDao.getAllUnits().size()>0  ? unitDao.getAllUnits().get(0) : new Unit());
		}
		if(!model.containsAttribute("editEmployee")){
			model.addAttribute("editEmployee", employeeDao.getAllEmployees().size() >0  ? employeeDao.getAllEmployees().get(0) : new Employee());
		}
	
	}
	
}
