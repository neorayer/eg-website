package com.skymiracle.gameUnion.models.team;

import static com.skymiracle.gameUnion.Singletons.*;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.Authority;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

public class TeamRolePower extends AbsMdo<TeamRolePower> {

	public static class X extends Mdo_X<TeamRolePower> {

		public void add4TeamLeader(TeamRole role) throws AppException, Exception {
			for (Authority au : AuthorityX.getItemsMap().values())
				new TeamRolePower(role, au).create();
		}

		public void add4Newer(TeamRole role) throws AppException, Exception {
			Authority au = AuthorityX.getItemsMap().get("vi_member");
			new TeamRolePower(role, au).create();
		}

		public MList<TeamRolePower> findByRole(TeamRole role) throws AppException, Exception {
			return this.find("teamId, roleId", role.getTeamId(), role.getId());
		}

	}

	@Length(32)
	private String teamId;

	@Length(64)
	private String roleId;

	@Length(64)
	private String powerId;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public TeamRolePower() {
		super(TeamRolePowerX);
	}

	public TeamRolePower(String teamId, String roleId, String powerId) {
		this();
		this.teamId = teamId;
		this.roleId = roleId;
		this.powerId = powerId;
	}

	public TeamRolePower(TeamRole role, Authority au) {
		this();
		this.teamId = role.getTeamId();
		this.roleId = role.getId();
		this.powerId = au.getCode();
	}

	@Override
	public String[] keyNames() {
		return new String[] { "teamid", "roleid", "powerid" };
	}

	@Override
	public String table() {
		return "tb_teamrolepower";
	}
	
	
	public Authority getAuthority() {
		return AuthorityX.getItemsMap().get(powerId);
	}
}
