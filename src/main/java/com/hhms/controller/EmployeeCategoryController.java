package com.hhms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.EmployeeCategory;
import com.hhms.entity.MessageResponse;
import com.hhms.service.EmployeeCategoryServiceImpl;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/employeecategory")
public class EmployeeCategoryController extends BaseControllerImpl<EmployeeCategory, EmployeeCategoryServiceImpl>{

	@Autowired
	private EmployeeCategoryServiceImpl employeeCategoryServiceImpl;
	
	/** 
	 * It lists all lower categories 
	 * @return ResponseEntity<?> containing a category object list if it's ok, otherwise it sends an error message  
	 */	
	@GetMapping("/listlowercategories")
	public ResponseEntity<?> listLowerCategories(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeCategoryServiceImpl.listLowerEmployeeCategory());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}
	
	/** 
	 * It lists all admin categories 
	 * @return ResponseEntity<?> containing a category object list if it's ok, otherwise it sends an error message  
	 */	
	@GetMapping("/listadmincategories")
	public ResponseEntity<?> listAdminCategories(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeCategoryServiceImpl.listAdminEmployeeCategory());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

}
