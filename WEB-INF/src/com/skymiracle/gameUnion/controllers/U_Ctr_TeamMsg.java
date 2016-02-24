package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.*;

import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.gameUnion.models.team.TeamMember;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;


public class U_Ctr_TeamMsg extends U_Ctr_Team {

	public void do_message_del(ActResult r) throws AppException, Exception {
		$M(TeamMsg.class).delete();
	}

	public void do_message_invalid(ActResult r) throws AppException, Exception {
		$M(TeamMsg.class).invalid();
	}

	public void vi_msgs(ActResult r) throws AppException, Exception {
		if (team == null)
			return;
		
		PagedList<TeamMsg> messages = TeamMsgX
				.findPaged($i("pageNum", 1), $i("perPage", 20),
						"receiver,createdatetime-", team.getId(), null);
		messages.setLinkPrefix("../teamMsg/msgs.jsp.vi");
		r.putMap("messages", messages);
		r.putMap("pageBar", messages.getPageBarHTML());
	}

	public void vi_more(ActResult r) throws AppException, Exception {

		PagedList<TeamMsg> messages = TeamMsgX
				.findPaged($i("pageNum", 1), $i("perPage", 10),
						"receiver,createdatetime-", actor.getUsername());

		messages.setLinkPrefix("../message/more.jsp.vi");

		r.putMap("messages", messages);
		r.putMap("pageBar", messages.getPageBarHTML());
	}

	// ////////////////////////

	public void do_message_reading(ActResult r) throws AppException, Exception {
		TeamMsg msg = new TeamMsg($("id")).load();
		if (!msg.getReaded())
			msg.reading();
		r.putMap("message", msg);
	}

	public void vi_CREATING(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_DISBAND(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_USER_APPLY(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_PASS_APPLY(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_REFUSE_APPLY(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_CancelApplyToTeamMessage(ActResult r) throws AppException,
			Exception {
		do_message_reading(r);
	}

	public void vi_DELETE_MEMBER(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_USER_QUIT(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}

	public void vi_MEMBER(ActResult r) throws AppException, Exception {
		do_message_reading(r);
	}
}
