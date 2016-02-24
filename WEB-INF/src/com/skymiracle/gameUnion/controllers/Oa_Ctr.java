package com.skymiracle.gameUnion.controllers;

import java.io.IOException;

import com.skymiracle.gameUnion.models.org.*;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.controller.WebController;
import com.skymiracle.sor.exception.AppException;

@Sessioned
public class Oa_Ctr extends WebController<OrgAdmin> {

	protected Org org;
	
	@Override
	protected void afterActorLogout(ActResult actResult) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealWithNoSession(ActResult actResult) throws AppException,
			Exception {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void invokeBefore(ActResult r) throws AppException, Exception {
		this.org = new Org(resourceId).load();
		r.putMap("org", org);
	}

	@Override
	public OrgAdmin getActorFromId() throws Exception {
		try {
			this.org = new Org(resourceId).load();
			OrgAdmin oa = new OrgAdmin(resourceId, actorId).load();
		return oa;
		}catch(NotExistException e) {
			return null;
		}
	}
	
}
