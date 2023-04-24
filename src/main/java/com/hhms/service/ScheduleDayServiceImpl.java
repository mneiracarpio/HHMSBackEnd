package com.hhms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Department;
import com.hhms.entity.Employee;
import com.hhms.entity.ScheduleDay;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.DepartmentRepository;
import com.hhms.repository.ScheduleDayRepository;
import com.hhms.repository.SchedulePeriodRepository;

@Service
public class ScheduleDayServiceImpl  extends BaseServiceImpl<ScheduleDay, Long> implements ScheduleDayService {

	public ScheduleDayServiceImpl(BaseRepository<ScheduleDay, Long> baseRepository) {
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SchedulePeriodRepository schedulePeriodRepository;

	@Autowired
	private ScheduleDayRepository scheduleDayRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override 
	public List<ScheduleDay> getCurrentPeriod(Long departmentId) throws Exception {
		//java.util.Date date = new java.util.Date();
		//Date myDate = new Date(date.getTime());
		LocalDate today = LocalDate.now();
		//today = today.minusDays(60); //only for test
		try {
			//System.out.println(myDate);
			Long id = schedulePeriodRepository.getByDate(Date.valueOf(today), departmentId).getSchedulePeriodId();
			List<ScheduleDay> scheduleDays = scheduleDayRepository.getSchedulePeriod(id);
			return scheduleDays;
		} catch (NullPointerException ne) {
			//insert new period
			schedulePeriodRepository.createSchedulePeriod(Date.valueOf(today), departmentId);
			Long id = schedulePeriodRepository.getByDate(Date.valueOf(today), departmentId).getSchedulePeriodId();
			List<ScheduleDay> scheduleDays = scheduleDayRepository.getSchedulePeriod(id);
			return scheduleDays;		
		} 
	}

	@Override
	public List<Employee> getEmployeeBySchedulePeriod(Long departmentId) throws Exception {
		LocalDate today = LocalDate.now();
		//today = today.minusDays(60); //only for test
		try {
			//System.out.println(myDate);
			Long id = schedulePeriodRepository.getByDate(Date.valueOf(today), departmentId).getSchedulePeriodId();
			List<Employee> employees = scheduleDayRepository.getEmployeeBySchedulePeriod(id);
			return employees;
		} catch (NullPointerException ne) {
			
			return null;		
		} 
	}

	@SuppressWarnings("finally")
	@Override
	public List<ScheduleDay> getByPeriod(Date date, Long departmentId) throws Exception {
		try {
			Long id = schedulePeriodRepository.getByDate(date, departmentId).getSchedulePeriodId();
			List<ScheduleDay> scheduleDays = scheduleDayRepository.getSchedulePeriod(id);
			return scheduleDays;
		} catch (NullPointerException ne) {
			LocalDate today = LocalDate.now();
			if (date.compareTo(Date.valueOf(today)) > 0 ) {
				//insert new period, if date is greater than today
				try {
				schedulePeriodRepository.createSchedulePeriod(date, departmentId);
				} catch (NegativeArraySizeException nase) {
					
				} finally {
					Long id = schedulePeriodRepository.getByDate(date, departmentId).getSchedulePeriodId();
					List<ScheduleDay> scheduleDays = scheduleDayRepository.getSchedulePeriod(id);
					return scheduleDays;
				}
			// if date is lower than today sent an error
			} else {
				throw new Exception(ne.getMessage());
				//return null;
			}
		} 
	}

	@Override
	public List<Employee> getEmployeeByCurrentPeriod(Long departmentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> listHiddenDepartments(Date myDate, String punchNumber) throws Exception {
		List<Department> departments = departmentRepository.listHiddenDepartments(myDate, punchNumber);
		//List<Department> departments = departmentRepository.listHiddenDepartments2();
		return departments;
	}

	@Override
	public List<Department> listShownDepartments(Date myDate, String punchNumber) throws Exception {
		List<Department> departments = departmentRepository.listShownDepartments(myDate, punchNumber);
		//List<Department> departments = departmentRepository.listHiddenDepartments2();
		return departments;
	}
}
