package com.hhms.entity;

/**
 * deprecated class
 * the object used for login previous to jwt implementation. It's not used any more.
 * @author Marco
 *
 */
public class Login {
	private String punchNumber;
	private String userName;
	private String password;
	
	public Login() {
		super();
	}
	
	public String getPunchNumber() {
		return punchNumber;
	}
	public void setPunchNumber(String punchNumber) {
		this.punchNumber = punchNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
