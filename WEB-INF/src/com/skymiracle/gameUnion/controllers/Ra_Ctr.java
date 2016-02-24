package com.skymiracle.gameUnion.controllers;

import java.io.IOException;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.controller.WebController;
import com.skymiracle.sor.exception.AppException;

@Sessioned
public class Ra_Ctr extends WebController<RootAdmin> {

	
	
	@Override
	protected void afterActorLogout(ActResult r) throws IOException {
	
		
	}

	@Override
	public void dealWithNoSession(ActResult actResult) throws AppException,
			Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RootAdmin getActorFromId() throws Exception {
		try {
		RootAdmin ra = new RootAdmin(actorId);
		ra.load();
		return ra;
		}catch(NotExistException e) {
			return null;
		}
	}
	
}
