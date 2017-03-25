package com.sng.sender;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sng.dto.Request;
import com.sng.dto.ResultInfo;
import com.sng.dto.SlackRequest;

import allbegray.slack.SlackClientFactory;
import allbegray.slack.exception.SlackResponseErrorException;
import allbegray.slack.webapi.SlackWebApiClient;
import allbegray.slack.webapi.method.chats.ChatPostMessageMethod;

@Component(value="slacksender")
public class SlackSender implements Sender {

	@Value("${slack.token}")
	private String token;
	
	private SlackWebApiClient webApiClient;
	
	@Override
	public ResultInfo send(Request req) {

		ResultInfo res;
		SlackRequest slkReq = (SlackRequest) req;
		
		try {
			ChatPostMessageMethod postMessage = new ChatPostMessageMethod(slkReq.getChannel(),slkReq.getText());
			postMessage.setUsername(slkReq.getUsername());
			postMessage.setIcon_emoji(slkReq.getIcon_emoji());
			postMessage.setIcon_url(slkReq.getIcon_url());
			String ts = webApiClient.postMessage(postMessage);
			res = new ResultInfo(MessageStatus.SUCCESS, "Mensaje Enviado Correctamente con timestamp: " + ts);
		}
		catch (SlackResponseErrorException ex) {
			res = new ResultInfo(MessageStatus.FAILED, ex.getMessage());
		}
		
		return res;
	}
	
	
	@PostConstruct
	public void buildSlackClient(){
		this.webApiClient = SlackClientFactory.createWebApiClient(token);
	}

}
