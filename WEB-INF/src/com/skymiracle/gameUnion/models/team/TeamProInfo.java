package com.skymiracle.gameUnion.models.team;

import static com.skymiracle.gameUnion.Singletons.*;

import java.util.UUID;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.MatchType;
import com.skymiracle.gameUnion.models.user.UserProInfo;
import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;

public class TeamProInfo extends AbsMdo<TeamProInfo> {

	public static class X extends Mdo_X<TeamProInfo> {

		public TeamProInfo createCertificate(GameTeam team, MatchType matchType) throws AppException, Exception {
			TeamProInfo proInfo =new TeamProInfo();
			proInfo.setTeamId(team.getId());
			proInfo.setMatchTypeId(matchType.getId());
			proInfo.setGrade("1");
			proInfo.setCreditVal("0");
			return proInfo.create();
		}

	}

	@Title("战队编号")
	private String teamId;

	@Title("比赛类型编号")
	private String matchTypeId;

	@Title("等级")
	private String grade;

	@Title("信誉值")
	private String creditVal;

	@Title("参赛证编号")
	private String certiId = UUID.randomUUID().toString();

	public TeamProInfo() {
		super(TeamProInfoX);
	}
	
	public TeamProInfo(String teamId, String matchTypeId) {
		this();
		this.teamId = teamId;
		this.matchTypeId = matchTypeId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getMatchTypeId() {
		return matchTypeId;
	}

	public void setMatchTypeId(String matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCreditVal() {
		return creditVal;
	}

	public void setCreditVal(String creditVal) {
		this.creditVal = creditVal;
	}

	public String getCertiId() {
		return certiId;
	}

	public void setCertiId(String certiId) {
		this.certiId = certiId;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "teamId", "matchTypeId" };
	}

	@Override
	public String table() {
		return "tb_teamPro_info";
	}
	
	public GameTeam getTeam() {
		GameTeam team = new GameTeam(teamId);
		try{
			return team.load();
		}catch(Exception e) {
			Logger.debug("没有战队", e);
			return team; 
		}
	}
	
	public MatchType getProject() {
		return new MatchType(matchTypeId).load();
	}

}
