package com.skymiracle.gameUnion.controllers;
import static com.skymiracle.gameUnion.Singletons.*;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.gameUnion.models.msg.*;
import com.skymiracle.mdo4.*;
import com.skymiracle.mdo5.*;
import com.skymiracle.sor.*;
import com.skymiracle.sor.annotation.*;
import com.skymiracle.sor.exception.*;

public class U_Ctr_FreshMsg  extends U_Ctr {
	@Sessioned
	public void vi_msgs(ActResult r) throws  AppException,  Exception {
		PagedList<FreshMsg> freshmsgs = FreshMsgX.findPaged($i("pageNum", 1), $i("perPage", 30),
				"receiver,createdatetime-", actorId,null);
		freshmsgs.setLinkPrefix("../freshMsg/msgs.jsplayout.vi");
		r.putMap("freshmsgs", freshmsgs);
		r.putMap("pageBar", freshmsgs.getPageBarHTML());
	}
	@Sessioned
	public void do_message_del(ActResult r) throws AppException, Exception {
		$M(FreshMsg.class).delete();
	}
	@Sessioned
	public void do_message_delall(ActResult r) throws AppException, Exception {
		FreshMsgX.find("receiver",actorId).delete();
	}

}
