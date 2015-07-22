package com.mentoring.hibernate.repo;

import java.util.List;

import com.mentoring.hibernate.domain.Employee;

public interface EmployeeDao {
	
	public void addEmployee(Employee employee);

	public void deleteEmployee(long id);
	
	public List<Employee> getAllEmployees();
	
	public void editEmployee(Employee project);
	
	public Employee getEmployee(long id);
	
	public void assignEmployeeToUnit(Long employeeId, Long unitId);

	public void assignEmployeeToProject(Long employeeId, Long projectId);
}
