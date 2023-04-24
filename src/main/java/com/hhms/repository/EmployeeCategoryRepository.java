package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhms.entity.EmployeeCategory;

@Repository
public interface EmployeeCategoryRepository extends BaseRepository<EmployeeCategory, Long> {
	@Query(value= "SELECT * from employee_category WHERE employee_category_id IN (3,4,5) ",
			nativeQuery = true )
	List<EmployeeCategory> lowerEmployeeCategory();

	@Query(value= "SELECT * from employee_category WHERE employee_category_id IN (1,2) ",
			nativeQuery = true )
	List<EmployeeCategory> adminEmployeeCategory();
}
