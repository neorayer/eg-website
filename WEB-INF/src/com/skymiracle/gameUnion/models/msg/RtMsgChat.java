package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.User;

public class RtMsgChat extends RtMsg<RtMsgChat>{

	private String content;
	
	private User sender;
	
	@Override
	public String getCmd() {
		return "chat";
	}
	
	@Override
	public void setCmd(String cmd) {
		this.cmd = "chat";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}




	
}
