package com.skymiracle.gameUnion.controllers;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.gameUnion.models.msg.*;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr_Comment  extends U_Ctr {
	
	public void do_comment_add(ActResult r) throws  AppException,  Exception {
		Comment comment=$M(Comment.class).create();
		if (comment.getOwner()!=actorId) {
			//陶睿荣... 在 每天必上的网站 回复你 
			comment.msg(CommentMsg.MsgType.COMMENT, " ${author} 在 ${Object} 回复你",comment);
		}
	}
	
	public void do_comment_del(ActResult r) throws  AppException,  Exception {
		$M(Comment.class).delete();
	}
	
	/*
	 * 相册评论
	 */
	public User getPlayer(String username) throws AppException, Exception{
		User player=null;
		if(username!= null && !"".equals(username)){
				player=new User(username).load();
		}else if(actorId != null && !"".equals(actorId)){
			player=new User(actorId).load();
		}
		return player;
	}
	public void vi_comment_add(ActResult r) throws  AppException,  Exception {
		r.putMap("player", getPlayer($("username")));
	}
	public void vi_album_comments(ActResult r) throws  AppException,  Exception {
		r.putMap("player", getPlayer($("username")));
		r.putMap("album", new Album($("rootid"),$("username")).load());
	}
	public void vi_photo_comments(ActResult r) throws  AppException,  Exception {
		r.putMap("player", getPlayer($("username")));
		r.putMap("photo", new Photo($("rootid")).load());
	}
	
	/*
	 * article评论
	 */
 
	public void vi_article_comments(ActResult r) throws  AppException,  Exception {
		r.putMap("player", getPlayer($("username")));
		r.putMap("article", new Article($("rootid")).load());
	}
	
}
