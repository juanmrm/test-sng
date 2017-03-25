package com.sng.dto;

/**
 * Representa el contenido de un mensaje java mail.
 * @author juanmiguel
 *
 */
public class MailRequest extends Request {
	
	private String[] to;
	private String[] cc;
	private String[] bcc;
	private String text;
	private String subject;
	
	public String[] getTo() {
		return to;
	}

	public String[] getCc() {
		return cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public String getText() {
		return text;
	}

	public String getSubject() {
		return subject;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
