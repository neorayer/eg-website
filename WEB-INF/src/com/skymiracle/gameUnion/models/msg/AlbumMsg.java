package com.skymiracle.gameUnion.models.msg;
import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.exception.AppException;

public class AlbumMsg extends FreshMsg {



	/*
	 *  ${username}")创建了新的相册《XXXXXXXXXXXXXXX》
	 * 
	 *  ${username}")更新了《XXXXXXXXXXXXXXX》相册
	 * 
	 */

	private Album album;
	
	public AlbumMsg() {
		super();
	}
 
	public AlbumMsg(String sender, String receiver, MSGTYPE msgtype,Album album,int count) {
		super(sender, receiver,msgtype,album.getId(),count);
		this.album =album;
	}
	
	public AlbumMsg(String sender, String receiver, MSGTYPE msgtype,Album album) {
		super(sender, receiver,msgtype);
		this.album =album;
	}
	public AlbumMsg(String sender, String receiver, MSGTYPE msgtype) {
		super(sender, receiver,msgtype);
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
			s = s.replace("${username}", this.vi_albumMsg(album));
		if(s.indexOf("${title}") > 0)
			s = s.replace("${title}", this.vi_albumTitle(album));
		return new StringBuffer(s);
	}

	// 相册user信息
	public String vi_albumMsg(Album album) throws AppException, Exception {
		String event = "FreshMsg.vi_user('" + this.sender + "')";
		User user = new User(this.sender).load();
		String desc =user.getDispName();
		return buildEvent(event, desc);
	}

	// 相册标题信息
	public String vi_albumTitle(Album album) {
		StringBuffer buf = new StringBuffer();
		String event = "FreshMsg.vi_album('" + album.getUsername() + "','" + album.getId() + "');";
		buf.append("<a href=\"javascript:"+event+"\">");
		buf.append(album.getTitle());
		buf.append("</a>");
		return buf.toString();
	} 

	public String buildEvent(String event, String desc) {
		StringBuffer buf = new StringBuffer();
		buf.append("<a href=\"javascript:"+event+"\">");
		buf.append(desc);
		buf.append("</a>");
		return buf.toString();
	}



}
