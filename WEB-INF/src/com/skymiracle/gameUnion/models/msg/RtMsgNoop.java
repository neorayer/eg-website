package com.skymiracle.gameUnion.models.msg;

public  class RtMsgNoop extends RtMsg<RtMsgNoop> {

	@Override
	public String getCmd() {
		return "noop";
	}

	@Override
	public void setCmd(String cmd) {
	}

	
}
