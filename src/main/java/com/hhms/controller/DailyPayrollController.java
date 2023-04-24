package com.hhms.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.DailyPayroll;
import com.hhms.entity.MessageResponse;
import com.hhms.service.DailyPayrollServiceImpl;

/** It has all the functionality for Daily Payroll objects, it's the detail procedures for each payroll period.
 * 
 * 
 * @author Marco
 * @version January 31, 2023
 */

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/dailypayroll")
public class DailyPayrollController extends BaseControllerImpl<DailyPayroll, DailyPayrollServiceImpl>{

	@Autowired
	DailyPayrollServiceImpl 	dailyPayrollServiceImpl;
	
	/**
	 * It brings all the payroll information for a given date
	 * It works only for housekeeping department
	 * @return ResponseEntity<?> object, it returns the payroll Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hsk/payrollview/{periodDate}")
	public ResponseEntity<?> listHSKPayrollView(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 1;
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.listPayroll(periodDate, departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/**
	 * It brings all the production information for a given date
	 * If it doesn't exists, it will create all the information.
	 * It works only for janitorial department
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/jan/payrollview/{periodDate}")
	public ResponseEntity<?> listJANPayrollView(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 3;
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.listPayroll(periodDate, departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/**
	 * It brings all the production information for a given date
	 * If it doesn't exists, it will create all the information.
	 * It works only for laundry department
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/lnd/payrollview/{periodDate}")
	public ResponseEntity<?> listLNDPayrollView(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 4;
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.listPayroll(periodDate, departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	

	/**
	 * It brings all the production information for the current payroll period
	 * If it doesn't exists, it will create all the information.
	 * It works only for housekeeping department
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hsk/payrollview/")
	public ResponseEntity<?> listHSKCurrentPayrollView() {
		try {
			Long departmentId=(long) 1;
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.listCurrentPayroll(departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/**
	 * It brings all the production information for the current payroll period
	 * If it doesn't exists, it will create all the information.
	 * It works only for janitorial department
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/jan/payrollview/")
	public ResponseEntity<?> listJANCurrentPayrollView() {
		try {
			Long departmentId=(long) 3;
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.listCurrentPayroll(departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/**
	 * It brings all the production information for the current payroll period
	 * If it doesn't exists, it will create all the information.
	 * It works only for laundry department
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/lnd/payrollview/")
	public ResponseEntity<?> listLNDCurrentPayrollView() {
		try {
			Long departmentId=(long) 4;
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.listCurrentPayroll(departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/**
	 * It recalculates payroll information when a daily payroll has changed
	 * It works only for any department
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects if it is ok. It returns a error message if it fails.
	 */
	@PutMapping("/recalculatepayroll/")
	public ResponseEntity<?> recalculatePayroll(@RequestBody DailyPayroll newDailyPayroll) {
		try {
			DailyPayroll dailyPayroll = dailyPayrollServiceImpl.findById(newDailyPayroll.getDailyPayrollId());
			
			dailyPayroll.getBiweeklyPayroll().setHours(dailyPayroll.getBiweeklyPayroll().getHours() - dailyPayroll.getHours() + newDailyPayroll.getHours());
			dailyPayroll.getBiweeklyPayroll().setLaborIndex(dailyPayroll.getBiweeklyPayroll().getHours() / dailyPayroll.getBiweeklyPayroll().getPayrollPeriod().getHourFactor());
			dailyPayroll.getBiweeklyPayroll().getPayrollPeriod().setHours(dailyPayroll.getBiweeklyPayroll().getPayrollPeriod().getHours() - dailyPayroll.getHours() + newDailyPayroll.getHours() );
			dailyPayroll.getBiweeklyPayroll().getPayrollPeriod().setLaborIndex(dailyPayroll.getBiweeklyPayroll().getPayrollPeriod().getHours() / dailyPayroll.getBiweeklyPayroll().getPayrollPeriod().getHourFactor());
			dailyPayroll.setHours(newDailyPayroll.getHours());
			return ResponseEntity.status(HttpStatus.OK).body(dailyPayrollServiceImpl.update(newDailyPayroll.getDailyPayrollId(), dailyPayroll));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Try again please."));
		}
		
	}
	
}
