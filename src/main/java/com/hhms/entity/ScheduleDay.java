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
 * schedule_day table
 * Employee Schedule business
 * @author Marco
 * @version January 31, 2023
 */
@Entity
@Table(name="schedule_day")
public class ScheduleDay {

	@Id
	@SequenceGenerator(name="schedule_day_seq",sequenceName="schedule_day_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schedule_day_seq")
	@Column(name = "schedule_day_id")
	private Long ScheduleDayId;

    @JoinColumn(name = "schedule_period_id", referencedColumnName = "schedule_period_id")
    @ManyToOne(optional = false)
	private SchedulePeriod schedulePeriod;

    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
	private Employee employee;
	
	@Column(name = "day")
	private Date day;

	@Column(name = "note")
	private String note;
	
	@Column(name = "state")
	private String state;

	public Long getScheduleDayId() {
		return ScheduleDayId;
	}

	public void setScheduleDayId(Long scheduleDayId) {
		ScheduleDayId = scheduleDayId;
	}

	public SchedulePeriod getSchedulePeriod() {
		return schedulePeriod;
	}

	public void setSchedulePeriod(SchedulePeriod schedulePeriod) {
		this.schedulePeriod = schedulePeriod;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ScheduleDay [ScheduleDayId=" + ScheduleDayId + ", schedulePeriod=" + schedulePeriod + ", employee="
				+ employee + ", day=" + day + ", note=" + note + ", state=" + state + "]";
	}
	
	
}
