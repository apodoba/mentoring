package com.apodoba.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apodoba.domain.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		return sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
	}

	@Override
	public void deleteCustomer(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		sessionFactory.getCurrentSession().merge(customer);
	}

	@Override
	public void addCustomer(Customer customer) {
		sessionFactory.getCurrentSession().persist(customer);		
	}

	@Override
	public Customer getCustomer(long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("id", id));
		return (Customer) criteria.uniqueResult();
		
	}

}
