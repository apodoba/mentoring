package com.mentoring.hibernate.repo;

import java.util.List;

import com.mentoring.hibernate.domain.Unit;

public interface UnitDao {
	
	public void addUnit(Unit unit);

	public void deleteUnit(long id);
	
	public List<Unit> getAllUnits();
	
	public void editUnit(Unit unit);
	
	public Unit getUnit(long id);
	
}
