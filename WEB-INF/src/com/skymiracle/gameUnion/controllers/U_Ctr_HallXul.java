package com.skymiracle.gameUnion.controllers;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.controller.WebController;
import com.skymiracle.sor.exception.AppException;

import com.skymiracle.gameUnion.models.hall.Hall;
import com.skymiracle.gameUnion.models.msg.*;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.version.ServerVersion;
import com.skymiracle.gameUnion.util.CommonUtils;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.json.JSONTools;

import static com.skymiracle.gameUnion.Singletons.*;

@Sessioned
public class U_Ctr_HallXul extends WebController<User> {

	String lastVersion = "1.4.6";
	String HALL_HOST = "http://hall.ko10000.com";
	String PORTAL_HOST = "http://www.ko10000.com";

	protected Hall hall = null;

	@NoSessioned
	public void invokeBefore(ActResult r) throws AppException, Exception {
		// 部署版本编号，用于sor taglib，
		ServerVersion serverVersion = ServerVersionX.getCurVersion();
		String deployVer = serverVersion == null ? null : serverVersion
				.getVid();
		r.putMap("DEPLOY_VER", deployVer);

		r.putMap("skin", "wow");
		String hallId = (String) getRequest().getSession().getAttribute(
				"hallid");
		hall = hallId == null ? HallX.getDefault() : new Hall(hallId).load();
		r.putMap("hall", hall);
		if (hall.getSkinId() != null)
			r.putMap("skin", hall.getSkinId());
	}

	@NoSessioned
	public void vi_ol_page_login(ActResult r) throws AppException, Exception {
		// 把hallId放入HTTP session
		getRequest().getSession().setAttribute("hallid", $("hall"));

		// TODO:这句不一定需要，以后要考虑一下与invokeBefore()的关系
		// 因为invokeBefore需要从session中读取hallId,而hallId则来自login.jsp.vi后面的参数
		this.hall = new Hall($("hall")).load();
		r.putMap("hall", hall);
		if (hall.getSkinId() != null)
			r.putMap("skin", hall.getSkinId());

		//
	}

	@NoSessioned
	public void vi_loginEnv(ActResult r) throws AppException, Exception {
		r.putMap("url_ureg",
				"http://www.eg88.cn/eg/u/user/reg.jsplayout.vi");
		r.putMap("url_forget_pass",
				"http://www.eg88.cn/eg/u/user/finduser.jsplayout.vi?");
		r.putMap("url_login", hallXulUrl("login.json.do?"));
		r.putMap("url_check_version", hallXulUrl("checkVersion.json.do"));
	}

	@NoSessioned
	public void vi_ol_page_main(ActResult r) throws AppException, Exception {
	}

	@NoSessioned
	public void vi_ol_page_alert(ActResult r) throws AppException, Exception {

	}

	// TODO: 这里出于窗口打开的考虑，用NoSesssion
	@NoSessioned
	public void vi_ol_page_chat(ActResult r) throws AppException, Exception {

	}

	@NoSessioned
	public void vi_ol_page_setting(ActResult r) throws AppException, Exception {

	}

	@NoSessioned
	public void vi_ol_page_search_exe(ActResult r) throws AppException,
			Exception {

	}

	@NoSessioned
	public void vi_ol_page_face(ActResult r) throws AppException, Exception {
		List<String> faces = new LinkedList<String>();
		for (int i = 0; i < 24; i++) {
			String face = String.format(
					"%s/GameHall2/u/hallXul/face/e%02d.gif", HALL_HOST, i + 1);
			faces.add(face);
		}
		r.putMap("faces", faces);
	}

	public static class UserRemoteInfo {
		public String avatorUrl;
		
		public String avatorSmallUrl;
	}
	private UserRemoteInfo getUserRmoteData(String username) throws AppException,
			Exception {
		String s = ("" + StreamPipe.urlToString(PORTAL_HOST
				+ "/eg/u/user/userHallInfo.jsp.vi?username=" + username,
				"UTF-8")).trim();
		String[] parts = s.split("::::::");
		UserRemoteInfo uInfo = new UserRemoteInfo();
		uInfo.avatorUrl = PORTAL_HOST + parts[0];
		uInfo.avatorSmallUrl = PORTAL_HOST + parts[1];
		return uInfo;
	}

	@NoSessioned
	public void do_login(ActResult r) throws AppException, Exception {
		User gameUser = $M(User.class);
		if (!gameUser.auth("password", $("password"))) {
			throw new AppException("用户未通过认证，请检查您的用户名或密码");
		}

		gameUser.load();

		String realIp = getRequest().getRemoteAddr();

		gameUser.login(realIp);
		
		UserRemoteInfo urInfo = getUserRmoteData(gameUser.getUsername());

		r.putMap("gameuser", gameUser);
		r.putMap("response", "login");

		JSONObject jo = new JSONObject();

		// url环境参数
		jo.put("portal_host", PORTAL_HOST);
		jo.put("url_get_games", hallXulUrl("games.json.vi?"));
		jo.put("url_get_allzones", hallXulUrl("zones.json.vi?"));
		jo.put("url_get_game_zones", hallXulUrl("gameZones.json.vi?"));
		jo.put("url_get_game_rooms", hallXulUrl("gameRooms.json.vi?"));
		jo.put("url_u_enter_room", hallXulUrl("enterRoom.json.do?"));
		jo.put("url_u_leave_room", hallXulUrl("leaveRoom.json.do?"));
		jo.put("url_u_enter_game", hallXulUrl("enterGame.json.do?"));
		jo.put("url_u_leave_game", hallXulUrl("leaveGame.json.do?"));
		jo.put("url_msg", hallXulUrl("msg.json.vi"));
		jo.put("url_msg_chat", hallXulUrl("chatMsgSend.json.do?"));
		jo.put("url_game_room_users", hallXulUrl("roomUsers.json.vi?"));
		jo.put("url_logout", hallXulUrl("logout.json.do?"));
		jo.put("url_add_friend", hallXulUrl("friend.add.json.do?"));
		jo.put("url_req_add_friend", hallXulUrl("addFriend.req.json.do?"));
		jo.put("url_del_friend", hallXulUrl("friend.del.json.do?"));
		jo.put("url_list_friends", hallXulUrl("friends.json.vi?"));
		jo.put("url_fightlog_add", hallXulUrl("fightLog.add.json.do"));
		jo.put("url_fightres", hallXulUrl("page_fightres.jsp.vi?"));
		jo.put("url_myteam", hallXulUrl("myTeam.json.vi?"));
		jo.put("url_gameuser", hallXulUrl("gameUser.json.vi"));
		jo.put("url_goto_myteam",
				"http://eg88.cn/eg/u/user/index.jsplayout.vi");
		jo.put("url_user_avator", urInfo.avatorUrl);
		jo.put("url_user_small_avator", urInfo.avatorSmallUrl);
		jo.put("url_userinfo",
				"http://eg88.cn/eg/u/user/userinfo.mod.jsplayout.vi");
		jo
				.put(
						"url_game_maps",
						PORTAL_HOST
								+ "/GamePortal2/pg/vi/map/listMap.jsp?");

		jo.put("gamestarter", "gf_startgame.exe");
		jo.put("gamestart_param", "/d:gf.dll \"{GAMEPATH}\" {GAMEPARAM}");

		r.putMap("env", jo);
	}

	private String hallXulUrl(String action) {
		return HALL_HOST + "/GameHall2/u/hallXul/" + action;
	}

	@NoSessioned
	public void do_checkVersion(ActResult r) throws AppException, Exception {
		String version = $("version");
		String hallId = $("hallid");
		if (version == null)
			throw new AppException("错误的版本信息");
		if (!version.equals(lastVersion)) {
			r.putMap("response", "update_client");
			r.putMap("ver_version", lastVersion);
			r.putMap("ver_url_download", HALL_HOST
					+ "/GameHall2/ver/update/" + hallId + "/" + lastVersion +".zip");
		} else {
			r.putMap("response", "loginable");
		}
		;

	}

	public void do_logout(ActResult r) throws AppException, Exception {
		actor.logout();
	}

	public void vi_gameUser(ActResult r) throws AppException, Exception {
		User user = new User($("theusername")).load();
		user.setIpLocation(CommonUtils.getPrivacyLocation(user.getIpLocation()));
		r.putMap("gameuser", user);
	}

	@NoSessioned
	public void vi_games(ActResult r) throws AppException, Exception {
		r.putColl(GameX.findAll());
	}

	public void vi_gameZones(ActResult r) throws AppException, Exception {
		r.putColl(GameZoneX.find("gameid,sortnum+", $("gameid")));
	}

	public void vi_gameRooms(ActResult r) throws AppException, Exception {
		MList<GameRoom> rooms = GameRoomX.find("gameid,zoneid", $("gameid"),
				$("zoneid"));
		JSONArray ja = new JSONArray();
		for (GameRoom room : rooms) {
			JSONObject jo = JSONTools.getJSONObject(room);
			jo.put("curusercount", room.getCurUserCount());
			ja.put(jo);
		}
		r.setJson(ja);
	}

	public void vi_roomUsers(ActResult r) throws AppException, Exception {
		GameRoom room = new GameRoom($("roomid")).load();
		MList<User> users = room.getUsers();
		JSONArray ja = new JSONArray();
		for(User u : users) {
			u.setIpLocation(CommonUtils.getPrivacyLocation(u.getIpLocation()));
			JSONObject jo = JSONTools.getJSONObject(u);
			ja.put(jo);
		}
		r.setJson(ja);
	}

	public void do_enterRoom(ActResult r) throws AppException, Exception {
		int speed = $i("speed");
		GameRoom room = actor.enterRoom($("roomid"), speed, true);
		r.putMap("room", room);

		JSONObject jo = new JSONObject();
		jo.put("v_netmask_c", room.getNetmaskC());
		jo.put("v_ipaddr", actor.getVIpAddrStr());
		jo.put("pipe_host", room.getPipeHost());
		jo.put("pipe_tcpport", room.getPipeTcpPort());
		jo.put("pipe_udpport", room.getPipeUdpPort());
		jo.put("udpdig_port_bgn", room.getUdpdigPortBgn());
		jo.put("udpdig_port_end", room.getUdpdigPortEnd());
		jo.put("speed", speed);
		jo.put("has_tcp", room.getHasTcp() ? "1" : "0");
		jo.put("res_url", hallXulUrl("fightLog.add.json.do?"));
		jo.put("roomid", room.getRoomId());

		r.putMap("props", jo);
	}

	public void do_leaveRoom(ActResult r) throws AppException, Exception {
		actor.leaveRoom(true);
	}

	public void do_enterGame(ActResult r) throws AppException, Exception {
		int speed = $i("speed");
		actor.enterGame(speed);
	}

	public void do_leaveGame(ActResult r) throws AppException, Exception {
		actor.leaveGame();
	}

	public void vi_msg(ActResult r) throws AppException, Exception {
		// 心跳一下
		actor.doHeartBeat();

		RtMsg<?> msg = actor.getMsg();
		if (msg == null) {
			RtMsgX.waitFor(actor, 10000);
			msg = actor.getMsg();
			if (msg == null)
				return;
			else
				r.setMdo(msg);
			return;
		}
		r.setMdo(msg);
	}

	public void do_chatMsgSend(ActResult r) throws AppException, Exception {
		// r.addHeader("cache-control", "no-cache");

		String content = $("content");
		// content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		// System.out.println(content);

		String dest = $("dest");
		RtMsgChat rtMsgChat = new RtMsgChat();
		rtMsgChat.setSender(actor);
		rtMsgChat.setContent(content);
		rtMsgChat.setDest(dest);

		actor.sendMsg(rtMsgChat);
	}

	@NoSessioned
	public void vi_chatCss(ActResult r) throws AppException, Exception {
	}

	public void do_addFriend_req(ActResult r) throws AppException, Exception {
		User friendUser = actor.reqAddFriend($("friendname"));
		r.setMdo(friendUser);
	}

	public void do_friend_add(ActResult r) throws AppException, Exception {
		// TODO: 之前要检查是否有过邀请

		User friendUser = actor.addFriend($("friendname"));
		r.setMdo(friendUser);
	}

	public void do_friend_del(ActResult r) throws AppException, Exception {
		User friendUser = actor.delFriend($("friendname"));
		r.setMdo(friendUser);
	}

	public void vi_friends(ActResult r) throws AppException, Exception {
		r.putColl(actor.getFriends());
	}

	public void vi_myTeam(ActResult r) throws AppException, Exception {
		GameTeam team = actor.getTeam();
		if (team == null || team.getId() == null)
			return;
		r.putMap("team", team);
		r.putMap("members", team.getUsers());
	}

	public void do_fightLog_add(ActResult r) throws AppException, Exception {
		// 读取参数 记录Log
		GameRoom room = new GameRoom($("roomid")).load();
		FightLog fl = $M(FightLog.class);
		fl.setGameId(room.getGameId());
		fl.setZoneId(room.getZoneId());
		fl.create();

		// 生成消息并发送
		RtMsgFightRes msg = new RtMsgFightRes();
		msg.setFightLog(fl);
		RtMsgX.send(msg, room);
	}

	public void vi_page_fightres(ActResult r) throws AppException, Exception {
		FightLog fl = $M(FightLog.class).load();
		r.putMap("fightLog", fl);
	}

	public void do_fightLog_mod(ActResult r) throws AppException, Exception {

	}

	@Override
	protected void afterActorLogout(ActResult actResult) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealWithNoSession(ActResult r) throws AppException {
		throw new AppException("用户登录超时，为了您的帐户安全，请重新登录！");
	}

	@Override
	public User getActorFromId() throws Exception {
		return null;
	}

	public User getActorFromSessionKey() throws AppException, Exception {
		String sessionKey = $("sessionKey");
		if (sessionKey == null)
			return null;
		String username = $("username");
		if (username == null)
			return null;
		try {
			User u = new User(username).load();
			if (!sessionKey.equals(u.getSessionKey()))
				return null;
			return u;
		} catch (NotExistException e) {
			return null;
		}

	}

	@NoSessioned
	public void vi_listMap(ActResult r) throws AppException, Exception {
		r.putColl(GameMapX.find("gameid, zoneid", $("gameid"), $("zoneid")));
	}
	
	@NoSessioned
	public void do_gamemap_download(ActResult r) throws  AppException,  Exception {
		File file = new GameMap($("gameid"), $("zoneid"), $i("checksum")).getMapFile();
		r.putFile(file, file.getName());
	}
}
