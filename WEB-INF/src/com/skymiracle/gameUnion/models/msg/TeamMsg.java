package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.Msg;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class TeamMsg extends Msg<TeamMsg> {

	public static class X extends Msg.X<TeamMsg> {

		public long getApplys2TeamCount(GameTeam team) throws AppException, Exception {
			return count("msgType, receiver, status", MsgType.USER_APPLY.toString(), team.getId(), true);
		}
	}

	private String srcUsername;

	private String descUsername;

	public static enum MsgType {
		USER_APPLY, PASS_APPLY, REFUSE_APPLY, USER_CANCEL_APPLY, USER_QUIT, DELETE_MEMBER
	}

	public TeamMsg() {
		super(TeamMsgX);
	}

	public TeamMsg(String id) {
		this();
		this.id = id;
	}

	public String getSrcUsername() {
		return srcUsername;
	}

	public void setSrcUsername(String srcUsername) {
		this.srcUsername = srcUsername;
	}

	public String getDescUsername() {
		return descUsername;
	}

	public void setDescUsername(String descUsername) {
		this.descUsername = descUsername;
	}

	public TeamMsg(String teamId, MsgType msgType, String srcUsername,
			String descUsername) throws AppException, Exception {
		this();

		this.sender = EG_SYS;
		this.receiver = teamId;
		this.msgType = msgType.toString();
		this.srcUsername = srcUsername;
		this.descUsername = descUsername;
	}

	public void putContent(String content) throws AppException,
			Exception {
		this.content = filter(content);
	}

	private StringBuffer filter(String s) throws AppException, Exception {
		if (s.indexOf("{SRC}") > -1)
			s = s.replace("{SRC}", this.vi_user(getSrcUser()));
		if (s.indexOf("{DESC}") > -1)
			s = s.replace("{DESC}", this.vi_user(getDescUser()));
		return new StringBuffer(s);
	}

	public String buildEvent(String event, String desc) {
		StringBuffer buf = new StringBuffer();
		buf.append("<a href=\"javascript:\" onclick=\"" + event + "\">");
		buf.append(desc);
		buf.append("</a>");

		return buf.toString();
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_team_msg";
	}

	public User getSrcUser() throws AppException, Exception {
		return new User(srcUsername).load();
	}

	public User getDescUser() throws AppException, Exception {
		return new User(descUsername).load();
	}

	// 查看用户
	public String vi_user(User user) {
		String event = "TeamMsg.vi_user('" + user.getUsername() + "')";
		String desc = user.getNickname();

		return buildEvent(event, desc);
	}
}
