package com.hhms.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.AuthenticationReq;
import com.hhms.entity.EmailMessage;
import com.hhms.entity.Manager;
import com.hhms.entity.MessageResponse;
import com.hhms.entity.TokenInfo;
import com.hhms.service.EmailSenderService;
import com.hhms.service.JwtUtilService;
import com.hhms.service.ManagerServiceImpl;
import com.hhms.service.ResetAuthenticationLogServiceImpl;

/**
 * This class has the API to allow the manager authentication 
 * @author Marco
 * @version January 1, 2023
 *
 */
@RestController
@CrossOrigin(origins="")
@RequestMapping("")
public class ManagerLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService usuarioDetailsService;

	@Autowired
	private JwtUtilService jwtUtilService;

	@Autowired 
	private ManagerServiceImpl managerServiceImpl;

	@Autowired
    private EmailSenderService emailSenderService;

	@Autowired
    private ResetAuthenticationLogServiceImpl resetAuthenticationLogServiceImpl;

	/**
	 * This method login the manager, it uses an object with username and password.
	 * It validates the information using Spring Security.
	 * @param authenticationReq object that contains the username and password.
	 * @return ResponseEntity<?> object that contains the Token if it is ok, and an error message if it fails.
	 */
	@PostMapping("/public/managerlogin")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationReq authenticationReq) {
		try {
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
					authenticationReq.getUsername(), authenticationReq.getPassword()));
			
			final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(authenticationReq.getUsername());
	
			//Validate the user owns to manager
			if (managerServiceImpl.existsInEmpMan(userDetails.getUsername()) != 2 ) {
				//return null;
				return ResponseEntity
						.badRequest()
						.body( new MessageResponse("Error: Username or password wrong"));
			}
			
			final String jwt = jwtUtilService.generateToken(userDetails, 0);
	
			TokenInfo tokenInfo = new TokenInfo(jwt);
			
			Manager manager = managerServiceImpl.getByUserName(authenticationReq.getUsername());
			tokenInfo.setManagerId(manager.getManagerId());
			tokenInfo.setFirstName(manager.getFirstName());
			tokenInfo.setLastName(manager.getLastName());
			tokenInfo.setUsername(manager.getUsername());
			tokenInfo.setEmployeeCategoryId(manager.getEmployeeCategory().getEmployeeCategoryId());
			return ResponseEntity.ok(tokenInfo);
			
		} catch (Exception e) {
			//e.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body( new MessageResponse("Error: Username or password wrong"));
		}
	}
	

	/**
	 * This method is a part of a recovering option, when manager can't login because he forgot his/her username or password.
	 * It validates the email, if it exists
	 * @param email string that contains the user email
	 * @return ResponseEntity<?> object that contains the Token if it is ok, and an error message if it fails.
	 */
	@PostMapping("/public/recoverlogin")
	public ResponseEntity<?> recoverLogin(@RequestBody String email) {
		final long JWT_TOKEN_VALIDITY = 1000 * 60 * (long) 5; // 5 minutos
		final long OPERATION = 1; //Reset email request
		
		// validate email
		Manager manager;
		try {
			System.out.println(email);
			manager = managerServiceImpl.getByEmail(email);
			System.out.println(manager);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Invalid email."));
		}
		
		try {
			final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(manager.getUsername());
			
			//Validate the user owns to manager
			if (managerServiceImpl.existsInEmpMan(userDetails.getUsername()) != 2 ) {
				return ResponseEntity
						.badRequest()
						.body( new MessageResponse("Error: Username or password wrong"));
			}
			
			final String jwt = jwtUtilService.generateToken(userDetails, JWT_TOKEN_VALIDITY);
	
			TokenInfo tokenInfo = new TokenInfo(jwt);
			
			tokenInfo.setManagerId(manager.getManagerId());
			tokenInfo.setFirstName(manager.getFirstName());
			tokenInfo.setLastName(manager.getLastName());
			tokenInfo.setUsername(manager.getUsername());
			tokenInfo.setEmployeeCategoryId(manager.getEmployeeCategory().getEmployeeCategoryId());
			//building the email
			String token = tokenInfo.getJwtToken();
			//String encodedToken = URLEncoder.encode(token, "UTF-8");
			String encodedToken = Base64.getEncoder().encodeToString(token.getBytes());
			
			//System.out.println(encodedToken);
			String resetLink = "http://13.58.21.38/resetpassword/" + encodedToken;
			//String resetLink = "http://localhost:3000/resetpassword/" + tokenInfo.getJwtToken();
			String emailBody = "Hi " + manager.getFirstName() + "<br>"
					+ "You recently requested to reset the password for your account in HHMS System. Click the link below to proceed. " 
					+ "This link will be valid only for 5 minutes." + "<br>"
					+ "<a href=\"" + resetLink + "\">Reset Password</a>" + "<br>"
					+ "<br>"
					+ "<br>"
					+ "Carriage Hotel";
			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setSubject("Recover your password");
			emailMessage.setText(emailBody);
			emailMessage.setTo(manager.getEmail());			
			emailMessage.setFrom("hhms.carriage.hotel@gmail.com");
			emailSenderService.sendEmail(emailMessage);
			
			resetAuthenticationLogServiceImpl.registerLog(manager, OPERATION);
			//respond a positive message
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("An email has been sent with instructions to reset your password."));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Invalid email."));
		}
	}
	
}
