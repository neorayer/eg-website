package com.skymiracle.gameUnion.models.msg;
import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.exception.AppException;

public class FriendMsg extends FreshMsg {



	/*
	 *  XX  与 XX 加为好友。
	 */

	private UserFriend userfriend;
	
	public FriendMsg() {
		super();
	}
 
	public FriendMsg(String sender, String receiver, MSGTYPE msgtype,UserFriend userfriend) {
		super(sender, receiver,msgtype);
		this.userfriend =userfriend;
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
		if(s.indexOf("${username}") > 0)
			s = s.replace("${username}", this.vi_username(userfriend));
		if(s.indexOf("${friendname}") > 0)
			s = s.replace("${friendname}", this.vi_friendname(userfriend));
		 
		return new StringBuffer(s);
	}

	// user信息
	public String vi_username(UserFriend userfriend) throws AppException, Exception {
		String event = "FreshMsg.vi_user('" + userfriend.getUsername() + "')";
		User user = new User(userfriend.getUsername()).load();
		String desc =user.getDispName();
		return buildEvent(event, desc);
	}

	// 好友信息
	public String vi_friendname(UserFriend userfriend) throws AppException, Exception {
		String event = "FreshMsg.vi_user('" + userfriend.getFriendname() + "')";
		User user = new User(userfriend.getFriendname()).load();
		String desc =user.getDispName();
		return buildEvent(event, desc);
	}

	public String buildEvent(String event, String desc) {
		StringBuffer buf = new StringBuffer();
		//buf.append("<a href=\"javascript:\" onclick=\"" + event + "\">");
		buf.append("<a href=\"javascript:"+event+"\">");
		buf.append(desc);
		buf.append("</a>");
		return buf.toString();
	}



}
