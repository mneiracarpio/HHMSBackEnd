package com.hhms.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.PayrollPeriod;

@Repository
public interface PayrollPeriodRepository extends BaseRepository<PayrollPeriod, Long>{


	@Query(value="SELECT * FROM payroll_period p WHERE :myDate between start_date and end_date and department_id = :departmentId",
			nativeQuery = true )
	PayrollPeriod getByDate(@Param("myDate") Date myDate, @Param("departmentId") Long departmentId);
	

	@Query(value="{call PAYROLL_PERIOD_CREATION(:periodDate, :departmentId)}",
			nativeQuery = true )
	public void createPayrollPeriod(
			@Param("periodDate") Date periodDate, 
			@Param("departmentId") Long departmentId);
	
	
}
