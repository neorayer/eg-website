package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.SignUpUserX;

import com.skymiracle.mdo5.Mdo_X;

public class SignUpUser extends UserInfo<SignUpUser> {

	public static class X extends Mdo_X<SignUpUser> {
	}

	@Title("比赛ID")
	private String bisaiId;

	@Title("战队名")
	private String teamname;

	@Title("状态")
	@Desc("0: 审批中 ｜ 1：通过 | 2: 拒绝")
	private String state = "0";

	public SignUpUser() {
		super(SignUpUserX);
	}

	public SignUpUser(String bisaiId, String username) {
		this();
		this.bisaiId = bisaiId;
		setUsername(username);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBisaiId() {
		return bisaiId;
	}

	public void setBisaiId(String bisaiId) {
		this.bisaiId = bisaiId;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "bisaiid", "username" };
	}

	@Override
	public String table() {
		return "tb_signupuser";
	}
}
