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

@Entity
@Table(name="payroll_period")
public class PayrollPeriod {

	@Id
	@SequenceGenerator(name="payroll_period_seq",sequenceName="payroll_period_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="payroll_period_seq")
	@Column(name = "payroll_period_id")
	private Long payrollPeriodId;
	
	@Column(name = "payroll_period")
	private Long payrollPeriod;
	
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne(optional = false)
	private Department department;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "hour_factor")
	private double hourFactor;
	
	@Column(name = "hours")
	private double hours;

	@Column(name = "labor_index")
	private double laborIndex;

	@Column(name = "state")
	private String state;

	public Long getPayrollPeriodId() {
		return payrollPeriodId;
	}

	public void setPayrollPeriodId(Long payrollPeriodId) {
		this.payrollPeriodId = payrollPeriodId;
	}

	public Long getPayrollPeriod() {
		return payrollPeriod;
	}

	public void setPayrollPeriod(Long payrollPeriod) {
		this.payrollPeriod = payrollPeriod;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public double getHourFactor() {
		return hourFactor;
	}

	public void setHourFactor(double hourFactor) {
		this.hourFactor = hourFactor;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getLaborIndex() {
		return laborIndex;
	}

	public void setLaborIndex(double laborIndex) {
		this.laborIndex = laborIndex;
	}

	@Override
	public String toString() {
		return "PayrollPeriod [payrollPeriodId=" + payrollPeriodId + ", payrollPeriod=" + payrollPeriod
				+ ", department=" + department + ", startDate=" + startDate + ", endDate=" + endDate + ", hourFactor="
				+ hourFactor + ", hours=" + hours + ", laborIndex=" + laborIndex + ", state=" + state + "]";
	}
	
	
}
