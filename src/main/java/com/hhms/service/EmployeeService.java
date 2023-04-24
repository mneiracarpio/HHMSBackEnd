package com.hhms.service;

import java.util.List;

import com.hhms.entity.Employee;

public interface EmployeeService extends BaseService<Employee, Long>{

	List<Employee> search(String punchNumber) throws Exception;
	
	Employee searchByPunchNumber(String punchNumber) throws Exception;

	List<Employee> listValidEmployees() throws Exception;
}
