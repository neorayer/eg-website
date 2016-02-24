package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.FightHistoryX;
import static com.skymiracle.gameUnion.Singletons.FreshMsgX;
import static com.skymiracle.gameUnion.Singletons.MatchTypeX;
import static com.skymiracle.gameUnion.Singletons.MessageBoardX;
import static com.skymiracle.gameUnion.Singletons.PhotoX;
import static com.skymiracle.gameUnion.Singletons.ReplayHistoryX;
import static com.skymiracle.gameUnion.Singletons.ReputationX;
import static com.skymiracle.gameUnion.Singletons.UserBattleFriendX;
import static com.skymiracle.gameUnion.Singletons.UserCallerX;
import static com.skymiracle.gameUnion.Singletons.UserEgHistoryX;
import static com.skymiracle.gameUnion.Singletons.UserFriendX;
import static com.skymiracle.gameUnion.Singletons.UserMsgX;
import static com.skymiracle.gameUnion.Singletons.UserX;
import static com.skymiracle.gameUnion.Singletons.WarZoneX;

import java.io.File;
import java.util.Random;

import com.skymiracle.gameUnion.models.Album;
import com.skymiracle.gameUnion.models.FightHistory;
import com.skymiracle.gameUnion.models.FreshMsg;
import com.skymiracle.gameUnion.models.MatchType;
import com.skymiracle.gameUnion.models.Photo;
import com.skymiracle.gameUnion.models.Replay;
import com.skymiracle.gameUnion.models.ReplayHistory;
import com.skymiracle.gameUnion.models.Reputation;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.UserFriend;
import com.skymiracle.gameUnion.models.bisai.Match;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.team.TeamMember;
import com.skymiracle.gameUnion.models.user.UserBattleFriend;
import com.skymiracle.gameUnion.models.user.UserCaller;
import com.skymiracle.gameUnion.models.user.UserEgHistory;
import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;

@Sessioned
public class U_Ctr_User extends U_Ctr {

	public void vi_aide(ActResult r) throws AppException, Exception {
	}

	public void vi_certificate(ActResult r) throws AppException, Exception {
		
		r.putMap("matchtypes", MatchTypeX.getItemsMap().values());

	}

	public void vi_team(ActResult r) throws AppException, Exception {
	}

	public void vi_friends(ActResult r) throws AppException, Exception {
	}

	public void vi_diary(ActResult r) throws AppException, Exception {
	}

	public void vi_album(ActResult r) throws AppException, Exception {
	}
	
	@NoSessioned
	public void vi_userHallInfo(ActResult r) throws AppException, Exception {
		String username = $("username");
		User u = new User(username).load();
		r.putMap("user", u);
	}

	public void vi_header(ActResult r) throws AppException, Exception {
	}

	public void vi_footer(ActResult r) throws AppException, Exception {
	}

	public void vi_index(ActResult r) throws AppException, Exception {
		if (actor != null) {
			Album album = new Album("1", actorId);
			if (!album.exists()) {
				album.setTitle("头像相册");
				album.setType(Album.TYPE.Avatar);
				album.create();
			}
		}

		String username=actor.getUsername();
		MList<UserCaller> ucs=UserCallerX.getLatestCaller6(username);
		MList<UserBattleFriend> ubfs=UserBattleFriendX.getTop6BattleFriend(username);
		r.putMap("visitorcount", UserCallerX.count("username",username));
		r.putMap("battlefriendcount", UserBattleFriendX.count("srcname",username));
		
		r.putMap("visitors", ucs);
		r.putMap("battlefriends", ubfs);
		
		PagedList<FreshMsg> freshmsgs = FreshMsgX.findPaged($i("pageNum", 1), $i("perPage", 30),
				"receiver,createdatetime-",username,null);
		r.putMap("freshmsgs", freshmsgs);
		
		r.putMap("friendscount", UserFriendX.count("username,state",
				actorId, UserFriend.STATE.NOGO));
		
		r.putMap("msgcount", UserMsgX.count("receiver,readed", actorId,
				false));
		
		//留言板消息
		r.putMap("MessageBoardCount", MessageBoardX.count("owner,readed",actorId,false));
		
		//留言板消息
		long warzonecount=UserX.count("warZoneId",actor.getWarZoneId());
		int k=1;
		int page=warzonecount>6?Long.valueOf(warzonecount/6).intValue():1;
		Random random = new Random();
		 k= random.nextInt(page);
		if(k==0)
			k=1;
		r.putMap("knowuser", UserX.findPaged(k, 6, "warZoneId", actor.getWarZoneId()));
		
		
	}

	public void vi_authImg(ActResult r) throws AppException, Exception {
		String code = "" + (new Random().nextInt(8999) + 1000);
		putAuthImageCode(r, "imgCode", code);
	}

	public void do_user_login(ActResult actResult) throws AppException,
			Exception {
		User user = $M(User.class);
		if (!user.auth("password", $("password")))
			throw new AppException("用户名或者密码错误, 请重新输入!");
		this.actorLogin(user.getUsername());
	}

	/*
	 * 远程调用接口 用户验证
	 */
	public void do_portalRemote_login(ActResult r) throws AppException,
			Exception {
		User user = $M(User.class);
		try {
			if (!user.auth("password", $("password")))
				getResponse().getWriter().print("0");
			else
				getResponse().getWriter().print("1");
		} catch (Exception e) {
			getResponse().getWriter().print("0");
		}
		r.setXmlText("<?xml version='1.0' encoding='UTF-8'?><data>1</data>");
	}

	public void do_user_logout(ActResult r) throws AppException, Exception {
		this.actorLogout(r);
		r.setRedirectTo("index.jsplayout.vi");
	}

	/*
	 * 用户基本信息
	 */
	@Sessioned
	public void vi_userinfo_mod(ActResult r) throws AppException, Exception {

	}

	@Sessioned
	public void do_userinfo_mod(ActResult r) throws AppException, Exception {
		checkAuthImageCode("imgCode");
		actor.update($MM(User.class));
	}

	/*
	 * 修改密码
	 */
	@Sessioned
	public void vi_userpassword_mod(ActResult r) throws AppException, Exception {

	}

	@Sessioned
	public void do_userpassword_mod(ActResult r) throws AppException, Exception {
		checkAuthImageCode("imgCode");
		if (!$("oldpassword").equals(actor.getPassword())) {
			throw new AppException("原密码输入错误, 请重新输入!");
		} else {
			actor.update($MM(User.class));
		}

	}

	/*
	 * 修改密码问题
	 */
	@Sessioned
	public void vi_userquestion_mod(ActResult r) throws AppException, Exception {

	}

	@Sessioned
	public void do_userquestion_mod(ActResult r) throws AppException, Exception {

		checkAuthImageCode("imgCode");
		if (!$("oldanswer").equals(actor.getAnswer())) {
			throw new AppException("原密码问题回答输入错误, 请重新输入!");
		} else {
			actor.update($MM(User.class));
		}

	}

	/*
	 * 密码找回
	 */
	public void vi_finduser(ActResult r) throws AppException, Exception {
	}

	public void do_user_find(ActResult r) throws AppException, Exception {
		checkAuthImageCode("imgCode");
		User user = $M(User.class);
		if (!user.exists())
			throw new AppException("用户名不存在！");
		if (!user.auth("question", $("question")))
			throw new AppException("密码问题错误, 请重新输入!");
		if (!user.auth("answer", $("answer")))
			throw new AppException("密码答案错误, 请重新输入!");
		user.update($MM(User.class));
		this.actorLogin(user.getUsername());
	}

	public void vi_leftbar(ActResult r) throws AppException, Exception {
	}

	public void vi_team_create(ActResult r) throws AppException, Exception {
	}

	public void vi_login(ActResult r) throws AppException, Exception {
	}

	public void vi_user_message(ActResult r) throws AppException, Exception {
	}

	public void vi_user_option(ActResult r) throws AppException, Exception {
	}

	// eg2
	// 补充用户信息
	public void vi_userInfo_add(ActResult r) throws AppException, Exception {
		r.putMap("warzones", WarZoneX.getItemsMap().values());
	}

	public void do_userInfor_add(ActResult r) throws AppException, Exception {
		actor
				.addCertificateInfo($("profession"), $("birthday"),
						$("warzoneid"));

		// 添加历史
		UserEgHistoryX.addRegUserHistory(actor);

	}

	public void vi_user(ActResult r) throws AppException, Exception {
		User user = new User($("username")).load();
		r.putMap("user", user);
	}

	// 用户证书
	public void vi_user_certificate(ActResult r) throws AppException, Exception {
		vi_user(r);
		r.putMap("matchtypes", MatchTypeX.getItemsMap().values());
	}

	// 查看用户证书
	public void vi_user_project_certificate(ActResult r) throws AppException,
			Exception {
		User user = new User($("username")).load();
		r.putMap("user", user);
		r.putMap("userProInfo", user.getUserProInfo(new MatchType(
				$("matchtypeid"))));
	}

	// 证书首页
	public void vi_certi_index(ActResult r) throws AppException, Exception {
		vi_user_project_certificate(r);
	}

	// 参赛历史
	public void vi_match_his(ActResult r) throws AppException, Exception {
		vi_user(r);
		PagedList<UserEgHistory> historys = UserEgHistoryX.findPaged($i(
				"pageNum", 1), $i("perPage", 5), "username",
				new Object[] { $("username") });
		historys.setLinkPrefix("../user/match_his.jsp.vi");
		r.putMap("historys", historys);
		r.putMap("pageBar", historys.getPageBarHTML());
	}

	// 参赛录像
	public void vi_match_replayhis(ActResult r) throws AppException, Exception {
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 10);
		String username = $("username");
		String matchtype = $("matchtypeid");
		PagedList<ReplayHistory> rhs = ReplayHistoryX.findPaged(pageNum,
				perPage, "userorteam,matchtype,createDateTime-", username,
				matchtype);
		rhs.setLinkPrefix("?username=" + username + "&matchtypeid="
						+ matchtype);
		r.putMap("replayhistorys", rhs);
		r.putMap("pageBar", rhs.getPageBarHTML());
	}

	public void do_downloadReplay(ActResult r) throws AppException, Exception {
		Replay replay = new Replay($("id")).load();
		r.putFile(new File(replay.getPath()), replay.getName());
		ReplayHistory hisrep = new ReplayHistory($("hisid")).load();
		hisrep.update("downtimes", hisrep.getDowntimes() + 1);
		// r.setRedirectTo("replays.jsplayout.vi");
	}

	// 用户空间
	public void vi_user_space(ActResult r) throws AppException, Exception {
		MList<UserBattleFriend> ubfs = UserBattleFriendX.find(
				"srcname,createdatetime-:0,6", $("username"));
		r.putMap("battlefriends", ubfs);
	}

	public void vi_coll(ActResult r) throws AppException, Exception {
		if ($("username").equals(actorId)) {
			MList<UserFriend> friends = UserFriendX.find("username,state",
					actorId, UserFriend.STATE.NOGO);
			r.putMap("friendscount", friends.size());

			MList<UserMsg> messages = UserMsgX.find("receiver,readed", actorId,
					false);
			r.putMap("msgcount", messages.size());
		} else {
			r.putMap("friendscount", 0);
			r.putMap("msgcount", 0);
		}

	}

	// 用户信誉
	public void vi_user_reputation(ActResult r) throws AppException, Exception {
		User user = new User($("username")).load();
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 4);
		PagedList<Reputation> reps = ReputationX.findPaged(pageNum, perPage,
				"username,recalled-", user.getUsername());
		long good = ReputationX.findGoodByUser(user.getUsername());
		long medium = ReputationX.findMediumByUser(user.getUsername());
		long bad = ReputationX.findBadByUser(user.getUsername());

		reps.setLinkPrefix("?username=" + user.getUsername());
		r.putMap("reputations", reps);
		r.putMap("pageBar", reps.getPageBarHTML());
		r.putMap("user", user);
		r.putMap("good", good);
		r.putMap("medium", medium);
		r.putMap("bad", bad);
	}

	public void do_dobackrep(ActResult r) throws AppException, Exception {
		Reputation rep = new Reputation($("id")).load();
		rep.update("recalled", true);
		if ("refrep" != null) {
			Reputation refrep = new Reputation();
			refrep.setBisaiid(rep.getBisaiid());
			refrep.setComment($("refrepcomm"));
			refrep.setCurrentTimeMill(System.currentTimeMillis());
			refrep.setFromuser(actor.getUsername());
			refrep.setMatchid(rep.getMatchid());
			refrep.setNeedRecall(false);
			refrep.setRecalled(true);
			refrep.setTypeByString($("refrep"));
			refrep.setUsername($("referee"));
			refrep.create();
			User u = new User($("referee")).load();
			u.update("reputation", u.getReputation()
					+ Integer.parseInt($("refrep")));
		}
		// 先给对手队伍加分，再给对手参赛队员加分
		if ("otherrep" != null) {
			Reputation refrep = new Reputation();
			refrep.setBisaiid(rep.getBisaiid());
			refrep.setComment($("otherrepcomm"));
			refrep.setCurrentTimeMill(System.currentTimeMillis());
			refrep.setFromuser(actor.getUsername());
			refrep.setMatchid(rep.getMatchid());
			refrep.setNeedRecall(false);
			refrep.setRecalled(true);
			refrep.setTypeByString($("otherrep"));
			refrep.setUsername($("other"));
			refrep.create();
			User u = new User($("other")).load();
			u.update("reputation", u.getReputation()
					+ Integer.parseInt($("otherrep")));
		}
	}

	public void vi_user_backrep(ActResult actResult) throws AppException,
			Exception {
		Reputation rep = new Reputation($("id")).load();
		Match match = new Match(rep.getMatchid()).load();
		User referee = new User(match.getReferee()).load();
		User other = null;
		if (match.getTeamId1().equals(rep.getUsername())) {
			if (!"".equals(match.getTeamId2())) {
				other = new User(match.getTeamId2()).load();
			}
		} else {
			if (!"".equals(match.getTeamId1())) {
				other = new User(match.getTeamId1()).load();
			}
		}
		actResult.putMap("reputation", rep);
		actResult.putMap("match", match);
		actResult.putMap("referee", referee);
		actResult.putMap("other", other);

	}

	public void vi_install(ActResult r) throws AppException, Exception {

	}

	public void vi_user_caller(ActResult r) throws AppException, Exception {
		User user = new User($("username")).load();
		r.putMap("callers", UserCallerX.findPaged(1, 6, "username,time-", user
				.getUsername(), null));
	}

	public void vi_userInfo(ActResult r) throws AppException, Exception {
		try {
			r.putMap("user", $M(User.class).load());
		} catch (Exception e) {
			throw new AppException("该玩家已经不存在!");
		}
	}

	// 修改头像
	public void vi_user_avator(ActResult r) throws AppException, Exception {
		r.putMap("album", new Album("1", actorId).load());
	}

	@Sessioned
	public void vi_user_avator_edit(ActResult r) throws AppException, Exception {
		Photo photo=null;
		MList<Photo> photos=PhotoX.find("username,albumid,isCover",actorId,"1", true);
		if (photos.size()==1) {
			photo=photos.get(0);
			r.putMap("photo",photo);
		}else{
			r.setRedirectTo("../user/user_avator.jsplayout.vi");
		}
	}
	
	public void vi_photo(ActResult r) throws  AppException,  Exception {
		Photo photo=new Photo($("id"),actorId).load();
	
		r.setImage(new SkyImageImpl(photo.getPath(), photo.getImageFormat())
			.getImage());
		
	}
	
	public void vi_user_battlefriend(ActResult actResult) throws AppException,
			Exception {
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 12);
		PagedList<UserBattleFriend> ubfs = UserBattleFriendX.findPaged(pageNum,
				perPage, "srcname,createDate-", $("username"));

		ubfs.setLinkPrefix("?username=" + $("username"));
		actResult.putMap("battlefriends", ubfs);
		actResult.putMap("pageBar", ubfs.getPageBarHTML());
	}
	
	public void vi_allvisitors(ActResult actResult) throws  AppException,  Exception {
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 12);
		PagedList<UserCaller> visitors=UserCallerX.getVisitorsByUsername(pageNum,perPage,actorId);
		long visitorCount=UserCallerX.getVisitorCountByUsername(actorId);
		visitors.setLinkPrefix("");
		actResult.putMap("visitors",visitors);
		actResult.putMap("visitorCount",visitorCount);
		actResult.putMap("pageBar",visitors.getPageBarHTML());
	}
	
	public void vi_allbattlefriends(ActResult actResult) throws  AppException,  Exception {
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 12);
		PagedList<UserBattleFriend> bfs=UserBattleFriendX.getBattleFriendsByUsername(pageNum,perPage,actorId);
		long bfCount=UserBattleFriendX.getBattleFriendsCountByUsername(actorId);
		bfs.setLinkPrefix("");
		actResult.putMap("bfs",bfs);
		actResult.putMap("bfCount",bfCount);
		actResult.putMap("pageBar",bfs.getPageBarHTML());
	}
	
	
	public void vi_myteam(ActResult r) throws  AppException,  Exception {
		GameTeam team = null;
		
		TeamMember member = null;
		//通过传参取得战队信息
		String teamId = $("teamid");
		if (teamId != null && !teamId.equals("")) {
			try {
				team = new GameTeam(teamId).load();
				member = team.getTeamMember(actorId+"");
				r.putMap("team", team);
				r.putMap("me", member);
				return;
			} catch (Exception e) {
				throw e;
			}
		}

		//通过session取得战队信息
		if (actor != null) {
			team = actor.getTeam();
			if (team.getId() == null) {
				team = null;
				return;
			}
			member = team.getTeamMember(actorId);
			r.putMap("team", team);
			r.putMap("me", member);
			return;
		}
	}
	
	/**
	 * 电竞记录
	 * 
	 */
	@Sessioned
	public void vi_userRecords(ActResult r) throws  AppException,  Exception {
		PagedList<FightHistory> fightHistorys = FightHistoryX.findPaged($i("pageNum", 1), $i("perPage", 10), "userorteam,cerateDateTime-", actor.getUsername(),null);
		fightHistorys.setLinkPrefix("../user/userRecords.jsplayout.vi");
		r.putMap("pageBar", fightHistorys.getPageBarHTML());
		r.putMap("fightHistorys", fightHistorys);
	}
	
	
}