package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.mdo5.Mdo_X;
import static com.skymiracle.gameUnion.Singletons.*;
public class LtMsgReqAddFriend extends LtMsg<LtMsgReqAddFriend>{

	public static class X extends Mdo_X<LtMsgReqAddFriend> {
		
	}
	
	@Length(64)
	private String sendername;
	

	private String sendernick;

	public LtMsgReqAddFriend() {
		super(LtMsgReqAddFriendX);
	}
	
	public LtMsgReqAddFriend(String sendername, String friendname) {
		this();
		this.sendername = sendername;
		this.dest = friendname;
	}
	
	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}


	public String getSendernick() {
		return sendernick;
	}

	public void setSendernick(String sendernick) {
		this.sendernick = sendernick;
	}

	@Override
	public String getCmd() {
		this.cmd = "ReqAddFriend";
		return cmd;
	}

	@Override
	public void setCmd(String cmd) {
		
	}

	@Override
	public String table() {
		return "tb_LtMsgReqAddFriend";
	}

	@Override
	public String[] keyNames() {
		return new String[]{"sendername", "dest"};
	}

}
