package com.sng.dto;

import com.sng.sender.MessageStatus;

/**
 * Representa un mensaje completo con id, status y mensaje de error en caso de haberlo.
 * @author juanmiguel
 *
 */
public class Message {
	
	private final String id;
	private final MessageStatus status;
	private final String errorMessage;
	private final Request request;
	
	public Message(String id, MessageStatus status, String errorMessage, Request request) {
		super();
		this.id = id;
		this.status = status;
		this.errorMessage = errorMessage;
		this.request = request;
	}

	public String getId() {
		return id;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Request getRequest() {
		return request;
	}
	
}
