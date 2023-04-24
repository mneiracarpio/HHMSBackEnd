package com.hhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hhms.entity.Employee;
import com.hhms.entity.Manager;

@Service
public class HHMSUserDetailsService implements UserDetailsService {

	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
	
	@Autowired
	private ManagerServiceImpl managerServiceImpl;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    /*Map<String, String> usuarios = Map.of(
        "jcabelloc", "USER",
        "mlopez", "JAPY"
    );*/
		int exists;
		//validate if the username owns Employee (1) or Manager (2) or doesn't exists (0)
		try {
			exists = managerServiceImpl.existsInEmpMan(username);
			// when it's an Employee
			if (exists == 1 ) {
				try {
					Employee employee = employeeServiceImpl.searchByPunchNumber(username);
					var rol = employee.getEmployeeCategory().getDescription(); 
				    if (rol != null) {
				    	User.UserBuilder userBuilder = User.withUsername(username);
				    	if (userBuilder != null) { 
				    		String encryptedPassword = bcrypt.hash(username);
				    		userBuilder.password(encryptedPassword).roles(rol);
				    		return userBuilder.build();
				    	}
				    } else {
				    	throw new UsernameNotFoundException(username);
				    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			// when it's a manager
			} else if (exists == 2) {
				//System.out.println("2");
				try {
					Manager manager = managerServiceImpl.getByUserName(username);
					var rol = manager.getEmployeeCategory().getDescription(); 
					//var rol = usuarios.get(username);
				    if (rol != null) {
				    	User.UserBuilder userBuilder = User.withUsername(username);
				    	if (userBuilder != null) { 
				    		// "secreto" => [BCrypt] => $2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq
				    		//String encryptedPassword = "$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq"; // secreto
				    		//String encryptedPassword = "$2a$10$RQkuKM3a2gzWTN.EW71FSevlCxMGBwrEirfvaIqR1UNyu.WQH.3Ly"; // miclave
			
				    		String encryptedPassword = manager.getPassword();
				    		userBuilder.password(encryptedPassword).roles(rol);
				    		return userBuilder.build();
				    	}
				    } else {
				    	throw new UsernameNotFoundException(username);
				    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return null;
			}
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
		
}
