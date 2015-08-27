package com.apodoba.entiry.adapter;

import com.apodoba.annotation.DBField;
import com.apodoba.annotation.DBPrimaryKey;
import com.apodoba.annotation.DBTable;
import com.apodoba.entiry.Employee;
import com.apodoba.entiry.User;

@DBTable(name="User")
public class EmployeeAdapter implements User{
	
	@DBPrimaryKey
	@DBField(name="id")
	private int id;
	
	@DBField(name="name")
	private String name;
	
	@DBField(name="address")
	private String address;
	
	public EmployeeAdapter(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getFirstName() + ";" + employee.getLastName();
		this.address = employee.getCity() + ";" + employee.getStreet() + ";" + employee.getFlat();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

}
