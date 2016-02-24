package com.skymiracle.gameUnion.controllers;
import com.skymiracle.gameUnion.models.version.ServerVersion;
import com.skymiracle.logger.Logger;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.exception.AppException;
import static com.skymiracle.gameUnion.Singletons.*;

public class Ra_Ctr_Sys extends Ra_Ctr { 
	public void vi_home(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_sidebar(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_logger(ActResult r) throws  AppException,  Exception {
		r.putMap("loggerLevel", Logger.getLevelString());
	}
	
	public void do_logger_mod(ActResult r) throws  AppException,  Exception {
		Logger.setLevel($("level"));
		r.setRedirectTo("logger.jsplayout.vi");
	}
	
	public void vi_serverVersion(ActResult r) throws  AppException,  Exception {
		r.putMap("curVersion", ServerVersionX.getCurVersion());
	}
	
	public void vi_serverVersions(ActResult r) throws  AppException,  Exception {
		r.putMap("versions", ServerVersionX.find("publishtime+"));
	}
	
	public void do_serverVersion_add(ActResult r) throws  AppException,  Exception {
		$M(ServerVersion.class).create();
		r.setRedirectTo("serverVersion.jsplayout.vi");
	}

	public void do_serverVersion_del(ActResult r) throws  AppException,  Exception {
		$M(ServerVersion.class).delete();
		r.setRedirectTo("serverVersion.jsplayout.vi");
	}

	public void do_serverVersion_setCur(ActResult r) throws  AppException,  Exception {
		$M(ServerVersion.class).setCurVer($b("iscurversion"));
		r.setRedirectTo("serverVersion.jsplayout.vi");
	}
}
