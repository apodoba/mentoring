package com.mentoring.hibernate.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentoring.hibernate.domain.Employee;
import com.mentoring.hibernate.domain.PersonalInfo;
import com.mentoring.hibernate.domain.Project;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	private EntityManager em;

	@Override
	public void addEmployee(Employee employee) {
		em.persist(employee);
	}

	@Override
	public void deleteEmployee(long id) {
		Employee employee = em.find(Employee.class, id);
		em.remove(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> member = criteria.from(Employee.class);

		criteria.select(member).orderBy(cb.asc(member.get("registrationDate")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void editEmployee(Employee employee) {
		System.out.println("employee.getId() "+employee.getId());
		System.out.println("employee.getId() "+employee.getPersonalInfo().getFirstName());
		
		Employee originalEmployee = em.find(Employee.class, employee.getId());
		employee.setRegistrationDate(originalEmployee.getRegistrationDate());
		
		employee.getPersonalInfo().setId(originalEmployee.getPersonalInfo().getId());
		employee.setRegistrationDate(originalEmployee.getRegistrationDate());
		
		em.merge(employee);
		em.merge(originalEmployee.getPersonalInfo());
	}

	@Override
	public Employee getEmployee(long id) {
		return em.find(Employee.class, id);
	}

}
