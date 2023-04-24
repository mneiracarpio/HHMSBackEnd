package com.hhms.entity;

/**
 * Object used to response messages to front when apis fails 
 * @author Marco
 *
 */

public class MessageResponse {
	  private String message;

	  public MessageResponse(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
