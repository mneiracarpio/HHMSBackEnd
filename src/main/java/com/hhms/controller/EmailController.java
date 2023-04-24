package com.hhms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.EmailMessage;
import com.hhms.service.EmailSenderService;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/email")
public class EmailController {

	
	@Autowired
    private EmailSenderService emailSenderService;


	/** 
	 * It sends and email 
	 * @param email it is the email object to send
	 * @return String message saying if it was ok or not
	 */	
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailMessage email) {
        try {
        	emailSenderService.sendEmail(email);
            return "Email successfully sent.";
        } catch (Exception ex) {
            return "Error sending the email: " + ex.getMessage();
        }
    }
    
    
    
}
