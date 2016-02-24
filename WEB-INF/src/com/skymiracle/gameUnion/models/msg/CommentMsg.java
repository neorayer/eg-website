package com.skymiracle.gameUnion.models.msg;

import static com.skymiracle.gameUnion.Singletons.CommentMsgX;

import com.skymiracle.gameUnion.models.Album;
import com.skymiracle.gameUnion.models.Article;
import com.skymiracle.gameUnion.models.Comment;
import com.skymiracle.gameUnion.models.Msg;
import com.skymiracle.gameUnion.models.Photo;
import com.skymiracle.sor.exception.AppException;

public class CommentMsg   extends Msg<CommentMsg> {

	public static class X extends Msg.X<CommentMsg> {
 
	}
	@Title("请求|通过请求|成为好友|拒绝|解除")
	public static enum MsgType {
		 NORMAL,COMMENT
	}
	private Comment comment;
	public CommentMsg() {
		super(CommentMsgX);
	}

	public CommentMsg(String sender, String receiver, MsgType msgType,Comment comment) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = MsgType.NORMAL.toString();
		this.comment=comment;
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
		 * comment
		 */
		if(s.indexOf("${author}") > 0)
			s = s.replace("${author}", this.vi_author(comment));
		if(s.indexOf("${Object}") > 0)
			s = s.replace("${Object}", this.vi_title(comment));
		return new StringBuffer(s);
	}

 
	public String buildEvent(String event, String desc) {
		StringBuffer buf = new StringBuffer();
		buf.append("<a href=\"javascript:"+event+"\">");
		buf.append(desc);
		buf.append("</a>");
		return buf.toString();
	}

	// 我的信息
	public String vi_author(Comment comment) throws AppException, Exception {
		String event = "UserMsg.vi_user('" + comment.getAuthor() + "')";
		String desc = comment.getAuthorDN();
		return buildEvent(event, desc);
	}
	
	private String vi_title(Comment comment) throws AppException, Exception {
		if (comment.getType()==Comment.ROOTTYPE.ALBUM) {
			Album album=new Album(comment.getRootid(),comment.getOwner()).load();
			String event = "UserMsg.vi_album('" + album.getUsername() + "','" + album.getId() + "');";
			String desc = album.getTitle();
			return buildEvent(event, desc);
		}else if(comment.getType()==Comment.ROOTTYPE.PHOTO){
			Photo photo=new Photo(comment.getRootid()).load();
			Album album=photo.getAlbum();
			String event = "UserMsg.vi_photo('" + photo.getUsername() + "','" + photo.getId() + "');";
			String desc = album.getTitle();
			return buildEvent(event, desc);
		}else if(comment.getType()==Comment.ROOTTYPE.ARTICLE){
			Article article=new Article(comment.getRootid()).load();
			String event = "UserMsg.vi_article('" + article.getAuthor() + "','" + article.getId() + "');";
			String desc = article.getTitle();
			return buildEvent(event, desc);
		}else {
			throw new AppException("无此类型评论") ;
		}
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
