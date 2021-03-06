package com.apodoba.shop.domain.shop;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name = "User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 645670100361647770L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String password;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String mail;
	
	@Column
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("firstName", firstName).append("lastName", lastName)
				.append("mail", mail).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		User user = (User) obj;
		return new EqualsBuilder().append(id, user.id)
				.append(lastName, user.lastName)
				.append(firstName, user.firstName).append(mail, user.mail)
				.append(password, user.password).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).append(lastName)
				.append(firstName).append(password).append(mail).toHashCode();
	}

}
