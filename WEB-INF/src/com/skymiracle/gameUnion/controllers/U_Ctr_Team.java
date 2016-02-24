package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import java.util.UUID;

import com.skymiracle.gameUnion.models.Attachment;
import com.skymiracle.gameUnion.models.MatchType;
import com.skymiracle.gameUnion.models.Replay;
import com.skymiracle.gameUnion.models.ReplayHistory;
import com.skymiracle.gameUnion.models.Reputation;
import com.skymiracle.gameUnion.models.Teampoint;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.bisai.Match;
import com.skymiracle.gameUnion.models.bisai.SignUpTeamMember;
import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.team.TeamBbs;
import com.skymiracle.gameUnion.models.team.TeamMember;
import com.skymiracle.gameUnion.models.team.TeamRole;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;

@NoSessioned
public class U_Ctr_Team extends U_Ctr {

	protected GameTeam team = null;
	
	protected TeamMember member = null;

	public void invokeBefore(ActResult r) throws AppException, Exception {
		
		// 通过传参取得战队信息
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

		// 通过session取得战队信息
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

	public void vi_portlet(ActResult r) throws AppException, Exception {
	}

	public void vi_index(ActResult r) throws AppException, Exception {
			
	}

	public void vi_head(ActResult actResult) throws AppException, Exception {
	}

	public void vi_userInfo(ActResult r) throws AppException, Exception {
		try {
			r.putMap("user", $M(User.class).load());
		} catch (Exception e) {
			throw new AppException("该玩家已经不存在!");
		}
	}

	public void vi_creating(ActResult r) throws AppException, Exception {
		r.putMap("warzones", WarZoneX.getItemsMap().values());
	}

	@Sessioned
	public void do_creating(ActResult r) throws AppException, Exception {
		GameTeam team = $M(GameTeam.class);
		team.setCreator(actorId);
		team.create();

		// 创建历史
		UserEgHistoryX.addRegTeamHistory(actor, team.load());

		// List<TempUpFile> files = this.$$TFile();
		// if (files.size() == 0)
		// return;
		// team.saveTeamLogo(new File(files.get(0).getTmpUpPath()));
	}

	public void do_team_mod(ActResult r) throws AppException, Exception {
		$M(GameTeam.class).mod();
	}

	public void do_uploadTeamLogo(ActResult r) throws AppException, Exception {
		GameTeam team = $M(GameTeam.class);
		if ($("logopath") == null)
			team.saveTeamLogo($File());
	}

	public void vi_team(ActResult r) throws AppException, Exception {
	}

	public void vi_teams(ActResult r) throws AppException, Exception {
		String filter = null;
		String teamName = $("teamname");
		if (!"".equals(teamName) && teamName!=null) {
			filter=" teamName LIKE '%" + teamName + "%'";
		}
		MdoMap mdoMap = new MdoMap();
		PagedList<GameTeam> teams = GameTeamX.findPaged(mdoMap, filter, "memberCount", false, $i("pageNum",1), $i("perPage", 10));
		teams.setLinkPrefix("../team/teams.jsplayout.vi");
		r.putMap("teamNum",GameTeamX.count());
		r.putMap("teamname", teamName);
		r.putMap("teams", teams);
		r.putMap("pageBar", teams.getPageBarHTML());
	}

	public void vi_all(ActResult r) throws AppException, Exception {
		String filter = null;
		String teamName = $("teamname");
		if (!"".equals(teamName) && teamName!=null) {
			filter=" teamName LIKE '%" + teamName + "%'";
		}
		MdoMap mdoMap = new MdoMap();
		PagedList<GameTeam> teams = GameTeamX.findPaged(mdoMap, filter, "memberCount", false, $i("pageNum",1), $i("perPage", 10));
		teams.setLinkPrefix("../team/all.jsplayout.vi");
		r.putMap("teamname", teamName);
		r.putMap("teams", teams);
		r.putMap("teamNum", GameTeamX.count());
		r.putMap("pageBar", teams.getPageBarHTML());
	}
	
	public void vi_team_mod(ActResult r) throws AppException, Exception {
	}

	public void vi_teamIntro(ActResult r) throws AppException, Exception {
		GameTeam gt = actor.getTeam();
		r.putMap("team", gt);
		r.putMap("teamMember", gt.getTeamMember(actorId));
	}

	@Sessioned
	public void vi_teamMembers(ActResult r) throws AppException, Exception {
		if (team == null)
			return;

		String roleId = $("roleid");
		String username = $("username");
		StringBuffer filter = new StringBuffer(" teamid='" + team.getId()
				+ "' and passed = 1 ");
		if (roleId != null && !roleId.equals("")) {
			filter.append(" and roleid = '" + roleId + "' ");
			r.putMap("roleid", roleId);
		}
		if (username != null && !username.equals("")) {
			filter.append(" and username = '" + username + "' ");
			r.putMap("username", username);
		}

		PagedList<TeamMember> teamMembers = TeamMemberX.findPaged(new MdoMap(),
				filter.toString(), "roleid", true, $i("pageNum", 1), $i(
						"perPage", 15));
		teamMembers.setLinkPrefix("../team/teamMembers.jsp.vi");
		r.putMap("team", team);
		r.putMap("teammembers", teamMembers);
		r.putMap("pageBar", teamMembers.getPageBarHTML());
	}

	// 待审批的成员
	public void vi_noApprovalMembers(ActResult r) throws AppException,
			Exception {
	}

	public void vi_team_upload(ActResult r) throws AppException, Exception {
	}

	public void do_team_upload(ActResult r) throws AppException, Exception {
		new GameTeam($("id")).saveTeamLogo($File());
	}

	public void vi_team_logo_edit(ActResult r) throws AppException, Exception {
	}

	public void do_team_logo_edit(ActResult r) throws AppException, Exception {
		new GameTeam($("id")).cutAvator($i("persent"), $i("x"), $i("y"),
				$i("w"), $i("h"));
	}

	// ////////////////////

	public void vi_team_disband(ActResult r) throws AppException, Exception {
		vi_team(r);
	}

	// 解散战队
	public void do_team_disband(ActResult r) throws AppException, Exception {
		new GameTeam($("id")).disband($("reason"));
	}

	// 战队申请
	public void do_teamMember_apply(ActResult r) throws AppException, Exception {
		new TeamMember($("teamid"), actorId).userApply();
	}

	// 批准加入
	@Sessioned
	public void do_teamMember_pass(ActResult r) throws AppException, Exception {
		new TeamMember($("teamid"), $("username")).passApply(actor);
	}

	// 拒绝申请
	public void do_teamMember_refuse(ActResult r) throws AppException,
			Exception {
		new TeamMember($("teamid"), $("username")).refuseApply(actor);
	}

	// 取消申请
	public void do_teamMember_apply_cancel(ActResult r) throws AppException,
			Exception {
		new TeamMember($("teamid"), actorId).userCancelApply();
	}

	// 离开战队
	public void do_teamMember_cancelTeam(ActResult r) throws AppException,
			Exception {
		new TeamMember($("teamid"), actorId).userQuit();
	}

	// 删除成员
	public void do_teamMember_del(ActResult r) throws AppException, Exception {
		if (team == null)
			return;

		team.deleteMember($("username"));
	}

	/* ********* 战队等级 ********* */
	public void vi_teamRoles(ActResult r) throws AppException, Exception {
		r.putMap("authoritys", AuthorityX.getItemsMap().values());
	}

	public void do_teamRole_add(ActResult r) throws AppException, Exception {
		$M(TeamRole.class).create();
	}

	public void do_teamRole_del(ActResult r) throws AppException, Exception {
		TeamRole role = $M(TeamRole.class);
		// 所有该职位的成员修改为新人
		TeamMemberX.setNewer(role);
		// delete role
		role.delete();
	}

	/* ********** ********* */
	public void vi_member_mod(ActResult r) throws AppException, Exception {
		r.putMap("member", team.getTeamMember($("username")));
	}

	public void do_member_mod(ActResult r) throws AppException, Exception {
		$M(TeamMember.class).update($MM(TeamMember.class));
	}

	public void vi_role_mod(ActResult r) throws AppException, Exception {
		r.putMap("role", new TeamRole($("teamid"), $("roleid")).load());
		r.putMap("authoritys", AuthorityX.getItemsMap().values());
	}

	public void do_teamRolePower_mod(ActResult r) throws AppException,
			Exception {
		TeamRole role = new TeamRole($("teamid"), $("roleid"));
		role.editPower($$("powerid"));
	}

	public void vi_msg_member_send(ActResult r) throws AppException, Exception {
		vi_team(r);
		r.putMap("user", new User($("username")).load());
	}

	public void do_msg_member_send(ActResult r) throws AppException, Exception {
		actor.sendMemberMsg($M(TeamMember.class), $M(TeamMsg.class));
	}

	public void vi_msg_group_send(ActResult r) throws AppException, Exception {
		vi_team(r);
	}

	public void do_msg_group_send(ActResult r) throws AppException, Exception {
		actor.sendGroupMsg(new TeamRole($("teamid"), $("roleid")),
				$M(TeamMsg.class));
	}

	// 战队申请
	public void vi_team_apply(ActResult r) throws AppException, Exception {
		GameTeam team = new GameTeam($("id")).load();
		PagedList<TeamMember> teamMembers = TeamMemberX.findPaged($i("pageNum",
				1), $i("perPage", 5), "teamId,passed, roleid+", team.getId(),
				true, null);
		teamMembers.setLinkPrefix("../team/team_apply.jsplayout.vi");
		r.putMap("team", team);
		r.putMap("teammembers", teamMembers);
		r.putMap("pageBar", teamMembers.getPageBarHTML());
	}

	public void do_teamType_mod(ActResult r) throws AppException, Exception {
		new GameTeam($("teamId")).modType($("descType"));
	}

	// 替换队长
	public void vi_leader_transfer(ActResult r) throws AppException, Exception {
		vi_team(r);
	}

	public void do_leader_transfer(ActResult r) throws AppException, Exception {
		new GameTeam($("teamid"))
				.transferLeader(actor, new User($("username")));
	}

	public void do_memberExists_check(ActResult r) throws AppException,
			Exception {
		// 此判断放在这里似乎不妥
		if (actorId.equals($("username")))
			throw new AppException("您不能把自己替换为新队长");

		TeamMember member = new TeamMember($("teamid"), $("username"));
		r.putMap("exists", member.exists() && member.load().getPassed());
	}

	// search
	public void do_team_checkexists(ActResult r) throws AppException, Exception {
		String teamname = $("teamname");
		if (teamname == null || teamname.trim().equals(""))
			throw new AppException("请输入战队名称！");

		StringBuffer filter = new StringBuffer(" teamName='" + teamname.trim()
				+ "' ");
		MList<GameTeam> teams = GameTeamX.find(new MdoMap(), filter.toString());

		if (teams.size() == 0)
			throw new AppException("没有" + teamname + "战队");

		r.putMap("teamid", teams.getFirst().getId());
	}

	// 删除战队
	public void do_team_del(ActResult r) throws AppException, Exception {
		new GameTeam($("id")).delete();
	}

	// eg2

	public void vi_todayMsg(ActResult r) throws AppException, Exception {
	}

	public void vi_coll(ActResult r) throws AppException, Exception {
		if(team == null)
			return;
		
		r.putMap("applysCount", TeamMsgX.getApplys2TeamCount(team));
	}

	public void vi_team_certificate(ActResult r) throws AppException, Exception {
		r.putMap("matchtypes", MatchTypeX.getItemsMap().values());
	}

	public void vi_team_space(ActResult r) throws AppException, Exception {
	}

	public void vi_team_project_certificate(ActResult r) throws AppException,
			Exception {
		if (team == null)
			return;
		long unRecallRep = ReputationX.count("username,needrecall,recalled",
				team.getId(), true, false);
		r.putMap("needRecall", unRecallRep);
		r.putMap("teamProInfo", team.getTeamProInfo(new MatchType(
				$("matchtypeid"))));
	}

	public void vi_certi_index(ActResult r) throws AppException, Exception {
		vi_team_project_certificate(r);
	}

	public void vi_match_his(ActResult r) throws AppException, Exception {
		// vi_team_project_certificate(r);
	}

	public void vi_match_vadio(ActResult r) throws AppException, Exception {
		// vi_team_project_certificate(r);
	}

	public void vi_team_reputation(ActResult r) throws AppException, Exception {
		String teamid = $("teamid");
		GameTeam gt = new GameTeam(teamid).load();
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 4);
		PagedList<Reputation> reps = ReputationX.findPaged(pageNum, perPage,
				"username,recalled-,createdatetime-", teamid);
		long good = ReputationX.findGoodByUser(teamid);
		long medium = ReputationX.findMediumByUser(teamid);
		long bad = ReputationX.findBadByUser(teamid);
		;
		reps.setLinkPrefix("?teamid=" + teamid);
		r.putMap("reputations", reps);
		r.putMap("pageBar", reps.getPageBarHTML());
		r.putMap("team", gt);
		r.putMap("good", good);
		r.putMap("medium", medium);
		r.putMap("bad", bad);
		r.putMap("leader", gt.getCreator());
	}

	public void vi_team_backrep(ActResult actResult) throws AppException,
			Exception {
		Reputation rep = new Reputation($("id")).load();
		Match match = new Match(rep.getMatchid()).load();
		User referee = new User(match.getReferee()).load();
		GameTeam other = null;
		if (match.getTeamId1().equals(rep.getUsername())) {
			if (!"".equals(match.getTeamId2())) {
				other = new GameTeam(match.getTeamId2()).load();
			}
		} else {
			if (!"".equals(match.getTeamId1())) {
				other = new GameTeam(match.getTeamId1()).load();
			}
		}
		actResult.putMap("reputation", rep);
		actResult.putMap("match", match);
		actResult.putMap("referee", referee);
		actResult.putMap("other", other);
	}

	// 
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
			GameTeam gt = new GameTeam($("other")).load();
			gt.update("reputation", gt.getReputation()
					+ Integer.parseInt($("otherrep")));

			MList<SignUpTeamMember> members = SignUpTeamMemberX
					.findMyMemberByBisai(rep.getBisaiid(), gt.getId());
			for (SignUpTeamMember member : members) {
				Reputation urep = new Reputation();
				urep.setBisaiid(rep.getBisaiid());
				urep.setComment($("otherrepcomm"));
				urep.setCurrentTimeMill(System.currentTimeMillis());
				urep.setFromuser(actor.getUsername());
				urep.setMatchid(rep.getMatchid());
				urep.setNeedRecall(false);
				urep.setRecalled(true);
				urep.setTypeByString($("otherrep"));
				urep.setUsername(member.getUsername());
				urep.create();
				User u = new User(member.getUsername());
				u.update("reputation", u.getReputation()
						+ Integer.parseInt($("otherrep")));
			}
		}
	}

	public void vi_chat(ActResult r) throws AppException, Exception {
	}

	public void vi_album(ActResult r) throws AppException, Exception {
	}

	public void vi_credit(ActResult r) throws AppException, Exception {
	}

	public void vi_bbses(ActResult r) throws AppException, Exception {
		if (team == null)
			return;

		PagedList<TeamBbs> bbses = TeamBbsX.findPaged($i("pageNum", 1), $i(
				"perPage", 15), "teamId, modifyDateTime-, createDateTime-", team
				.getId(), null, null);
		bbses.setLinkPrefix("../team/bbses.jsp.vi");
		r.putMap("bbses", bbses);
		r.putMap("pageBar", bbses.getPageBarHTML());
	}

	public void vi_bbs_addOrMod(ActResult r) throws AppException, Exception {
		TeamBbs bbs = new TeamBbs();
		if ("mod".equals($("act")))
			bbs = $M(TeamBbs.class).load();
		r.putMap("bbs", bbs);
	}

	public void do_bbs_addOrMod(ActResult r) throws AppException, Exception {
		TeamBbs bbs = $M(TeamBbs.class);
		bbs.setAuthor(actorId);
		bbs.createOrUpdate();
	}

	public void do_bbs_attach_add(ActResult r) throws AppException, Exception {
		r.putMap("attach", new Attachment().create($File()));
	}

	public void vi_bbs_attach(ActResult r) throws AppException, Exception {
		r.setImage($M(Attachment.class).loadImg());
	}

	public void vi_bbs(ActResult r) throws AppException, Exception {
		TeamBbs bbs = $M(TeamBbs.class).load();
		r.putMap("bbs", bbs);

		PagedList<TeamBbs> replys = TeamBbsX.findPaged($i("pageNum", 1), $i(
				"perPage", 5), "rootId, createDateTime+", bbs.getUuid(), null);
		replys.setLinkPrefix("../team/bbs.jsp.vi");
		r.putMap("bbs_replys", replys);
		r.putMap("pageBar", replys.getPageBarHTML());

	}

	public void do_bbs_reply_add(ActResult r) throws AppException, Exception {
		TeamBbs bbs = $M(TeamBbs.class);
		bbs.setUuid(UUID.randomUUID().toString());
		bbs.setAuthor(actorId);
		bbs.create();
	}

	public void do_bbs_del(ActResult r) throws AppException, Exception {
		$M(TeamBbs.class).delete();
	}

	public void vi_members(ActResult r) throws AppException, Exception {
		if (team == null)
			return;

		String roleId = $("roleid");
		String username = $("username");
		StringBuffer filter = new StringBuffer(" teamid='" + team.getId()
				+ "' and passed = 1 ");
		if (roleId != null && !roleId.equals("")) {
			filter.append(" and roleid = '" + roleId + "' ");
			r.putMap("roleid", roleId);
		}
		if (username != null && !username.equals("")) {
			filter.append(" and username = '" + username + "' ");
			r.putMap("username", username);
		}

		PagedList<TeamMember> teamMembers = TeamMemberX.findPaged(new MdoMap(),
				filter.toString(), "roleid", true, $i("pageNum", 1), $i(
						"perPage", 15));
		teamMembers.setLinkPrefix("../team/members.jsp.vi");
		r.putMap("team", team);
		r.putMap("members", teamMembers);
		r.putMap("pageBar", teamMembers.getPageBarHTML());
	}

	public void vi_membersOfFirst_any(ActResult r) throws AppException,
			Exception {
	}

	public void vi_membersOfSecond_any(ActResult r) throws AppException,
			Exception {
	}

	public void vi_membersOfFirst_all(ActResult r) throws AppException,
			Exception {
		if (team == null)
			return;

		PagedList<TeamMember> teamMembers = TeamMemberX.findPaged($i("pageNum",
				1), $i("perPage", 15), "teamId, memberType", team.getId(),
				TeamMember.MemberType.first);
		teamMembers.setLinkPrefix("../team/membersOfFirst_all.jsp.vi?teamid="
				+ team.getId() + "&memberType="
				+ TeamMember.MemberType.first.toString());
		r.putMap("members", teamMembers);
		r.putMap("pageBar", teamMembers.getPageBarHTML());
	}

	public void vi_membersOfSecond_all(ActResult r) throws AppException,
			Exception {
		if (team == null)
			return;

		PagedList<TeamMember> teamMembers = TeamMemberX.findPaged($i("pageNum",
				1), $i("perPage", 15), "teamId, memberType", team.getId(),
				TeamMember.MemberType.second);
		teamMembers.setLinkPrefix("../team/membersOfFirst_all.jsp.vi?teamid="
				+ team.getId() + "&memberType="
				+ TeamMember.MemberType.second.toString());
		r.putMap("members", teamMembers);
		r.putMap("pageBar", teamMembers.getPageBarHTML());
	}

	// 战队补充信息
	public void vi_teamInfor_add(ActResult r) throws AppException, Exception {
		r.putMap("warzones", WarZoneX.getItemsMap().values());
	}

	public void do_teamInfor_add(ActResult r) throws AppException, Exception {
		actor.getTeam().update("areaId", $("areaId"));
	}
	
	// 参赛录像
	public void vi_match_replayhis(ActResult r) throws AppException, Exception {
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 10);
		String teamid=$("teamid");
		String matchtype=$("matchtypeid");
		PagedList<ReplayHistory> rhs=ReplayHistoryX.findPaged(pageNum, perPage,"userorteam,matchtype,createDateTime-",teamid,matchtype);
		rhs.setLinkPrefix("?teamid="+teamid+"&matchtypeid="+matchtype);
		r.putMap("replayhistorys",rhs);
		r.putMap("pageBar", rhs.getPageBarHTML());
	}
	public void do_downloadReplay(ActResult r) throws AppException, Exception {
		Replay replay = new Replay($("id")).load();
		r.putFile(new File(replay.getPath()), replay.getName());
		ReplayHistory hisrep=new ReplayHistory($("hisid")).load();
		hisrep.update("downtimes", hisrep.getDowntimes()+1);
		//r.setRedirectTo("replays.jsplayout.vi");
	}
	public void vi_eglevel(ActResult r) throws  AppException,  Exception {
	}
	public void vi_teamPoint(ActResult r) throws  AppException,  Exception {
		MList<Teampoint> teampoints = TeampointX.find("teamid", $("teamid"));
		r.putMap("teampoints", teampoints);
	}
	
	/**
	 * 战队成员
	 * 
	 */
	public void vi_tmembers(ActResult r) throws  AppException,  Exception {
		MList<TeamMember> members = TeamMemberX.find("teamid:0,6", $("teamid"));
		r.putMap("members", members);
	}
}
