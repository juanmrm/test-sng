package com.sng.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
public class JavaSender implements Sender {
	
	 @Autowired
	 private JavaMailSender sender;
	
	 @Override
	 public ResultInfo send(Request req) {

		 ResultInfo res;
		 MailRequest mailReq = (MailRequest) req;
		 
		 try {
			 SimpleMailMessage message = new SimpleMailMessage();

			 //From
			 message.setFrom("sng@gmail.com");

			 //To
			 message.setTo(mailReq.getTo());

			 //cc
			 message.setCc(mailReq.getCc());

			 //Bcc
			 message.setBcc(mailReq.getBcc());

			 //Subject
			 message.setSubject(mailReq.getSubject());

			 //Text
			 message.setText(mailReq.getText());

			sender.send(message);

			 res = new ResultInfo(MessageStatus.SUCCESS, "Email Enviado Correctamente");

		 }catch (MailException mex) {
			 mex.printStackTrace();
			 res = new ResultInfo(MessageStatus.FAILED, mex.getMessage());
		 }
		
		return res;
	}

}