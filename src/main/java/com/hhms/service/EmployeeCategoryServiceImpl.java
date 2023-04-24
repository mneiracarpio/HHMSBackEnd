package com.hhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hhms.entity.EmployeeCategory;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.EmployeeCategoryRepository;


@Service
public class EmployeeCategoryServiceImpl extends BaseServiceImpl<EmployeeCategory, Long> implements EmployeeCategoryService {

	@Autowired
	private EmployeeCategoryRepository employeeCategoryRepository;
	
	public EmployeeCategoryServiceImpl(BaseRepository<EmployeeCategory, Long> baseRepository) {
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EmployeeCategory> listLowerEmployeeCategory() throws Exception {
		List<EmployeeCategory> employeeCategories = employeeCategoryRepository.lowerEmployeeCategory();
		return employeeCategories;
	}

	@Override
	public List<EmployeeCategory> listAdminEmployeeCategory() throws Exception {
		List<EmployeeCategory> employeeCategories = employeeCategoryRepository.adminEmployeeCategory();
		return employeeCategories;
	}

}
