package com.hhms.entity;

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
@Table(name="biweekly_payroll")
public class BiweeklyPayroll {

	@Id
	@SequenceGenerator(name="biweekly_payroll_seq",sequenceName="biweekly_payroll_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="biweekly_payroll_seq")
	@Column(name = "biweekly_payroll_id")
	private Long biweeklyPayrollId;
	
	@JoinColumn(name = "payroll_period_id", referencedColumnName = "payroll_period_id")
    @ManyToOne(optional = false)
	private PayrollPeriod payrollPeriod;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
	private Employee employee;
	
	@Column(name = "hours")
	private double hours;
	
	@Column(name = "labor_index")
	private double laborIndex;

	@Column(name = "state")
	private String state;

	public Long getBiweeklyPayrollId() {
		return biweeklyPayrollId;
	}

	public void setBiweeklyPayrollId(Long biweeklyPayrollId) {
		this.biweeklyPayrollId = biweeklyPayrollId;
	}

	public PayrollPeriod getPayrollPeriod() {
		return payrollPeriod;
	}

	public void setPayrollPeriod(PayrollPeriod payrollPeriod) {
		this.payrollPeriod = payrollPeriod;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		return "BiweeklyPayroll [biweeklyPayrollId=" + biweeklyPayrollId + ", payrollPeriod=" + payrollPeriod
				+ ", employee=" + employee + ", hours=" + hours + ", laborIndex=" + laborIndex + ", state=" + state
				+ "]";
	}

		
}
