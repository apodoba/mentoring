package com.apodoba.shop.beans;

import java.util.List;

import javax.ejb.Local;

import com.apodoba.shop.domain.shop.Audit;
import com.apodoba.shop.domain.shop.Service;
import com.apodoba.shop.domain.shop.User;

@Local
public interface ShopBean {

	public void saveAudit(Audit audit);
	
	public User getUser(String mail, String password);
	
	public List<Service> getAllServices();
	
	public User getUserByEmail(String mail);
	
	public Service getServiceById(int id);
}

