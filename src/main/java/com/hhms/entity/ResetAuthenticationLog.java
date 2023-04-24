package com.hhms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="reset_authentication_log")
public class ResetAuthenticationLog {

	@Id
	@SequenceGenerator(name="reset_authentication_log_seq",sequenceName="reset_authentication_log_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reset_authentication_log_seq")
	@Column(name = "reset_authentication_log_id")
	private Long resetAuthenticationLogId;
	
	@Column(name = "log_time")
	private Timestamp logTime;
	
	@Column(name = "manager_id")
	private Long manager_id;
	
	@Column(name = "email")
	private String email;

    @JoinColumn(name = "operation_id", referencedColumnName = "operation_id")
    @ManyToOne(optional = false)
    private Operation operation;

	public Long getResetAuthenticationLogId() {
		return resetAuthenticationLogId;
	}

	public void setResetAuthenticationLogId(Long resetAuthenticationLogId) {
		this.resetAuthenticationLogId = resetAuthenticationLogId;
	}

	public Timestamp getLogTime() {
		return logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

}
