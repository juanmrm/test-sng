package com.sng.sender;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sng.dto.MailRequest;
import com.sng.dto.Request;
import com.sng.dto.ResultInfo;

/**
 * Implementacion del sender por java mail.
 * @author juanmiguel
 *
 */
@Component(value="mailsender")
public class MailSender implements Sender {
	
	 @Autowired
	 private JavaMailSender sender;
	
	 @Override
	 public ResultInfo send(Request req) {

		 ResultInfo res;
		 MailRequest mailReq = (MailRequest) req;
		 
		 try {
			 MimeMessage mail = sender.createMimeMessage();
			 MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			 
			 //From
			 helper.setFrom(new InternetAddress("sng@gmail.com"));

			 //To
			 helper.setTo(mailReq.getTo());

			 //cc
			 helper.setCc(mailReq.getCc());

			 //Bcc
			 helper.setBcc(mailReq.getBcc());

			 //Subject
			 helper.setSubject(mailReq.getSubject());

			 //Text
			 helper.setText(mailReq.getText());

			 sender.send(mail);

			 res = new ResultInfo(MessageStatus.SUCCESS, "Email Enviado Correctamente");

		 }catch (MailException | MessagingException mex) {
			 mex.printStackTrace();
			 res = new ResultInfo(MessageStatus.FAILED, mex.getMessage());
		 }
		
		return res;
	}

}