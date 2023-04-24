package com.hhms.entity;

import java.io.Serializable;

/**
 * Object that represent a token in jwt. It is sent to front
 * @author Marco
 *
 */
public class TokenInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String jwtToken;
  
	private Long managerId;
	private String firstName;
	private String lastName;
	private String username;
	private Long employeeCategoryId;
	private Long employeeId;
	private String punchNumber;
  

	public TokenInfo(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}
	
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
	
	public Long getEmployeeCategoryId() {
		return employeeCategoryId;
	}
	
	public void setEmployeeCategoryId(Long employeeCategoryId) {
		this.employeeCategoryId = employeeCategoryId;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getPunchNumber() {
		return punchNumber;
	}
	
	public void setPunchNumber(String punchNumber) {
		this.punchNumber = punchNumber;
	}

	@Override
	public String toString() {
		return "TokenInfo [jwtToken=" + jwtToken + "]";
	}
	  
  
}
