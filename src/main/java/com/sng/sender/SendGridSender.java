package com.sng.sender;

import org.springframework.stereotype.Component;

import com.sng.dto.Request;
import com.sng.dto.ResultInfo;

@Component(value="sendgridsender")
public class SendGridSender implements Sender {

	@Override
	public ResultInfo send(Request req) {
		ResultInfo res = new ResultInfo(MessageStatus.FAILED, "SENDGRID NOT YET IMPLEMENTED");		
		return res;
	}

}
