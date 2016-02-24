package com.skymiracle.gameUnion.controllers;
import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;
	
public class Ra_Ctr_Room extends Ra_Ctr { 
	public void vi_home(ActResult r) throws  AppException,  Exception {
		r.putMap("games", GameX.findAll());
	}
	
	public void vi_zones(ActResult r) throws  AppException,  Exception {
		r.putMap("zones", GameZoneX.find("gameid", $("gameid")));
	}
	
	public void vi_rooms(ActResult r) throws  AppException,  Exception {
		r.putMap("rooms", GameRoomX.find("gameid,zoneid", $("gameid"), $("zoneid")));
	}
	
	public void vi_users(ActResult r) throws  AppException,  Exception {
		r.putMap("users", UserX.find("roomid,vipaddr+", $("roomid"), null));
	}
	
	public void do_timeoutCheck(ActResult r) throws  AppException,  Exception {
		new GameRoom($("roomid")).load().checkTimeout();
	}
	
}
