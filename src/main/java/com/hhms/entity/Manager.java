package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Manager table, represents hhms manager
 * @author Marco
 *
 */
@Entity
@Table(name="manager")
public class Manager {

	@Id
	@SequenceGenerator(name="manager_seq",sequenceName="manager_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="manager_seq")
	@Column(name = "manager_id")
	private Long managerId;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

    @JoinColumn(name = "employee_category_id", referencedColumnName = "employee_category_id")
    @ManyToOne(optional = false)
    private EmployeeCategory employeeCategory;

	@Column(name = "email")
	private String email;

	@Column(name = "state")
	private String state;
	
	@Lob
	@Column(name = "picture")
    private byte[] picture;

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		//this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));
		this.password = password;
	}

	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(EmployeeCategory employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", employeeCategory=" + employeeCategory + ", email=" + email
				+ ", state=" + state + "]";
	}
	
}
