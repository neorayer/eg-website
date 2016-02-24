package com.skymiracle.gameUnion.controllers;
import com.skymiracle.gameUnion.models.org.*;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;


@Sessioned
public class Oa_Ctr_Portal extends Oa_Ctr { 
	
	@NoSessioned
	public void vi_login(ActResult r) throws  AppException,  Exception {
	}
	
	@NoSessioned
	public void do_login(ActResult r) throws  AppException,  Exception {
		OrgAdmin oa = new OrgAdmin();
		oa.setOrgId(resourceId);
		oa.setUsername($("username"));
		oa.load();
		oa.auth("password", $("password"));
		
		actorLogin(oa.getPassword());
		
		r.setRedirectTo("home.jsplayout.vi");
	}
	
	public void vi_head(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_foot(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_home(ActResult r) throws  AppException,  Exception {
	}
}
