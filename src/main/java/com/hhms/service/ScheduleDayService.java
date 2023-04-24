package com.hhms.service;

import java.sql.Date;
import java.util.List;

import com.hhms.entity.Department;
import com.hhms.entity.Employee;
import com.hhms.entity.ScheduleDay;

public interface ScheduleDayService {

	
	public List<ScheduleDay> getCurrentPeriod(Long departmentId) throws Exception;
	public List<ScheduleDay> getByPeriod(Date date, Long departmentId) throws Exception;
	
	public List<Employee> getEmployeeByCurrentPeriod(Long departmentId) throws Exception;
	public List<Employee> getEmployeeBySchedulePeriod(Long departmentId) throws Exception;
	
	public List<Department> listHiddenDepartments(Date myDate, String punchNumber) throws Exception;
	public List<Department> listShownDepartments(Date myDate, String punchNumber) throws Exception;
}
