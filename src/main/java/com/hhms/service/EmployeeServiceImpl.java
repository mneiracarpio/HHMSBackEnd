package com.hhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Employee;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(BaseRepository<Employee, Long> baseRepository) {
		super(baseRepository);
	}
	
	@Override
	public List<Employee> search(String punchNumber) throws Exception {
		try {
			List<Employee> employees = employeeRepository.findByPunchNumber(punchNumber);
			//List<Employee> employees = employeeRepository.search(punchNumber);
			//List<Employee> employees = employeeRepository.searchNativo(punchNumber);
			return employees;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Employee searchByPunchNumber(String punchNumber) throws Exception {
		Employee employee = employeeRepository.searchNByPunchNumber(punchNumber);
		return employee;
	}

	@Override
	public List<Employee> listValidEmployees() throws Exception {
		List<Employee> employees = employeeRepository.listVisibleEmployees();
		return employees;
	}
	
}
