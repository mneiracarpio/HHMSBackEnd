package com.hhms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Employee;
import com.hhms.repository.EmployeeRepository;

@Service
public class LoginService {

	@Autowired 
	private EmployeeRepository employeeRepository;
	
    public List<Employee> login(String punchNumber) {
		System.out.println(punchNumber );
        List<Employee> employeeOpt= employeeRepository.findByPunchNumber(punchNumber);
        return employeeOpt;
        
    }
    
    
}
