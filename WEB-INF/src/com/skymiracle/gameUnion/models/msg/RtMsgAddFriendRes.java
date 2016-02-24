package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.User;

public class RtMsgAddFriendRes extends RtMsg<RtMsgAddFriendRes> {

	private User sender;

	public RtMsgAddFriendRes() {
	}

	public RtMsgAddFriendRes(User sender, String friendname) {
		this();
		this.sender = sender;
		this.dest = friendname;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}


	@Override
	public String getCmd() {
		this.cmd = "AddFriendRes";
		return cmd;
	}

	@Override
	public void setCmd(String cmd) {

	}

}
