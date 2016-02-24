package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.User;

public  class RtMsgRoomInOut extends RtMsg<RtMsgRoomInOut> {

	private boolean isIn = true;
	
	private User who;
	
	private int speed = -1;
	
	@Override
	public String getCmd() {
		return "roomInOut";
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
}
