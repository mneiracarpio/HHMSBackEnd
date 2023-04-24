package com.hhms.service;

import java.util.List;


import com.hhms.entity.EmployeeCategory;

public interface EmployeeCategoryService  extends BaseService<EmployeeCategory, Long>{

	List<EmployeeCategory> listLowerEmployeeCategory() throws Exception;
	
	List<EmployeeCategory> listAdminEmployeeCategory() throws Exception;
}
