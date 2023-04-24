package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.Employee;
import com.hhms.entity.ScheduleDay;

@Repository
public interface ScheduleDayRepository extends BaseRepository<ScheduleDay, Long> {
	@Query(value="SELECT * FROM schedule_day d, schedule_period p "+ 
			"WHERE d.schedule_period_id=p.schedule_period_id " + 
			"AND p.schedule_period_id = :schedulePeriodId " + 
			"ORDER BY d.employee_id, d.day",
			nativeQuery = true )
	List<ScheduleDay> getSchedulePeriod(@Param("schedulePeriodId") Long schedulePeriodId);
	
	@Query(value="select distinct employee_id, first_name, last_name, punch_number, employee_category_id, state from ( " +
			"SELECT e.*, d.employee_id id2, d.day FROM schedule_day d, schedule_period p, employee e "+ 
			"WHERE d.schedule_period_id=p.schedule_period_id " + 
			"AND p.schedule_period_id = :schedulePeriodId " +
			"AND e.employee_id = d.employee_id " +
			"ORDER BY d.employee_id, d.day )",
			nativeQuery = true )
	List<Employee> getEmployeeBySchedulePeriod(@Param("schedulePeriodId") Long schedulePeriodId);
}
