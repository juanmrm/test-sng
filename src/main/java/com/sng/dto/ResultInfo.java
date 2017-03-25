package com.sng.dto;

import com.sng.sender.MessageStatus;

/**
 * Resultado del envio de un mensaje.
 * @author juanmiguel
 *
 */
public class ResultInfo {
	
	private final MessageStatus status;
	private final String errorMessage;
	
	public ResultInfo(MessageStatus status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
