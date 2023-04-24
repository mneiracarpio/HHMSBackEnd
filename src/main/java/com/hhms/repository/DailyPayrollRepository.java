package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.DailyPayroll;
import com.hhms.entity.PayrollByEmployee;

@Repository
public interface DailyPayrollRepository extends BaseRepository<DailyPayroll, Long>  {

	@Query(value="SELECT  biweekly_payroll_id  " +
			"FROM payroll_x_employee "+ 
			"WHERE :payrollPeriodId = :payrollPeriodId ",
			nativeQuery = true )
	List<PayrollByEmployee> getPayrollView(@Param("payrollPeriodId") Long payrollPeriodId);

}
