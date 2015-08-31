package com.apodoba.entiry;

import com.apodoba.annotation.DBField;
import com.apodoba.annotation.DBPrimaryKey;
import com.apodoba.annotation.DBTable;

@DBTable(name="Service")
public class Service{
	
	@DBPrimaryKey
	@DBField(name="id")
	private int id;
	
	@DBField(name="name")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
