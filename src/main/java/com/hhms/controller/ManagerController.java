package com.hhms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.Manager;
import com.hhms.entity.MessageResponse;
import com.hhms.service.ManagerServiceImpl;
import com.hhms.service.ResetAuthenticationLogServiceImpl;
import com.hhms.service.UpdatableBCrypt;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/manager")
public class ManagerController extends BaseControllerImpl<Manager, ManagerServiceImpl> {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
	
	@Autowired 
	private ManagerServiceImpl managerServiceImpl;

	@Autowired
    private ResetAuthenticationLogServiceImpl resetAuthenticationLogServiceImpl;

	/*@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> hardLogin(@RequestBody Login request){
		try {
			String username = request.getUserName();
			String password = request.getPassword();
			return ResponseEntity.status(HttpStatus.OK).body(managerServiceImpl.validateManager(username, password));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}*/

	/**
	 * It register a manager user
	 * @param manager object to be created
	 * @return ResponseEntity<?> sending an message when it's ok or it founds an error
	 */
	@PostMapping("/registration")
	public ResponseEntity<?> register(@RequestBody Manager manager) {
		try {
			//return ResponseEntity.status(HttpStatus.OK).body(managerServiceImpl.register(manager));
			managerServiceImpl.register(manager);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The user was successfully created."));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when register a new user"));
		}
	}
	
	/**
	 * It updates manager information
	 * @param id identifying a manager to be updated
	 * @param manager object to be updated
	 * @return ResponseEntity<?> sending the manager object when it's ok or it founds an error
	 */
	@PutMapping("/updatemanager/{id}")
	public ResponseEntity<?> updateManager(@PathVariable Long id, @RequestBody Manager newManager) {
		try {
			Manager manager = managerServiceImpl.findById(id);
			manager.setFirstName(newManager.getFirstName());
			manager.setLastName(newManager.getLastName());
			manager.setUsername(newManager.getUsername());
			manager.setEmail(newManager.getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(managerServiceImpl.update(id, manager));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when update manager information"));
		}
	}

	/**
	 * It updates manager state
	 * @param id identifying a manager to be updated
	 * @param manager object to be updated
	 * @return ResponseEntity<?> sending the manager object when it's ok or it founds an error
	 */
	@PutMapping("/updatestate/{id}")
	public ResponseEntity<?> updateState(@PathVariable Long id, @RequestBody Manager newManager) {
		try {
			Manager manager = managerServiceImpl.findById(id);
			manager.setState(newManager.getState());
			return ResponseEntity.status(HttpStatus.OK).body(managerServiceImpl.update(id, manager));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when update manager state"));
		}
	}
	
	/**
	 * It resets manager password
	 * @param manager object to be reseted
	 * @return ResponseEntity<?> sending a message when it's ok or if it founds an error
	 */
	@PutMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody Manager newManager) {
		final long OPERATION = 2; //Change user password by reset email
		try {
			Manager manager = managerServiceImpl.findById(newManager.getManagerId());
			manager.setPassword(bcrypt.hash(newManager.getPassword()));
			managerServiceImpl.update(manager.getManagerId(), manager);
			resetAuthenticationLogServiceImpl.registerLog(manager, OPERATION);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The password was changed successfully"));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Oops! Something went wrong"));
		}
	}

	/**
	 * It update manager password using encriptation
	 * @param newManager object to be updated
	 * @return ResponseEntity<?> sending a message when it's ok or if it founds an error
	 */
	@PutMapping("/updatepassword")
	public ResponseEntity<?> updatePassword(@RequestBody Manager newManager) {
		final long OPERATION = 3; //Change user password by regular procedure
		try {
			Manager manager = managerServiceImpl.findById(newManager.getManagerId());
			manager.setPassword(bcrypt.hash(newManager.getPassword()));
			managerServiceImpl.update(manager.getManagerId(), manager);
			resetAuthenticationLogServiceImpl.registerLog(manager, OPERATION);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The password was changed successfully"));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Oops! Something went wrong"));
		}
	}

	/**
	 * It get the manager picture, based on the manager id
	 * @param id represents the managerId
	 * @return ResponseEntity<?> sending the image bytes when it's ok or a message if it founds an error
	 */
	@GetMapping("/getpicture/{id}")
	public ResponseEntity<?> getPicture(@PathVariable Long id) {
		try {
			Manager manager = managerServiceImpl.findById(id);
			byte[] bytes = manager.getPicture();
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        
	        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when get the manager's picture"));
		}
	}
	
	/**
	 * It saves the manager object
	 * @param manager represents the manager to be saved
	 * @return ResponseEntity<?> send a message saying if it was ok or not
	 */
	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Manager manager) {
		try {
			managerServiceImpl.save(manager);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The user was successfully created."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Error: There was problem when trying to create the user."));
		}
	}
	
	
}
