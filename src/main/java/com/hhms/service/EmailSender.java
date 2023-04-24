package com.hhms.service;

import com.hhms.entity.EmailMessage;
import org.springframework.mail.*;

public interface EmailSender {

	public void sendEmail(EmailMessage email) throws MailException;
	
}
