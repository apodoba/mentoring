package com.mentoring.hibernate.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentoring.hibernate.domain.Project;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Project> getAllProjects() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Project> criteria = cb.createQuery(Project.class);
		Root<Project> member = criteria.from(Project.class);

		criteria.select(member).orderBy(cb.asc(member.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void addProject(Project project) {
		em.persist(project);
	}

	@Override
	public void deleteProject(long id) {
		Project project = em.find(Project.class, id);
		em.remove(project);
	}

	@Override
	public void editProject(Project project) {
		Project newProject = em.find(Project.class, project.getId());
		newProject.setName(project.getName());
		newProject.setDescription(project.getDescription());
		em.merge(project);
	}

	@Override
	public Project getProject(long id) {
		return em.find(Project.class, id);
	}

}
