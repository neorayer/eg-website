package com.skymiracle.gameUnion.controllers;

import java.io.IOException;

import com.skymiracle.gameUnion.SHA1;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.controller.WebController;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr extends WebController<User> {
	
	@Override
	protected void afterActorLogout(ActResult r) throws IOException {
		r.setRedirectTo("../portal/index.jsplayout.vi");
	}

	@Override
	public void dealWithNoSession(ActResult r) throws AppException,
			Exception {
		r.setRedirectTo("../portal/index.jsplayout.vi");
	}

	@Override
	public User getActorFromId() throws Exception {
		String username = actorId;
		try {
			return new User(username).load();
		} catch (NotExistException e) {
			return null;
		}
	}
	

	@Override
	public User getActorFromSessionKey() throws AppException, Exception {
		String sessionKey = $("sessionKey");
		if (sessionKey == null)
			return null;
		String username = $("username");
		if (username == null)
			return null;
		try {
			User u = new User(username).load();
			if (!sessionKey.equals(new SHA1(u.getPassword()).encrypting()))
				return null;
			actorLogin(u.getUsername());
			return u;
		} catch (NotExistException e) {
			return null;
		}

	}

}
