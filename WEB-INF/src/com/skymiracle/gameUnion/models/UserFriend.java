package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import com.skymiracle.gameUnion.models.*;
import com.skymiracle.gameUnion.models.msg.*;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;
import com.skymiracle.gameUnion.models.FreshMsg.MSGTYPE;
import com.skymiracle.gameUnion.models.msg.UserMsg.MsgType;
public class UserFriend extends AbsMdo<UserFriend> {

	@Title("用户名")
	@Length(32)
	private String username;

	@Title("好友用户名")
	@Length(32)
	private String friendname;
	
	@Title("好友别名")
	private String alias;

	@Title("未通过|等待通过|已通过|特别好友")
	public static enum STATE {
		NOGO,AWAIT, PASSED, SPECIAL
	};

	@Title("好友状态")
	private STATE state = STATE.NOGO;

	@Title("申请主题")
	private String subject;

	@Title("通过时间")
	private String passedDate;

	@Title("通过时间")
	private String passedDateTime;

	@Title("申请时间")
	private String createDate = CalendarUtil.getLocalDate();

	@Title("申请时间")
	private String createDateTime = CalendarUtil.getLocalDateTime();

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFriendname() {
		return friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPassedDate() {
		return passedDate;
	}

	public void setPassedDate(String passedDate) {
		this.passedDate = passedDate;
	}

	public String getPassedDateTime() {
		return passedDateTime;
	}

	public void setPassedDateTime(String passedDateTime) {
		this.passedDateTime = passedDateTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "username", "friendname" };
	}

	@Override
	public String table() {
		return "tb_gameHallUserFriend";
	}

	public UserFriend() {
		super(UserFriendX);
	}

	public UserFriend(String username, String friendname) {
		this();
		this.username = username;
		this.friendname = friendname;
	}

	public static class X extends Mdo_X<UserFriend> {
	}

 
	public void msg(MsgType msgType,String content) throws AppException, Exception {
		UserMsg msg = new UserMsg(UserMsg.EG_SYS, username,msgType,this);
		msg.putContent(null, content);
		msg.create();
	}
	public void msgAlbum(MSGTYPE msgtype,String content,Album album,int count) throws AppException, Exception {
		AlbumMsg msg = new AlbumMsg(username, friendname,msgtype,album,count);
		msg.putContent(null, content);
		msg.create();
	}
	
	public void msgAvator(MSGTYPE msgtype,String content) throws AppException, Exception {
		AlbumMsg msg = new AlbumMsg(username, friendname,msgtype);
		msg.putContent(null, content);
		msg.create();
	}
	public void msgArticle(MSGTYPE msgtype,String content,Article article) throws AppException, Exception {
		ArticleMsg msg = new ArticleMsg(username, friendname,msgtype,article);
		msg.putContent(null, content);
		msg.create();
	}
	public void msgFriend(MSGTYPE msgtype,String content,UserFriend userfriend) throws AppException, Exception {
		FriendMsg msg = new FriendMsg(username, friendname,msgtype,userfriend);
		msg.putContent(null, content);
		msg.create();
	}
	public String getAvatorUrl() throws AppException, Exception {
		User user = new User(friendname).load();
		File logoFile = user.getAvatorFile();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.friendname + "/1/" + this.friendname+ ".jpg?" + logoFile.lastModified();
		else
			return "/51bisaifiles/user/default.jpg";
	}
	public String getDispName() throws AppException, Exception {
		User user = new User(friendname).load();
		return user.getDispName();
	}
	public String getAvatorSmallUrl() throws AppException, Exception {
		User user = new User(friendname).load();
		return user.getAvatorSmallUrl();
	}
}
