package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.exception.AppException;

public class RtMsgFightRes extends RtMsg<RtMsgFightRes>{

	private String content;
	
	private FightLog fightLog;

	@Override
	public String getCmd() {
		return "fightRes";
	}
	
	@Override
	public void setCmd(String cmd) {
		this.cmd = "fightRes";
	}

	public String getContent() throws AppException, Exception {
		 content = fightLog.getShortDesc();
		 return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FightLog getFightLog() {
		return fightLog;
	}

	public void setFightLog(FightLog fightLog) {
		this.fightLog = fightLog;
	}

	
}
