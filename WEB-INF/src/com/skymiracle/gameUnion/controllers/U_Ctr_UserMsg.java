package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.*;

import com.skymiracle.gameUnion.models.Msg;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr_UserMsg extends U_Ctr_User {
	public void vi_msgs(ActResult r) throws  AppException,  Exception {
		do_message_readall(r);
		PagedList<UserMsg> messages = UserMsgX
		.findPaged($i("pageNum", 1), $i("perPage", 20),
				"receiver,createdatetime-", actorId,null);
		messages.setLinkPrefix("../userMsg/msgs.jsplayout.vi");
		r.putMap("messages", messages);
		r.putMap("pageBar", messages.getPageBarHTML());
	}
	@Sessioned
	public void do_message_del(ActResult r) throws AppException, Exception {
		$M(UserMsg.class).delete();
	}
	@Sessioned
	public void do_message_delall(ActResult r) throws AppException, Exception {
		UserMsgX.find("receiver",actorId).delete();
	}
	@Sessioned
	public void do_message_readall(ActResult r) throws AppException, Exception {
		MList<UserMsg> usermsgs=UserMsgX.find("receiver",actorId);
		MdoMap mdomap=new MdoMap();
		mdomap.put("readed", true);
		for (UserMsg usermsg:usermsgs) {
			usermsg.update(mdomap);
		}
	}

	public void do_message_invalid(ActResult r) throws AppException, Exception {
		$M(UserMsg.class).invalid();
	}

	public void vi_more(ActResult r) throws AppException, Exception {
	}

	// ////////////////////////

	public void do_message_reading(ActResult r) throws AppException, Exception {
		UserMsg message = new UserMsg($("id")).load();
		if (!message.getReaded())
			message.reading();
		r.putMap("message", message);
	}

	 

	// /////////////////////
	
	public void vi_NORMAL(ActResult r) throws  AppException,  Exception {
		do_message_reading(r);
	}
	
	public void vi_FRIEND_REQUEST(ActResult r) throws  AppException,  Exception {
		do_message_reading(r);
	}
	public void vi_FRIEND_REFUSE(ActResult r) throws  AppException,  Exception {
		do_message_reading(r);
	}
	public void vi_FRIEND_PASSED(ActResult r) throws  AppException,  Exception {
		do_message_reading(r);
	}
	public void vi_FRIEND_REMOVE(ActResult r) throws  AppException,  Exception {
		do_message_reading(r);
	}
	public void vi_FRIEND_PASSREQUEST(ActResult r) throws  AppException,  Exception {
		do_message_reading(r);
	}
}
