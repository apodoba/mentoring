package com.apodoba.dao;

import java.util.List;

import com.apodoba.domain.Customer;

public interface CustomerDao {
	
	List<Customer> getAllCustomers();
	
	void deleteCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void addCustomer(Customer customer);
	
	Customer getCustomer(long id);
	
}
