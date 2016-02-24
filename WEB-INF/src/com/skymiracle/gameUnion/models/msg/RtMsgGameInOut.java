package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.User;

public  class RtMsgGameInOut extends RtMsg<RtMsgGameInOut> {

	private boolean isIn = true;
	
	private User who;
	
	@Override
	public String getCmd() {
		return "gameInOut";
	}

	@Override
	public void setCmd(String cmd) {
	}

	public boolean getIsIn() {
		return isIn;
	}

	public void setIsIn(boolean isIn) {
		this.isIn = isIn;
	}


	public User getWho() {
		return who;
	}

	public void setWho(User who) {
		this.who = who;
	}


	
	
}
