package com.apodoba.entiry;

public class EmployeeImpl implements Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private String street;
	private String flat;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;

	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String getFlat() {
		return flat;
	}

	@Override
	public void setFlat(String flat) {
		this.flat = flat;
	}

}
