package com.skymiracle.gameUnion.controllers;
import com.skymiracle.gameUnion.models.org.*;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;
import static com.skymiracle.gameUnion.Singletons.*;

@Sessioned
public class Ra_Ctr_Org extends Ra_Ctr {


	public void vi_home(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_sidebar(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_orgs(ActResult r) throws  AppException,  Exception {
		r.putMap("orgs", OrgX.findAll());
	}
	
	public void vi_org_add(ActResult r) throws  AppException,  Exception {
	}
	
	public void do_org_add(ActResult r) throws  AppException,  Exception {
		Org org = $M(Org.class).create();
		r.putMap("org", org);
	}
	
	public void vi_org_add_ok(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_org_mod(ActResult r) throws  AppException,  Exception {
		r.putMap("org", $M(Org.class).load());
	}
	
	public void do_org_mod(ActResult r) throws  AppException,  Exception {
		r.putMap("org",$M(Org.class).update($MM(Org.class)));
		r.setRedirectTo("orgs.jsplayout.vi");
	}
	
	public void vi_org(ActResult r) throws  AppException,  Exception {
		r.putMap("org", $M(Org.class).load());
	}
	public void do_org_del(ActResult r) throws  AppException,  Exception {
		$M(Org.class).delete();
		r.setRedirectTo("orgs.jsplayout.vi");
	}
	
	public void vi_orgAdmins(ActResult r) throws  AppException,  Exception {
		r.putMap("admins",OrgAdminX.find("orgid", $("orgid")));
	}
	
	public void vi_orgAdmin_add(ActResult r) throws  AppException,  Exception {
		r.putMap("org", new Org($("orgid")).load());
	}
	
	public void do_orgAdmin_add(ActResult r) throws  AppException,  Exception {
		r.putMap("orgAdmin", $M(OrgAdmin.class).create());
		r.setRedirectTo("org.jsplayout.vi?id=" + $("orgid"));
	}
	public void vi_orgAdmin_del(ActResult r) throws  AppException,  Exception {
		$M(OrgAdmin.class).delete();
		r.setRedirectTo("org.jsplayout.vi?id=" + $("orgid"));
	}
}
