package com.skymiracle.gameUnion.models.team;

import static com.skymiracle.gameUnion.Singletons.TeamMemberX;
import static com.skymiracle.gameUnion.Singletons.TeamMsgX;
import static com.skymiracle.gameUnion.Singletons.UserEgHistoryX;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.Authority;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.gameUnion.models.msg.TeamMsg.MsgType;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.mdo5.NullKeyException;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;

@Title("战队成员")
public class TeamMember extends AbsMdo<TeamMember> {

	public static class X extends Mdo_X<TeamMember> {
		public TeamMember getByUsername(String username) throws AppException,
				Exception {
			MList<TeamMember> tms = find("userName", username);
			if (tms.size() == 0)
				return new TeamMember();
			return tms.getFirst();
		}

		public boolean hasUser(User user) throws AppException, Exception {
			return this.count("username, passed", user.getUsername(), true) > 0;
		}

		public void deleteByTeamId(String teamId) throws AppException,
				Exception {
			MdoMap mm = new MdoMap();
			mm.put("teamId", teamId);
			delete(mm);
		}

		public void setNewer(TeamRole role) throws AppException, Exception {
			MList<TeamMember> members = find("teamid,roleid", role.getTeamId(),
					role.getId());
			for (TeamMember member : members) {
				member.toNewer();
			}
		}

		public MList<TeamMember> findByRole(TeamRole role) throws AppException,
				Exception {
			return super.find("teamId, roleId, passed", role.getTeamId(), role
					.getId(), true);
		}

		public MList<TeamMember> notPassed(GameTeam gameTeam)
				throws AppException, Exception {
			return super.find("teamid, passed, createDateTime+", gameTeam
					.getId(), false, null);
		}

		public MList<TeamMember> getFirstingByTeamId(String teamId, long count) throws AppException, Exception {
			String format = "teamId, memberType";
			if(count > 0)
				format += ":" + count;
			return this.find(format, teamId, MemberType.first);
		}
		
		public MList<TeamMember> getSecondingByTeamId(String teamId, long count) throws AppException, Exception {
			String format = "teamId, memberType";
			if(count > 0)
				format += ":" + count;
			return this.find(format, teamId, MemberType.second);
		}

		
	}

	@Title("战队ID")
	@Length(32)
	private String teamId;

	@Title("成员名称")
	private String username;

	@Title("队伍贡献")
	private int contribute = 0;

	@Title("状态")
	private boolean passed = false;

	@Title("职位")
	private String roleId = TeamRole.ID_Newer;

	public static enum MemberType {
		normal, first, second
	}

	@Title("种类")
	private MemberType memberType = MemberType.normal;

	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	public TeamMember() {
		super(TeamMemberX);
	}

	public void toNewer() throws AppException, Exception {
		update("roleId", TeamRole.ID_Newer);
	}

	public TeamMember(String teamId, String username) {
		this();
		this.teamId = teamId;
		this.username = username;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public int getContribute() {
		return contribute;
	}

	public void setContribute(int contribute) {
		this.contribute = contribute;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public boolean getPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "teamid", "username" };
	}

	@Override
	public String table() {
		return "tb_teamMember";
	}

	public GameTeam getTeam() throws AppException, Exception {
		if (teamId == null)
			return new GameTeam();
		try {
			return new GameTeam(teamId).load();
		} catch (NotExistException e) {
			return new GameTeam();
		}
	}

	public User getUser(){
		if (username == null)
			return new User();
		try {
			return new User(username).load();
		} catch (Exception e) {
			return new User();
		}
	}

	private boolean isAvaiable() throws AppException, Exception {
		if (getTeam().getId() == null)
			return false;

		if (getUser().getUsername() == null)
			return false;

		return true;
	}

	public void userQuit() throws AppException, Exception {
		if (!isAvaiable())
			return;

		// 不存在
		if (!exists())
			return;

		// team中删除Member
		this.delete();
		
		// 发消息给个人
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, username);
			msg.putContent(null, "您离开了" + getTeam().getTeamName() + "战队！您现在没有战队了!～～");
			msg.create();
		}
		
		// 发消息给战队 
		{
			TeamMsg msg = new TeamMsg(teamId, TeamMsg.MsgType.USER_QUIT, username, null);
			msg.putContent("{SRC}离开了战队！");
			msg.create();
		}
	}

	public void userApply() throws AppException, Exception {
		if (!isAvaiable())
			return;

		// 发消息给个人
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, username);
			msg.putContent(null, "您申请加入" + getTeam().getTeamName() + "战队！正等待队长审批");
			msg.create();
		}
		
		// 发消息给战队 
		{
			TeamMsg msg = new TeamMsg(teamId, MsgType.USER_APPLY, username, null);
			msg.putContent("{SRC}申请加入战队！");
			msg.create();
		}

		create();
	}

	public void userCancelApply() throws AppException, Exception {
		if (!isAvaiable())
			return;

		// 不存在
		if (!exists())
			return;

		// 已通过批准
		load();
		if (passed)
			return;

		this.delete();
	}

	public void passApply(User who) throws AppException, Exception {
		// 获得user的team
		GameTeam team = getTeam();
		if (team == null)
			return;

		User user = getUser();
		if (user == null)
			return;

		// 不存在
		if (!exists())
			return;

		// 已通过批准
		load();
		if (passed)
			return;

		// 修改标记
		this.update("passed", true);

		// team.count ++
		team.update("membercount", team.getMemberCount() + 1);

		 // 发消息给个人
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, username);
			msg.putContent(null, "您加入" + getTeam().getTeamName() + "的申请已经通过审批。");
			msg.create();
		}
		
		// 发消息给战队
		{
			TeamMsg msg = new TeamMsg(teamId, TeamMsg.MsgType.PASS_APPLY, who.getUsername(), username);
			msg.putContent("{SRC}批准了{DESC}加入战队的申请！");
			msg.create();
		}
		
		
		// 添加新增队员 职业生涯记录
		UserEgHistoryX.addRegTeamHistory(user, team);

	}

	public void refuseApply(User who) throws AppException, Exception {
		if (!isAvaiable())
			return;

		// 不存在
		if (!exists())
			return;

		
		 // 发消息给个人
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, username);
			msg.putContent(null, "很遗憾，您加入" + getTeam().getTeamName() + "的申请已经被拒绝了。");
			msg.create();
		}
		
		// 发消息给战队
		{
			TeamMsg msg = new TeamMsg(teamId, TeamMsg.MsgType.PASS_APPLY, who.getUsername(), username);
			msg.putContent("{SRC}拒绝了{DESC}加入战队的申请！");
			msg.create();
		}

		this.delete();
	}

	public boolean getIsLeader() {
		return TeamRole.ID_Leader.equals(this.roleId);
	}

	public TeamRole getRole() throws AppException, Exception {
		try {
			return new TeamRole(teamId, roleId).load();
		} catch (NullKeyException e) {
			return new TeamRole();
		}
	}

	// 是否有职位管理权限
	public boolean getHasMRole() throws AppException, Exception {
		Authority au = new Authority("manage_role");
		return getRole().getHasPower(au);
	}

	// 是否有成员管理权限
	public boolean getHasMMember() throws AppException, Exception {
		Authority au = new Authority("manage_member");
		return getRole().getHasPower(au);
	}

	// 是否有群发消息权限
	public boolean getHasGMsg() throws AppException, Exception {
		Authority au = new Authority("group_msg");
		return getRole().getHasPower(au);
	}

	// 是否有审批申请权限
	public boolean getHasAApply() throws AppException, Exception {
		Authority au = new Authority("approval_apply");
		return getRole().getHasPower(au);
	}

	public void setToLeader() throws AppException, Exception {
		MdoMap mm = new MdoMap();
		mm.put("roleId", TeamRole.ID_Leader);
		update(mm);
	}

	public void setToNewer() throws AppException, Exception {
		MdoMap mm = new MdoMap();
		mm.put("roleId", TeamRole.ID_Newer);
		update(mm);

	}
	
	public MList<TeamMsg> getMessagesTop10() throws AppException, Exception {
		return TeamMsgX.find("teamid, receiver", teamId, username);
	}
}
