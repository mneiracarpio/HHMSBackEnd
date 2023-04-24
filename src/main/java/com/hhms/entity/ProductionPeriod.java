package com.hhms.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * production_period table, represents a period when then production is evaluated 
 * Production business
 * @author Marco
 *
 */
@Entity
@Table(name="production_period")
public class ProductionPeriod {

	@Id
	@SequenceGenerator(name="production_period_seq",sequenceName="production_period_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="production_period_seq")
	@Column(name = "production_period_id")
	private Long productionPeriodId;
	
	@Column(name = "production_period")
	private Long productionPeriod;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "production_index")
	private Double productionIndex;
	
	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "labor_factor")
	private Double laborFactor;
	
	@Column(name = "state")
	private String state;
	
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
	@ManyToOne(optional = false)
	private Department department;
		

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getProductionPeriodId() {
		return productionPeriodId;
	}

	public void setProductionPeriodId(Long productionPeriodId) {
		this.productionPeriodId = productionPeriodId;
	}

	public Long getProductionPeriod() {
		return productionPeriod;
	}

	public void setProductionPeriod(Long productionPeriod) {
		this.productionPeriod = productionPeriod;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getProductionIndex() {
		return productionIndex;
	}

	public void setProductionIndex(Double productionIndex) {
		this.productionIndex = productionIndex;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getLaborFactor() {
		return laborFactor;
	}

	public void setLaborFactor(Double laborFactor) {
		this.laborFactor = laborFactor;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
