package com.mentoring.hibernate.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentoring.hibernate.domain.Unit;

@Repository
@Transactional
public class UnitDaoImpl implements UnitDao{

	@Autowired
	private EntityManager em;
	
	@Override
	public void addUnit(Unit unit) {
		em.persist(unit);
		
	}

	@Override
	public void deleteUnit(long id) {
		Unit unit = em.find(Unit.class, id);
		em.remove(unit);
	}

	@Override
	public List<Unit> getAllUnits() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Unit> criteria = cb.createQuery(Unit.class);
		Root<Unit> member = criteria.from(Unit.class);

		criteria.select(member).orderBy(cb.asc(member.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void editUnit(Unit unit) {
		Unit newUnit = em.find(Unit.class, unit.getId());
		newUnit.setName(unit.getName());
		newUnit.setDescription(unit.getDescription());
		em.merge(newUnit);
	}

	@Override
	public Unit getUnit(long id) {
		return em.find(Unit.class, id);
	}

}
