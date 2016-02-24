package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.AlbumX;
import static com.skymiracle.gameUnion.Singletons.GameTeamX;
import static com.skymiracle.gameUnion.Singletons.UserEgHistoryX;
import static com.skymiracle.gameUnion.Singletons.WarZoneX;
import static com.skymiracle.gameUnion.Singletons.UserX;

import java.util.Random;

import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr_Portal extends U_Ctr {

	public void vi_head(ActResult r) throws AppException, Exception {
	}

	public void vi_index(ActResult r) throws AppException, Exception {
	}

	public void vi_tail(ActResult r) throws AppException, Exception {
	}

	public void vi_login(ActResult r) throws AppException, Exception {
	}
	
	public void vi_authImg(ActResult r) throws AppException, Exception {
		String code = "" + (new Random().nextInt(8999) + 1000);
		putAuthImageCode(r, "imgCode", code);
	}
	
	public void vi_reg(ActResult r) throws AppException, Exception {
		r.putMap("warzones", WarZoneX.getItemsMap().values());
		if (actor != null)
			throw new AppException("温馨提示：本机上次登录的通行证用户名是：" + actor.getUsername()
					+ "!");
	}

	public void do_login(ActResult r) throws AppException, Exception {
		User user = $M(User.class);
		if (user.auth("password", $("password"))) {
			actorLogin(user.getUsername());
			return;
		}

		throw new AppException("用户名或者密码错误, 请重新输入!");
	}
	
	public void do_user_reg(ActResult actResult) throws AppException, Exception {
		checkAuthImageCode("imgCode");
		User user = $M(User.class);
		if (user.exists())
			throw new AppException("用户名已经存在！");
		user.setUsername($("username").toLowerCase());
		user.create();

		// 添加历史
		UserEgHistoryX.addRegUserHistory(user.load());

		// 添加头像相册
		AlbumX.addAvatorAlbum(user.load());

		actorLogin(user.getUsername());
	}

	public void do_logout(ActResult r) throws AppException, Exception {
		actorLogout(r);
	}

	public void vi_users(ActResult r) throws  AppException,  Exception {
		MList<User> users = UserX.find("createDateTime-:0,5", null);
		r.putMap("users", users);
	}
	
	public void vi_teams(ActResult r) throws AppException, Exception {
		MList<GameTeam> teams = GameTeamX.find("memberCount-:0,5", null);
		r.putMap("teams", teams);
	}
	
	/**
	 * 搜索用户
	 * 
	 */
	public void vi_searchUsers(ActResult r) throws  AppException,  Exception {
		String filter = null;
		String nickname = $("nickname");
		
		if (!"".equals(nickname) && nickname!=null) {
//			nickname =new String(nickname.getBytes("iso8859-1"),"UTF-8");
			filter=" nickname  LIKE '%" + nickname + "%'";
		}
		MdoMap mdoMap = new MdoMap();
		PagedList<User> users = UserX.findPaged(mdoMap, filter, "createdatetime", false, $i("pageNum",1), $i("perPage", 10));
		users.setLinkPrefix("../portal/searchUsers.jsplayout.vi");
		r.putMap("users", users);
		r.putMap("userNum", UserX.count());
		r.putMap("nickname", nickname);
		r.putMap("pageBar", users.getPageBarHTML());
	}
	
	/**
	 * 搜索战队
	 * 
	 */
	public void vi_searchTeams(ActResult r) throws  AppException,  Exception {
		String filter = null;
		String teamName = $("teamname");
		
		if (!"".equals(teamName) && teamName!=null) {
//			teamName =new String(teamName.getBytes("iso8859-1"),"UTF-8");
			filter=" teamName LIKE '%" + teamName + "%'";
		}
		MdoMap mdoMap = new MdoMap();
		PagedList<GameTeam> teams = GameTeamX.findPaged(mdoMap, filter, "memberCount", false, $i("pageNum",1), $i("perPage", 10));
		teams.setLinkPrefix("../portal/searchTeams.jsplayout.vi");
		r.putMap("teams", teams);
		r.putMap("teamNum", GameTeamX.count());
		r.putMap("teamname", teamName);
		r.putMap("pageBar", teams.getPageBarHTML());
	}
}
