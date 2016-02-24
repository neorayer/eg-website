package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.UserFriendX;
import static com.skymiracle.gameUnion.Singletons.UserX;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.gameUnion.models.FreshMsg.MSGTYPE;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;

public class U_Ctr_UserFriend extends U_Ctr {

	public void vi_friends(ActResult r) throws AppException, Exception {
		MList<UserFriend> friends = UserFriendX.find(
				"username,state,createdatetime-", actorId,
				UserFriend.STATE.NOGO, null);
		r.putMap("friends", friends);

		MdoMap mdomap = new MdoMap();
		mdomap.put("username", actorId);
		mdomap.put("state", UserFriend.STATE.PASSED);
		PagedList<UserFriend> myfriends = UserFriendX.findPaged(mdomap, null,
				"createdatetime", false, $i("pageNum", 1), $i("perPage", 12));
		myfriends.setLinkPrefix("../userFriend/index.jsp.vi");
		r.putMap("myfriends", myfriends);
		r.putMap("pageBar", (myfriends).getPageBarHTML());
		r.putMap("totalcount", myfriends.size());
	}
	@Sessioned
	public void vi_find(ActResult r) throws AppException, Exception {
		String searchuser = $("searchuser") == null ? "" : $("searchuser")
				.trim();
		MdoMap mdomap = new MdoMap();
		String filterString=" username <> '"+ actorId + "' ";
		if (!searchuser.equals("")) {
			filterString+="and nickname LIKE '%"+$("searchuser")+"%'";
		}
		PagedList<User> users = UserX.findPaged(mdomap, filterString, "createdatetime",
				false, $i("pageNum", 1), $i("perPage",10));
		users.setLinkPrefix("../userFriend/find.jsplayout.vi");
		r.putMap("users", users);
		r.putMap("pageBar", users.getPageBarHTML());

	}
	@Sessioned
	public void vi_control(ActResult r) throws  AppException,  Exception {
		FriendSetting setting = new FriendSetting(actorId);
		if (setting.exists()) {
			setting.load();
		} else {
			setting.create();
		}
		r.putMap("userfriend", setting);
	}
	
	@Sessioned
	public void vi_add(ActResult r) throws AppException, Exception {
		String friendname = $("friendname");
		if (friendname.equals(actorId))
			throw new AppException("不能添加自己");
		UserFriend friend = new UserFriend(actorId, friendname);
		if (friend.exists())
			throw new AppException(friendname + "已成为您的好友");
		FriendSetting setting = new FriendSetting($("friendname"));
		if (setting.exists()) {
			setting.load();
		} else {
			setting.create();
		}
		r.putMap("userfriend", setting);
	}
	
	@Sessioned
	public void do_userfriend_addbyanyone(ActResult r) throws AppException,
			Exception {
		UserFriend myfriend = new UserFriend(actorId, $("friendname"));
		UserFriend youfriend = new UserFriend($("friendname"), actorId);
		if (myfriend.exists() || youfriend.exists()) {
			throw new AppException("温馨提示：你已经是" + youfriend.getDispName()
					+ "的好友!");
		} else {
			myfriend.setState(UserFriend.STATE.PASSED);
			myfriend.setPassedDate(CalendarUtil.getLocalDate());
			myfriend.setPassedDateTime(CalendarUtil.getLocalDateTime());
			youfriend.setState(UserFriend.STATE.PASSED);
			youfriend.setPassedDate(CalendarUtil.getLocalDate());
			youfriend.setPassedDateTime(CalendarUtil.getLocalDateTime());
			myfriend.create();
			youfriend.create();
			// 发送创建消息
			myfriend.msg(UserMsg.MsgType.FRIEND_PASSED,
					"你 与 ${userfriend} 成为好友");
			youfriend.msg(UserMsg.MsgType.FRIEND_PASSED,
					"你 与 ${userfriend} 成为好友");
			
			youfriend.msg(UserMsg.MsgType.FRIEND_PASSREQUEST,
			" ${userfriend} 通过了你的好友请求，并把你加为他的好友");
			
			
			MList<UserFriend> friends=UserFriendX.find("username,state", youfriend.getUsername(),UserFriend.STATE.PASSED);
			for (UserFriend friend:friends) {
				friend.msgFriend(MSGTYPE.FRIEND, " ${username} 与 ${friendname} 加为好友。", youfriend);
			}
			MList<UserFriend> myfriends=UserFriendX.find("username,state", myfriend.getUsername(),UserFriend.STATE.PASSED);
			for (UserFriend friend:myfriends) {
				friend.msgFriend(MSGTYPE.FRIEND, " ${username} 与 ${friendname} 加为好友。", myfriend);
			}
		}
	}
	@Sessioned
	public void do_userfriend_addbysomeone(ActResult r) throws AppException,
			Exception {
		UserFriend myfriend = new UserFriend(actorId, $("friendname"));
		UserFriend youfriend = new UserFriend($("friendname"), actorId);
		if (myfriend.exists()||youfriend.exists()) {
			throw new AppException("温馨提示：你已经是" + youfriend.getDispName()
					+ "的好友!");
		} else {
			youfriend.setState(UserFriend.STATE.NOGO);
			youfriend.setSubject($("subject"));
			youfriend.create();
			
			myfriend.setState(UserFriend.STATE.AWAIT);
			myfriend.create();
		}
		youfriend.msg(UserMsg.MsgType.FRIEND_REQUEST, " ${userfriend} 请求加你为好友");
	}
	@Sessioned
	public void do_userfriend_pass(ActResult r) throws AppException, Exception {
		UserFriend myfriend = new UserFriend(actorId, $("friendname"));
		UserFriend youfriend = new UserFriend($("friendname"), actorId);
		if ( !myfriend.exists() || !youfriend.exists()) {
			throw new AppException("温馨提示：操作失败！");
		}
		MdoMap mdoMap = new MdoMap();
		mdoMap.put("state", UserFriend.STATE.PASSED);
		mdoMap.put("passeddate", CalendarUtil.getLocalDate());
		mdoMap.put("passeddatetime", CalendarUtil.getLocalDateTime());
		myfriend.update(mdoMap);
		youfriend.update(mdoMap);
		
		youfriend.msg(UserMsg.MsgType.FRIEND_PASSREQUEST,
				" ${userfriend} 通过了你的好友请求，并把你加为他的好友");
		myfriend.msg(UserMsg.MsgType.FRIEND_PASSREQUEST,
		"你 与 ${userfriend} 成为好友");
		
		MList<UserFriend> friends=UserFriendX.find("username,state", youfriend.getUsername(),UserFriend.STATE.PASSED);
		for (UserFriend friend:friends) {
			friend.msgFriend(MSGTYPE.FRIEND, " ${username} 与 ${friendname} 加为好友。", youfriend);
		}
		
		MList<UserFriend> myfriends=UserFriendX.find("username,state", myfriend.getUsername(),UserFriend.STATE.PASSED);
		for (UserFriend friend:myfriends) {
			friend.msgFriend(MSGTYPE.FRIEND, " ${username} 与 ${friendname} 加为好友。", myfriend);
		}
		
		
	}
	@Sessioned
	public void do_userfriend_ignore(ActResult r) throws AppException,
			Exception {
		UserFriend myfriend = new UserFriend(actorId, $("friendname")).load();
		UserFriend youfriend = new UserFriend($("friendname"), actorId).load();

		myfriend
				.msg(UserMsg.MsgType.FRIEND_REFUSE, "你拒绝了 ${userfriend} 的好友请求。");
		youfriend
				.msg(UserMsg.MsgType.FRIEND_REFUSE, " ${userfriend} 拒绝了你的好友请求。");
		
		if (myfriend.exists()) {
			myfriend.delete();
		}
		
		youfriend.delete();
	}
	@Sessioned
	public void do_userfriend_del(ActResult r) throws AppException, Exception {
		UserFriend myfriend = new UserFriend(actorId, $("friendname")).load();
		UserFriend youfriend = new UserFriend($("friendname"), actorId).load();
		// remove XX 已和你解除好友关系
		myfriend
				.msg(UserMsg.MsgType.FRIEND_REMOVE, "你已和 ${userfriend} 解除好友关系。");
		youfriend.msg(UserMsg.MsgType.FRIEND_REMOVE,
				" ${userfriend} 已和你解除好友关系。");
		myfriend.delete();
		youfriend.delete();
	}
}
