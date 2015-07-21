package com.mentoring.hibernate.repo;

import java.util.List;

import com.mentoring.hibernate.domain.Employee;
import com.mentoring.hibernate.domain.PersonalInfo;

public interface PersonalInfoDao {
	
	public void addPersonalInfo(PersonalInfo personalInfo);

	public void deleteEmployee(long id);

	public List<Employee> getAllEmployees();

	public void editEmployee(Employee project);

	public Employee getEmployee(long id);
}
