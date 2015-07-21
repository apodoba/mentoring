package com.mentoring.hibernate.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentoring.hibernate.domain.Employee;
import com.mentoring.hibernate.domain.PersonalInfo;

@Repository
@Transactional
public class PersonalInfoDaoImpl implements PersonalInfoDao{
	
	@Autowired
	private EntityManager em;

	@Override
	public void addPersonalInfo(PersonalInfo personalInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editEmployee(Employee project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
