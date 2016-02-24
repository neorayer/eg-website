package com.skymiracle.gameUnion.controllers;

import java.io.File;
import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;
import static com.skymiracle.gameUnion.Singletons.*;
@Sessioned
public class Ra_Ctr_Portal  extends Ra_Ctr {
	@NoSessioned
	public void vi_test(ActResult r) throws  AppException,  Exception {
	}
	@NoSessioned
	public void vi_login(ActResult r) throws  AppException,  Exception {
	}

	@NoSessioned
	public void do_login(ActResult r) throws  AppException,  Exception {
		RootAdmin ra = new RootAdmin($("username"));
		ra.auth("password", $("password"));
		ra.load();
		actorLogin(ra.getUsername());
		
		r.setRedirectTo("home.jsplayout.vi");
	}
	
	@NoSessioned
	public void do_logout(ActResult r) throws  AppException,  Exception {
		actorLogout(r);
		r.setRedirectTo("../portal/login.jsp.vi");
	}
	
	public void do_importIps(ActResult r) throws  AppException,  Exception {
		IpLocX.importFile(new File("/tmp/ips.txt"));
	}
	public void vi_head(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_foot(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_home(ActResult r) throws  AppException,  Exception {
	}
	
	
}
