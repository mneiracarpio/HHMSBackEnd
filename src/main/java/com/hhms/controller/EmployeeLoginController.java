package com.hhms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.AuthenticationReq;
import com.hhms.entity.Employee;
import com.hhms.entity.MessageResponse;
import com.hhms.entity.TokenInfo;
import com.hhms.service.EmployeeServiceImpl;
import com.hhms.service.JwtUtilService;
import com.hhms.service.ManagerServiceImpl;

/**
 * This class has the API to allow the employee authentication 
 * @author Marco
 * @version January 1, 2023
 *
 */
@RestController
@RequestMapping("")
public class EmployeeLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService usuarioDetailsService;

	@Autowired
	private JwtUtilService jwtUtilService;
	
	@Autowired
	private ManagerServiceImpl managerServiceImpl;
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	/**
	 * This method login the employee, it uses an object with punch number
	 * It validates the information using Spring Security.
	 * @param authenticationReq object that contains the punch number.
	 * @return ResponseEntity<?> object that contains the Token if it is ok, and an error message if it fails.
	 */
	@PostMapping("/public/employeelogin")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationReq authenticationReq) {
		try {
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
					authenticationReq.getUsername(), authenticationReq.getPassword()));
	
			final UserDetails userDetails =  
					usuarioDetailsService.loadUserByUsername(authenticationReq.getUsername());
	
			//Validate the user owns to employee
			if (managerServiceImpl.existsInEmpMan(userDetails.getUsername()) != 1 ) {
				//return null;
				return ResponseEntity
						.badRequest()
						.body( new MessageResponse("Error: Wrong punch number"));
			}
			
			final String jwt = jwtUtilService.generateToken(userDetails, 0);
	
			TokenInfo tokenInfo = new TokenInfo(jwt);
			
			Employee employee = employeeServiceImpl.searchByPunchNumber(authenticationReq.getUsername());
			tokenInfo.setEmployeeId(employee.getEmployeeId());
			tokenInfo.setFirstName(employee.getFirstName());
			tokenInfo.setLastName(employee.getLastName());
			tokenInfo.setPunchNumber(employee.getPunchNumber());
			tokenInfo.setEmployeeCategoryId(employee.getEmployeeCategory().getEmployeeCategoryId());			
			//System.out.println("Authentication OK " + tokenInfo);
			return ResponseEntity.ok(tokenInfo);
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return ResponseEntity
					.badRequest()
					.body( new MessageResponse("Error: Wrong punch number"));
		}
	}
	
}
