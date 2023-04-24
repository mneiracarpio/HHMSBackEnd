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
 * schedule_period table
 * Employee Schedule business
 * @author Marco
 * @version January 31, 2023
 */
@Entity
@Table(name="schedule_period")
public class SchedulePeriod {

	@Id
	@SequenceGenerator(name="schedule_period_seq",sequenceName="schedule_period_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schedule_period_seq")
	@Column(name = "schedule_period_id")
	private Long SchedulePeriodId;
	
	@Column(name = "schedule_period")
	private Long schedulePeriod;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
		
	@Column(name = "state")
	private String state;
	
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
	@ManyToOne(optional = false)
	private Department department;

	public Long getSchedulePeriodId() {
		return SchedulePeriodId;
	}

	public void setSchedulePeriodId(Long schedulePeriodId) {
		SchedulePeriodId = schedulePeriodId;
	}

	public Long getSchedulePeriod() {
		return schedulePeriod;
	}

	public void setSchedulePeriod(Long schedulePeriod) {
		this.schedulePeriod = schedulePeriod;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
		
}
