package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operation")
public class Operation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "operation_id")
	private Long operationId;

	@Column(name = "description")
	private String description;

	@Column(name = "state")
	private String state;

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Operation [operationId=" + operationId + ", description=" + description + ", state=" + state + "]";
	}
	
}
