package com.hhms.service;


import java.sql.Date;
import java.util.List;

import com.hhms.entity.DailyPayroll;
import com.hhms.entity.PayrollByEmployee;

public interface DailyPayrollService extends BaseService<DailyPayroll, Long> {

	List<PayrollByEmployee> listPayroll(Date periodDate, Long departmentId) throws Exception; 
	
	List<PayrollByEmployee> listCurrentPayroll(Long departmentId) throws Exception;
	
}
