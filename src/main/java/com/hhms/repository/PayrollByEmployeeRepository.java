package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.hhms.entity.PayrollByEmployee;

@Repository
public interface PayrollByEmployeeRepository extends BaseRepository<PayrollByEmployee, Long> {

	@Query(value="SELECT  * " +
			"FROM payroll_x_employee "+ 
			"WHERE payroll_period_id = :payrollPeriodId ",
			nativeQuery = true )
	List<PayrollByEmployee> getPayrollView(@Param("payrollPeriodId") Long payrollPeriodId);
}

