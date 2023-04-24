package com.hhms.entity;

import java.io.Serializable;

/** Object that back get from back with the username and password for login
 * 
 */
public class AuthenticationReq implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;

  private String password;

  public AuthenticationReq(String username, String password) {
    this.username = username;
    this.password = password;
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
    this.password = password;
  }
}
