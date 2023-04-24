package com.hhms.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="payroll_x_employee")
public class PayrollByEmployee {


	@OneToMany
	@JoinColumn(name = "biweekly_payroll_id")
	private List<DailyPayroll> dailyPayroll;

	public List<DailyPayroll> getDailyPayroll() {
		return dailyPayroll;
	}

	public void setDailyPayroll(List<DailyPayroll> dailyPayroll) {
		this.dailyPayroll = dailyPayroll;
	}
	
	@Column(name = "payroll_period_id")
	private Long payrollPeriodId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "biweekly_payroll_id")
	private Long biweeklyPayrollId;
	
	
	@Column(name = "start_date")
    private Date startDate;
	
	@Column(name = "end_date")
    private Date endDate;
	
	@Column(name = "hour_factor")
    private double hourFactor;
	
	@Column(name = "biweekly_hours")
    private double biweeklyHours;

	@Column(name = "biweekly_labor_index")
    private double biweeklyLaborIndex;
	
	@Column(name = "total_hours")
    private double totalHours;
	
	@Column(name = "total_labor_index")
    private double totalLaborIndex;
	
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @ManyToOne(optional = true)
    private Employee employee;
    
    


	public Long getPayrollPeriodId() {
		return payrollPeriodId;
	}

	public void setPayrollPeriodId(Long payrollPeriodId) {
		this.payrollPeriodId = payrollPeriodId;
	}

	public Long getBiweeklyPayrollId() {
		return biweeklyPayrollId;
	}

	public void setBiweeklyPayrollId(Long biweeklyPayrollId) {
		this.biweeklyPayrollId = biweeklyPayrollId;
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

	public double getBiweeklyHours() {
		return biweeklyHours;
	}

	public void setBiweeklyHours(double biweeklyHours) {
		this.biweeklyHours = biweeklyHours;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public double getBiweeklyLaborIndex() {
		return biweeklyLaborIndex;
	}

	public void setBiweeklyLaborIndex(double biweeklyLaborIndex) {
		this.biweeklyLaborIndex = biweeklyLaborIndex;
	}

	public double getTotalLaborIndex() {
		return totalLaborIndex;
	}

	public void setTotalLaborIndex(double totalLaborIndex) {
		this.totalLaborIndex = totalLaborIndex;
	}

	@Override
	public String toString() {
		return "PayrollByEmployee [dailyPayroll=" + dailyPayroll + ", payrollPeriodId=" + payrollPeriodId
				+ ", biweeklyPayrollId=" + biweeklyPayrollId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", hourFactor=" + hourFactor + ", biweeklyHours=" + biweeklyHours + ", biweeklyLaborIndex="
				+ biweeklyLaborIndex + ", totalHours=" + totalHours + ", totalLaborIndex=" + totalLaborIndex
				+ ", employee=" + employee + "]";
	}


	
	
}
