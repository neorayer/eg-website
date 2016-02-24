
package com.skymiracle.gameUnion.controllers;
import com.skymiracle.gameUnion.models.*;

import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;
@Sessioned
public class U_Ctr_FriendSetting extends U_Ctr {
	
	
	public void do_user_modprivacy(ActResult r) throws  AppException,  Exception {
		FriendSetting userfriend = $M(FriendSetting.class);
		userfriend.update($MM(FriendSetting.class));
	}
	
}


