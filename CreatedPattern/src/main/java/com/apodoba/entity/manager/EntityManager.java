package com.apodoba.entity.manager;

import java.util.List;


public interface EntityManager {

	public <T> void delete(T entity) throws Exception;

	public <T> void update(T entity) throws Exception;

	public <T> void insert(T entity) throws Exception;

	public <T> List<T> selectAll(Class<T> entityClass) throws Exception;

}
