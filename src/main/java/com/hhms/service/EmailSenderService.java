package com.hhms.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hhms.entity.EmailMessage;

@Service
public class EmailSenderService {

	@Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailMessage email) throws MailException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        message.setHeader("Importance", "High");
        // Set the HTML body of the email
        helper.setText(email.getText(), true);
        mailSender.send(message);
    }
	
}
