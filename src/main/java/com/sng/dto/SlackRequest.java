package com.sng.dto;

/**
 * Representa el contenido de un mensaje slack.
 * @author juanmiguel
 *
 */
public class SlackRequest extends Request{
	
	private String username;
	private String text;
	private String icon_emoji;
	private String icon_url;
	private String channel;
	
	public String getUsername() {
		return username;
	}

	public String getText() {
		return text;
	}

	public String getIcon_emoji() {
		return icon_emoji;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public String getChannel() {
		return channel;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setIcon_emoji(String icon_emoji) {
		this.icon_emoji = icon_emoji;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
