package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.exception.AppException;
//extends FriendMsg {
public class ArticleMsg extends FreshMsg {

	 

	/*
	 *  ${username}")创建了新的个人日志《XXXXXXXXXXXXXXX》
	 * 
	 *  ${username}")更新了《XXXXXXXXXXXXXXX》日志
	 * 
	 */
 
	private Article article;
	

	public ArticleMsg() {
		super();
	}
	
	public ArticleMsg(String sender, String receiver, MSGTYPE msgtype,Article article) {
		super(sender, receiver,msgtype,article.getId());
		this.article =article;
	}
	public void putContent(String subject, String content)  throws AppException, Exception {
		this.content = filter(content);
		// 如果没标题，则直接用内容做标题
		if (subject == null)
			this.subject = this.content.toString();
		else
			this.subject = filter(subject).toString();
	}

	private StringBuffer filter(String s)  throws AppException, Exception {
		if(s.indexOf("${username}") > 0)
			s = s.replace("${username}", this.vi_articleMsg(article));
		if(s.indexOf("${title}") > 0)
			s = s.replace("${title}", this.vi_articleTitle(article));
		return new StringBuffer(s);
	}

	// 相册user信息
	public String vi_articleMsg(Article article) throws AppException, Exception {
		String event = "FreshMsg.vi_user('" + article.getAuthor() + "')";
		User user = new User(article.getAuthor()).load();
		String desc =user.getDispName();
		return buildEvent(event, desc);
	}

	// 相册标题信息
	public String vi_articleTitle(Article article) {
		StringBuffer buf = new StringBuffer();
		String event = "FreshMsg.vi_article('" + article.getAuthor() + "','" + article.getId() + "');";
		buf.append("<a href=\"javascript:"+event+"\">");
		buf.append(article.getTitle());
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
