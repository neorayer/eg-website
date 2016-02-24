package com.skymiracle.gameUnion.controllers;
import com.skymiracle.gameUnion.models.hall.*;
import com.skymiracle.image.SkyImage;
import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.exception.AppException;

public class Oa_Ctr_Hall extends Oa_Ctr { 
	@NoSessioned
	public void vi_test(ActResult r) throws  AppException,  Exception {
	}

	public void vi_home(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_halls(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_hall_add(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_sidebar(ActResult r) throws  AppException,  Exception {
	}
	
	public void do_hall_add(ActResult r) throws  AppException,  Exception {
		Hall hall = $M(Hall.class);
		hall.setOrgId(org.getId());
		
		r.putMap("hall",hall.create());
		
		r.setRedirectTo("hall.jsplayout.vi?id=" + hall.getId());
	}
	
	public void vi_hall(ActResult r) throws  AppException,  Exception {
		r.putMap("hall", $M(Hall.class).load());
	}
	
	public void do_hall_del(ActResult r) throws  AppException,  Exception {
		$M(Hall.class).delete();
		r.setRedirectTo("home.jsplayout.vi");
	}
	
	public void vi_hall_mod(ActResult r) throws  AppException,  Exception {
		r.putMap("hall", $M(Hall.class).load());
	}
	
	public void do_hall_mod(ActResult r) throws  AppException,  Exception {
		Hall hall = $M(Hall.class);
		hall.update($MM(Hall.class));
		r.setRedirectTo("hall.jsplayout.vi?id=" + hall.getId());
	}

	public void do_hallLogo_mod(ActResult r) throws  AppException,  Exception {
		Hall hall = $M(Hall.class).load();
		hall.setLogo($File());
		r.setRedirectTo("hall.jsplayout.vi?id=" + hall.getId());
	}
	
	public void vi_logoImg(ActResult r) throws  AppException,  Exception {
		Hall hall = $M(Hall.class).load();
		SkyImage image = new SkyImageImpl(hall.getLogoFile().getAbsolutePath(), "jpg");
		r.setImage(image.getImage());
	}
	
	public void vi_menuItems(ActResult r) throws  AppException,  Exception {
	}
	
	public void do_menuItem_add(ActResult r) throws  AppException,  Exception {
		$M(HallMenuItem.class).create();
		r.setRedirectTo("hall.jsplayout.vi?id=" + $("hallid"));
	}
	
	public void do_menuItem_del(ActResult r) throws  AppException,  Exception {
		$M(HallMenuItem.class).delete();
		r.setRedirectTo("hall.jsplayout.vi?id=" + $("hallid"));
	}
	

}
