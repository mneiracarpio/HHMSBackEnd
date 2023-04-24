package com.hhms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.Department;
import com.hhms.entity.Employee;
import com.hhms.entity.MessageResponse;
import com.hhms.entity.ScheduleDay;
import com.hhms.service.ScheduleDayServiceImpl;

/** create a new schedule_period (current and futures weeks, deny if its past weeks)
 * list current period, with employees, by department
 * list a period, with employees, by department, by date
 * add a new employee in a period,
 * delete an employee in a period,
 * 
 * 
 * @author Marco
 * @version January 31, 2023
 */

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/schedule")
public class ScheduleController extends BaseControllerImpl<ScheduleDay, ScheduleDayServiceImpl> {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ScheduleDayServiceImpl scheduleDayServiceImpl;

	/** OK
	 * It brings all the production information for the current period. by housekeeping department
	 * If it doesn't exists, it will create all the information.
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hskp/currentperiod")
	public ResponseEntity<?> hskpCurrentPeriod() {
		try {
			Long departmentId=(long) 1;
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.getCurrentPeriod(departmentId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/** OK
	 * It brings all the production information for the current period. by Janitorial department
	 * If it doesn't exists, it will create all the information.
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/jnt/currentperiod")
	public ResponseEntity<?> jntCurrentPeriod() {
		try {
			Long departmentId=(long) 3;
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.getCurrentPeriod(departmentId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/** OK
	 * It brings all the production information for the current period. by Laundry department
	 * If it doesn't exists, it will create all the information.
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/lnd/currentperiod")
	public ResponseEntity<?> lndCurrentPeriod() {
		try {
			Long departmentId=(long) 4;
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.getCurrentPeriod(departmentId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/** OK
	 * It brings all the production information for a period by department and by a given date.
	 * If it doesn't exists, it will create all the information only if date is greater than this week.
	 * It works only for housekeeping employees
	 * @return ResponseEntity<?> object, it returns the schedule information for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hskp/getByDate/{periodDate}")
	public ResponseEntity<?> hskpGetByDate(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 1;
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.getByPeriod(periodDate, departmentId));
		} catch (Exception e) {
			//java.lang.NegativeArraySizeException: -1
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/** OK
	 * It brings all the production information for a period by department and by a given date.
	 * If it doesn't exists, it will create all the information only if date is greater than this week.
	 * It works only for janitorial employees
	 * @return ResponseEntity<?> object, it returns the schedule information for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/jnt/getByDate/{periodDate}")
	public ResponseEntity<?> jntGetByDate(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 3;
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.getByPeriod(periodDate, departmentId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/** OK
	 * It brings all the production information for a period by department and by a given date.
	 * If it doesn't exists, it will create all the information only if date is greater than this week.
	 * It works only for laundry employees
	 * @return ResponseEntity<?> object, it returns the schedule information for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/lnd/getByDate/{periodDate}")
	public ResponseEntity<?> lndGetByDate(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 4;
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.getByPeriod(periodDate, departmentId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/** OK
	 * It brings the employee list in a production period for Housekeeping department
	 * If it doesn't exists, it will create all the information.
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hskp/currentperiodemployee")
	public ResponseEntity<?> hskpCurrentPeriodEmployee() {
		try {
			Long departmentId=(long) 1;
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Long currentEmployeeId=(long) 0;
			List<ScheduleDay> scheduleDays = scheduleDayServiceImpl.getCurrentPeriod(departmentId);
			for (ScheduleDay scheduleDay : scheduleDays) {
				if (!scheduleDay.getEmployee().getEmployeeId().equals(currentEmployeeId)) {
					employees.add(scheduleDay.getEmployee());
					currentEmployeeId=scheduleDay.getEmployee().getEmployeeId();
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	/** OK
	 * It brings the employee list in a production period for Janitorial department
	 * If it doesn't exists, it will create all the information.
	 * It works only for catering items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/jnt/currentperiodemployee")
	public ResponseEntity<?> jntCurrentPeriodEmployee() {
		try {
			Long departmentId=(long) 3;
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Long currentEmployeeId=(long) 0;
			List<ScheduleDay> scheduleDays = scheduleDayServiceImpl.getCurrentPeriod(departmentId);
			for (ScheduleDay scheduleDay : scheduleDays) {
				if (!scheduleDay.getEmployee().getEmployeeId().equals(currentEmployeeId)) {
					employees.add(scheduleDay.getEmployee());
					currentEmployeeId=scheduleDay.getEmployee().getEmployeeId();
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}

	/** OK
	 * It brings the employee list in a production period for Laundry department
	 * If it doesn't exists, it will create all the information.
	 * It works only for catering items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/lnd/currentperiodemployee")
	public ResponseEntity<?> lndCurrentPeriodEmployee() {
		try {
			Long departmentId=(long) 4;
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Long currentEmployeeId=(long) 0;
			List<ScheduleDay> scheduleDays = scheduleDayServiceImpl.getCurrentPeriod(departmentId);
			for (ScheduleDay scheduleDay : scheduleDays) {
				if (!scheduleDay.getEmployee().getEmployeeId().equals(currentEmployeeId)) {
					employees.add(scheduleDay.getEmployee());
					currentEmployeeId=scheduleDay.getEmployee().getEmployeeId();
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}

	/** OK
	 * It brings all the employees in a schedule period in Housekeeping department
	 * If it doesn't exists, it will create all the information.
	 * It works only for catering items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hskp/employeebyperiod/{periodDate}")
	public ResponseEntity<?> hskpEmployeeByPeriod(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 1;
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Long currentEmployeeId=(long) 0;
			List<ScheduleDay> scheduleDays = scheduleDayServiceImpl.getByPeriod(periodDate, departmentId);
			for (ScheduleDay scheduleDay : scheduleDays) {
				if (!scheduleDay.getEmployee().getEmployeeId().equals(currentEmployeeId)) {
					employees.add(scheduleDay.getEmployee());
					currentEmployeeId=scheduleDay.getEmployee().getEmployeeId();
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/** OK
	 * It brings all the employees in a schedule period in Janitorial department
	 * If it doesn't exists, it will create all the information.
	 * It works only for catering items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/jnt/employeebyperiod/{periodDate}")
	public ResponseEntity<?> jntEmployeeByPeriod(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 3;
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Long currentEmployeeId=(long) 0;
			List<ScheduleDay> scheduleDays = scheduleDayServiceImpl.getByPeriod(periodDate, departmentId);
			for (ScheduleDay scheduleDay : scheduleDays) {
				if (!scheduleDay.getEmployee().getEmployeeId().equals(currentEmployeeId)) {
					employees.add(scheduleDay.getEmployee());
					currentEmployeeId=scheduleDay.getEmployee().getEmployeeId();
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	/** OK
	 * It brings all the employees in a schedule period in Laundry department
	 * If it doesn't exists, it will create all the information.
	 * It works only for catering items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/lnd/employeebyperiod/{periodDate}")
	public ResponseEntity<?> lndEmployeeByPeriod(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 4;
			ArrayList<Employee> employees = new ArrayList<Employee>();
			Long currentEmployeeId=(long) 0;
			List<ScheduleDay> scheduleDays = scheduleDayServiceImpl.getByPeriod(periodDate, departmentId);
			for (ScheduleDay scheduleDay : scheduleDays) {
				if (!scheduleDay.getEmployee().getEmployeeId().equals(currentEmployeeId)) {
					employees.add(scheduleDay.getEmployee());
					currentEmployeeId=scheduleDay.getEmployee().getEmployeeId();
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
		
	/** OK
	 * It brings all departments where the punchNumber is not found
	 * @return ResponseEntity<?> object, it returns a list of departments where the punchNumber is not found
	 */
	@GetMapping("/emp/hiddendepartment")
	public ResponseEntity<?> hiddenDepartment(
			@RequestParam("periodDate") Date periodDate, 
			@RequestParam("punchNumber") String punchNumber) {
		
		try {
			List<Department> departments = scheduleDayServiceImpl.listHiddenDepartments(periodDate, punchNumber);
			return ResponseEntity.status(HttpStatus.OK).body(departments);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/** OK
	 * It brings all departments where the punchNumber is found
	 * @return ResponseEntity<?> object, it returns a list of departments where the punchNumber is found
	 */
	@GetMapping("/emp/showndepartment")
	public ResponseEntity<?> shownDepartment(
			@RequestParam("periodDate") Date periodDate, 
			@RequestParam("punchNumber") String punchNumber) {
		
		try {
			List<Department> departments = scheduleDayServiceImpl.listShownDepartments(periodDate, punchNumber);
			return ResponseEntity.status(HttpStatus.OK).body(departments);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/** OK
	 * It get the ScheduleDay with only 2 fields, the id and notes.
	 * Id is used to find the object and it updates notes field.
	 * @return ResponseEntity<?> object, it returns the DailyItem Object modified with the new data as confirmation
	 */
	@PutMapping("/updatenotes")
	public ResponseEntity<?> updateNotes(@RequestBody ScheduleDay scheduleDay) {
		//System.out.println(scheduleDays.get(0).toString());
		System.out.println(scheduleDay.toString());
		System.out.println(scheduleDay.getEmployee().getEmployeeId() );
		try {
			ScheduleDay newScheduleDay = scheduleDayServiceImpl.findById(scheduleDay.getScheduleDayId());
			newScheduleDay.setNote(scheduleDay.getNote());
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDayServiceImpl.update(scheduleDay.getScheduleDayId(), newScheduleDay));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Try again please."));
		}
	}
	
}
