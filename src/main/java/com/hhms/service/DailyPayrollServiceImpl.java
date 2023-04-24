package com.hhms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.DailyPayroll;
import com.hhms.entity.PayrollByEmployee;
import com.hhms.entity.PayrollPeriod;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.PayrollByEmployeeRepository;
import com.hhms.repository.PayrollPeriodRepository;

@Service
public class DailyPayrollServiceImpl extends BaseServiceImpl<DailyPayroll, Long> implements DailyPayrollService{

	@Autowired
	PayrollByEmployeeRepository payrollByEmployeeRepository;
	
	@Autowired
	PayrollPeriodRepository payrollPeriodRepository;
	
	public DailyPayrollServiceImpl(BaseRepository<DailyPayroll, Long> baseRepository) {
		super(baseRepository);
	}

	@Override
	public List<PayrollByEmployee> listPayroll(Date periodDate, Long departmentId) throws Exception {
		try {
			PayrollPeriod payrollPeriod = payrollPeriodRepository.getByDate(periodDate, departmentId);
			List<PayrollByEmployee> payrollViews = payrollByEmployeeRepository.getPayrollView(payrollPeriod.getPayrollPeriodId());
			return payrollViews;
		} catch (NullPointerException ne) {
			/*payrollPeriodRepository.createPayrollPeriod(periodDate, departmentId);
			PayrollPeriod payrollPeriod = payrollPeriodRepository.getByDate(periodDate, departmentId);
			List<PayrollByEmployee> payrollViews = payrollByEmployeeRepository.getPayrollView(payrollPeriod.getPayrollPeriodId());
			return payrollViews;*/
			throw ne;
		}
	}	
	

	@Override
	public List<PayrollByEmployee> listCurrentPayroll(Long departmentId) throws Exception {
		LocalDate today = LocalDate.now();
		try {
			PayrollPeriod payrollPeriod = payrollPeriodRepository.getByDate(Date.valueOf(today), departmentId);
			//System.out.println(payrollPeriod);
			//System.out.println(payrollPeriod.getPayrollPeriodId());
			List<PayrollByEmployee> payrollViews = payrollByEmployeeRepository.getPayrollView(payrollPeriod.getPayrollPeriodId());
			return payrollViews;
		} catch (NullPointerException ne) {
			//System.out.println(ne.getMessage());
			payrollPeriodRepository.createPayrollPeriod(Date.valueOf(today), departmentId);
			PayrollPeriod payrollPeriod = payrollPeriodRepository.getByDate(Date.valueOf(today), departmentId);
			List<PayrollByEmployee> payrollViews = payrollByEmployeeRepository.getPayrollView(payrollPeriod.getPayrollPeriodId());
			return payrollViews;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

}
