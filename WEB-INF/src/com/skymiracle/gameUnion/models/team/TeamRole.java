package com.skymiracle.gameUnion.models.team;

import static com.skymiracle.gameUnion.Singletons.SequenceX;
import static com.skymiracle.gameUnion.Singletons.*;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.Authority;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;

@Title("战队中的角色（职位）")
public class TeamRole extends AbsMdo<TeamRole> {
	public static String ID_Leader = "0leader";

	public static String ID_Newer = "9newer";

	public TeamRole() {
		super(TeamRoleX);
	}

	public TeamRole(String teamId, String id) {
		this();
		this.teamId = teamId;
		this.id = id;
	}

	public static class X extends Mdo_X<TeamRole> {

		public void initTeamRoles(String teamId) throws AppException, Exception {
			// 队长
			{
				TeamRole role = new TeamRole();
				role.setTeamId(teamId);
				role.setId(ID_Leader);
				role.setName("队长");
				role.setLevel(0);
				role.create(true);

				TeamRolePowerX.add4TeamLeader(role);
			}

			// 新人
			{
				TeamRole role = new TeamRole();
				role.setTeamId(teamId);
				role.setId(ID_Newer);
				role.setName("新人");
				role.setLevel(100);
				role.create(true);

				TeamRolePowerX.add4Newer(role);
			}
		}

		public void deleteByTeamId(String teamId) throws AppException,
				Exception {
			MList<TeamRole> roles = find("teamId", teamId);
			for (TeamRole role : roles)
				role.delete();
		}

		public MList<TeamRole> findByTeam(GameTeam gameTeam)
				throws AppException, Exception {
			return this.find("teamid, createdatetime+", gameTeam.getId(), null);
		}

	}

	@Title("战队ID")
	@Length(32)
	private String teamId;

	@Title("ID")
	@Length(128)
	private String id;

	@Title("level")
	private int level;

	@Title("职位名称")
	private String name;

	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "teamid", "id" };
	}

	@Override
	public String table() {
		return "tb_teamrole";
	}

	@Override
	public TeamRole create() throws AppException, Exception {
		return this.create(false);
	}

	public TeamRole create(boolean isSys) throws AppException, Exception {
		if (!isSys)
			this.id = SequenceX.currentNum(this.table()) + "";
		return super.create();
	}

	@Override
	public void delete() throws AppException, Exception {
		// 删除该职位的所有权限
		deleteAllPower();

		super.delete();
	}

	private void deleteAllPower() throws AppException, Exception {
		getPowers().delete();
	}

	public boolean getHasPower(Authority au) throws AppException, Exception {
		// 任何人都给vi_member的权限
		if ("vi_member".equals(au.getCode()))
			return true;

		if (teamId == null || id == null || au == null || au.getCode() == null)
			return false;
		return new TeamRolePower(teamId, id, au.getCode()).exists();
	}

	public MList<TeamRolePower> getPowers() throws AppException, Exception {
		return TeamRolePowerX.findByRole(this);
	}

	public boolean getIsSys() {
		return ID_Leader.equals(id) || ID_Newer.equals(id);
	}

	public boolean getIsLeader() {
		return ID_Leader.equals(id);
	}

	public void editPower(String[] powers) throws AppException, Exception {
		// 删除所有
		deleteAllPower();
		// 重新保存
		for (String powerid : powers) {
			new TeamRolePower(teamId, id, powerid).create();
		}
	}

	public MList<TeamMember> getTeamMembers() throws AppException, Exception {
		return TeamMemberX.findByRole(this);
	}
}
