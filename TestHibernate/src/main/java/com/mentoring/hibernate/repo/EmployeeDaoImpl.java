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
import com.mentoring.hibernate.domain.Project;
import com.mentoring.hibernate.domain.Unit;

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
		Employee originalEmployee = em.find(Employee.class, employee.getId());
		employee.setRegistrationDate(originalEmployee.getRegistrationDate());
		
		employee.getPersonalInfo().setId(originalEmployee.getPersonalInfo().getId());
		employee.getPersonalInfo().setEmployee(employee);
		
		em.merge(employee);
	}
	
	@Override
	public void assignEmployeeToUnit(Long employeeId, Long unitId) {
		Employee employee = em.find(Employee.class, employeeId);
		Unit unit = em.find(Unit.class, unitId); 
		employee.setUnit(unit);
		em.merge(employee);
	}

	@Override
	public Employee getEmployee(long id) {
		return em.find(Employee.class, id);
	}

	@Override
	public void assignEmployeeToProject(Long employeeId, Long projectId) {
		Employee employee = em.find(Employee.class, employeeId);
		Project project = em.find(Project.class, projectId); 
		if(!employee.getProjects().contains(project)){
			employee.addProject(project);
			em.merge(employee);
		}
	}

}
