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
@Table(name="daily_payroll")
public class DailyPayroll {

	@Id
	@SequenceGenerator(name="daily_payroll_seq",sequenceName="daily_payroll_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="daily_payroll_seq")
	@Column(name = "daily_payroll_id")
	private Long dailyPayrollId;
	
	//@Column(name = "biweekly_payroll_id")
	@JoinColumn(name = "biweekly_payroll_id", referencedColumnName = "biweekly_payroll_id")
    @ManyToOne(optional = false)
	private BiweeklyPayroll biweeklyPayroll;
	
	@Column(name = "day")
	private Date day;
	
	@Column(name = "hours")
	private double hours;
	
	@Column(name = "state")
	private String state;

	public Long getDailyPayrollId() {
		return dailyPayrollId;
	}

	public void setDailyPayrollId(Long dailyPayrollId) {
		this.dailyPayrollId = dailyPayrollId;
	}

	public BiweeklyPayroll getBiweeklyPayroll() {
		return biweeklyPayroll;
	}

	public void setBiweeklyPayroll(BiweeklyPayroll biweeklyPayroll) {
		this.biweeklyPayroll = biweeklyPayroll;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
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

	@Override
	public String toString() {
		return "DailyPayroll [dailyPayrollId=" + dailyPayrollId + ", biweeklyPayroll=" + biweeklyPayroll + ", day="
				+ day + ", hours=" + hours + ", state=" + state + "]";
	}
	
}
