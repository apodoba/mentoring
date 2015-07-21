package com.mentoring.hibernate.repo;

import java.util.List;

import com.mentoring.hibernate.domain.Project;

public interface ProjectDao {

	public void addProject(Project project);

	public void deleteProject(long id);
	
	public List<Project> getAllProjects();
	
	public void editProject(Project project);
	
	public Project getProject(long id);
}
