package com.hhms.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.SchedulePeriod;

@Repository
public interface SchedulePeriodRepository extends BaseRepository<SchedulePeriod, Long> {

	@Query(value="SELECT * FROM schedule_period p WHERE :myDate between start_date and end_date and department_id = :departmentId",
			nativeQuery = true )
	SchedulePeriod getByDate(@Param("myDate") Date myDate, @Param("departmentId") Long departmentId);
	
	@Query(value="{call SCHEDULE_PERIOD_CREATION(:periodDate, :departmentId)}",
			nativeQuery = true )
	public void createSchedulePeriod(
			@Param("periodDate") Date periodDate, 
			@Param("departmentId") Long departmentId);
}
