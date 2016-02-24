package com.skymiracle.gameUnion.models.msg;

import static com.skymiracle.gameUnion.Singletons.UserMsgX;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.exception.AppException;

public class UserMsg extends Msg<UserMsg> {

	public static class X extends Msg.X<UserMsg> {

		public void send(UserMsg msg) throws AppException, Exception {
			msg.create();
		}

	}

	/*
	 * request XX 请求加你为好友。
	 * 
	 * PASSrequest XX 通过了你的好友请求，并把你加为他的好友。
	 * 
	 * PASSED： XX（你） 与 XX 成为好友
	 * 
	 * refuse：XX 拒绝了你的好友请求。
	 * 
	 * remove XX 已和你解除好友关系
	 */
	@Title("请求|通过请求|成为好友|拒绝|解除")
	public static enum MsgType {
		FRIEND_REQUEST, FRIEND_PASSREQUEST, FRIEND_PASSED, FRIEND_REFUSE, FRIEND_REMOVE, NORMAL,
	}

	private UserFriend userfriend;
	
	
	public UserMsg() {
		super(UserMsgX);
		this.msgType = MsgType.NORMAL.toString();
	}
	
	public UserMsg(String id) {
		this();
		this.id = id;
	}

	public UserMsg(String sender, String receiver, MsgType msgType,UserFriend userfriend) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = msgType.toString();
		this.userfriend =userfriend;
	}
	 
	public UserMsg(String sender, String receiver) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = MsgType.NORMAL.toString();
	}

	public UserMsg(String sender, String receiver, MsgType msgType) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = msgType.toString();
	}

	public void putContent(String subject, String content) throws AppException, Exception {
		this.content = filter(content);
		// 如果没标题，则直接用内容做标题
		if (subject == null)
			this.subject = this.content.toString();
		else
			this.subject = filter(subject).toString();
	}

	private StringBuffer filter(String s) throws AppException, Exception {
		/*
		 * userfriend
		 */
		if(s.indexOf("${userfriend}") > 0)
			s = s.replace("${userfriend}", this.vi_user(userfriend));
		if(s.indexOf("${username}") > 0)
			s = s.replace("${username}", this.vi_username(userfriend));
		return new StringBuffer(s);
	}


	// 查看好友
	public String vi_user(UserFriend user) throws AppException, Exception {
		String event = "UserMsg.vi_user('" + user.getFriendname() + "')";
		String desc = user.getDispName();
		return buildEvent(event, desc);
	}

	// 我的信息
	public String vi_username(UserFriend user) throws AppException, Exception {
		String event = "UserMsg.vi_user('" + user.getUsername() + "')";
		String desc = user.getDispName();
		return buildEvent(event, desc);
	}

	public String buildEvent(String event, String desc) {
		StringBuffer buf = new StringBuffer();
	//	buf.append("<a href=\"javascript:\" onclick=\"" + event + "\">");
		buf.append("<a href=\"javascript:"+event+"\">");
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
		return "tb_user_msg";
	}

}
