package com.skymiracle.gameUnion.models.team;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.MatchType;
import com.skymiracle.gameUnion.models.Msg;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.WarZone;
import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.gameUnion.models.msg.TeamMsg.MsgType;
import com.skymiracle.image.SkyImage;
import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.exception.AppException;

public class GameTeam extends AbsMdo<GameTeam> {

	@Title("ID")
	private String id;

	@Title("战队名称")
	@Desc("2~8个汉字（16个字符）以内！")
	private String teamName;

	@Title("战队短名")
	private String shortName;

	@Title("战队队长")
	private String creator;

	@Title("直接域名")
	private String url;

	@Title("QQ群号")
	private String qq;

	@Title("战区ID")
	private String areaId;

	@Title("战队简介")
	private StringBuffer memo;

	@Title("战队积分")
	private int point;

	@Title("总人数")
	private int memberCount = 1;

	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	@Title("是否开通")
	private boolean opened = true;

	private int reputation;

	public static enum TeamType {
		S, A, B, C
	}

	@Title("战队等级")
	private TeamType teamType = TeamType.C;

	private int teamcontribute;

	public int getTeamcontribute() {
		return teamcontribute;
	}

	public void setTeamcontribute(int teamcontribute) {
		this.teamcontribute = teamcontribute;
	}

	public GameTeam() {
		super(GameTeamX);
	}

	public GameTeam(String id) {
		super(GameTeamX);
		this.id = id;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_gameteam";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public StringBuffer getMemo() {
		return memo;
	}

	public void setMemo(StringBuffer memo) {
		this.memo = memo;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public boolean getOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public TeamType getTeamType() {
		return teamType;
	}

	public void setTeamType(TeamType teamType) {
		this.teamType = teamType;
	}

	public void checkTeamNameRepeat() throws AppException, Exception {
		if (GameTeamX.count("teamName", this.teamName) > 0)
			throw new AppException("战队名已经被注册！");
	}

	public void saveTeamLogo(File srcFile) throws AppException, Exception {
		// 创建文件夹
		File filesDir = getFilesDir();
		filesDir.mkdirs();

		// 保存文件
		File logoFile = new File(filesDir, GameTeamX.teamLogoFName);
		StreamPipe.fileToFile(srcFile, logoFile);
	}

	@Override
	public GameTeam create() throws AppException, Exception {
		// 战队名不能重复
		checkTeamNameRepeat();

		// 战队序列号
		setId(SequenceX.currentNum(table()) + "");

		// 创建成员(队长)
		TeamMember tm = new TeamMember();
		tm.setTeamId(id);
		tm.setUsername(creator);
		tm.setPassed(true);
		tm.setRoleId(TeamRole.ID_Leader);
		tm.create();

		// 初始化职位
		TeamRoleX.initTeamRoles(id);

		// 发消息给队长
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, creator);
			msg.putContent(null, "恭喜您创建了" + teamName + "队。您现在有自己的战队了～～");
			msg.create();
		}

		// 保存
		return super.create();
	}

	public void mod() throws AppException, Exception {
		update(toMdoMap());
	}

	public String getLogoPath() {
		File logoFile = getLogoFile();
		if (logoFile.exists())
			return GameTeamX.getLogoPath(id) + "?" + logoFile.lastModified();
		else
			return GameTeamX.getDefaultLogoPath();
	}

	private File getLogoFile() {
		return new File(getFilesDir(), GameTeamX.teamLogoFName);
	}

	private File getFilesDir() {
		return new File(GameTeamX.getFileStoreDir(id));
	}

	// 解散战队
	public void disband(String reason) throws AppException, Exception {
		load();

		// 发信息给队长
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, creator);
			msg.putContent(null, "您解散了" + teamName + "队。您现在没有战队了～～");
			msg.create();
		}

		// 发消息给队员
		MList<TeamMember> members = getTeamMembers();
		for (TeamMember member : members) {
			if (member.getIsLeader())
				continue;
			{
				UserMsg msg = new UserMsg(UserMsg.EG_SYS, member.getUsername());
				msg.putContent(null, "很遗憾, 您所在的" + teamName
						+ "战队被队长解散了, 您现在没有战队了～～");
				msg.create();
			}
		}

		// 删除战队
		delete();
	}

	public void deleteAllMembers() throws AppException, Exception {
		TeamMemberX.deleteByTeamId(id);
	}

	public void deleteAllRoles() throws AppException, Exception {
		TeamRoleX.deleteByTeamId(id);
	}

	@Override
	public void delete() throws AppException, Exception {
		// 删除所有成员
		deleteAllMembers();

		// 删除职位
		deleteAllRoles();

		super.delete();
	}

	public void deleteMember(String memberName) throws AppException, Exception {
		if (!exists())
			return;
		load();

		TeamMember member = new TeamMember(this.id, memberName);
		if (!member.exists())
			return;

		// 发消息给个人
		{
			UserMsg msg = new UserMsg(UserMsg.EG_SYS, member.getUsername());
			msg.putContent(null, "很遗憾, 您已经被队长开除" + teamName + "战队, 您现在没有战队了～～");
			msg.create();
		}

		// 发消息给战队
		{
			TeamMsg msg = new TeamMsg(id, TeamMsg.MsgType.DELETE_MEMBER,
					creator, member.getUsername());
			msg.putContent("{SRC}把{DESC}踢出了战队");
			msg.create();
		}

		// 删除成员
		member.delete();

		// 战队总人数减一
		update("membercount", --memberCount);
	}

	public static class X extends Mdo_X<GameTeam> {

		private String teamLogoFName;

		private String fileStoreRootPath;

		private String fileHttpRootPath;

		private String defaultLogoPath;

		private String getFileStoreDir(String id) {
			return fileStoreRootPath + id;
		}

		public String getLogoPath(String id) {
			return fileHttpRootPath + id + "/" + teamLogoFName;
		}

		public String getDefaultLogoPath() {
				return fileHttpRootPath + "default.jpg";
		}
		
		public void setDefaultLogoPath(String defaultLogoPath) {
			this.defaultLogoPath = defaultLogoPath;
		}
		

		public String getTeamLogoFName() {
			return teamLogoFName;
		}

		public void setTeamLogoFName(String teamLogoFName) {
			this.teamLogoFName = teamLogoFName;
		}

		public String getFileStoreRootPath() {
			return fileStoreRootPath;
		}

		public void setFileStoreRootPath(String fileStoreRootPath) {
			this.fileStoreRootPath = fileStoreRootPath;
		}

		public String getFileHttpRootPath() {
			return fileHttpRootPath;
		}

		public void setFileHttpRootPath(String fileHttpRootPath) {
			this.fileHttpRootPath = fileHttpRootPath;
		}

		public PagedList<GameTeam> search(GameTeam team) throws AppException,
				Exception {
			String creator = team.getCreator();
			String teamName = team.getTeamName();

			StringBuffer filter = new StringBuffer(" 1=1 ");
			if (creator != null && !"".equals(creator))
				filter.append(" and creator like '%").append(creator).append(
						"%' ");
			if (teamName != null && !"".equals(teamName))
				filter.append(" and teamname like '%").append(teamName).append(
						"%' ");

			return findPaged(null, filter.toString(), null, false, -1, -1);
		}

		public GameTeam getByCreator(String username) throws AppException,
				Exception {
			MList<TeamMember> tms = TeamMemberX.find("username", username);
			if (tms.size() == 0)
				return null;

			return tms.getFirst().getTeam();
		}

		public MList<GameTeam> getS_Team() throws AppException, Exception {
			return this.find("teamType, point-", TeamType.S, null);
		}

		public MList<GameTeam> getA_Team() throws AppException, Exception {
			return this.find("teamType, point-", TeamType.A, null);
		}

		public MList<GameTeam> getB_Team() throws AppException, Exception {
			return this.find("teamType, point-", TeamType.B, null);
		}

		public MList<GameTeam> getC_Team() throws AppException, Exception {
			return this.find("teamType, point-", TeamType.C, null);
		}

		// TODO: 为Hall新增
		/**
		 * 找到所有用户共同存在的战队。 return null if 用户不再统一战队
		 * 
		 * @throws Exception
		 * @throws AppException
		 */
		public GameTeam getTeamOfUsers(MList<User> users) throws AppException,
				Exception {
			GameTeam team = null;
			for (User u : users) {
				GameTeam t = u.getTeam();
				if (t == null)
					return null;
				
				if (t.getId() == null)
					return null;

				if (team == null) {
					team = t;
					continue;
				}

				if (!team.getId().equals(t.getId()))
					return null;
			}
			return team;
		}

	}

	public TeamMember getTeamMember(String username) throws AppException,
			Exception {
		TeamMember member = new TeamMember(id, username);
		if (member.exists())
			return member.load();
		return null;
	}

	// TODO: 为Hall新增
	public MList<User> getUsers() throws AppException, Exception {
		MList<User> users = new MList<User>();
		if (id == null)
			return users;
		MList<TeamMember> members = TeamMemberX
				.find("teamid, passed", id, true);
		for (TeamMember m : members) {
			users.add(new User(m.getUsername()).load());
		}
		return users;
	}

	// TODO: 为Hall新增
	public void incPoint(int i) throws AppException, Exception {
		this.update("point", point + i);
	}

	public MList<TeamMember> getTeamMembers() throws AppException, Exception {
		return TeamMemberX.find("teamid", id);
	}

	public void cutAvator(int persent, int x, int y, int w, int h)
			throws AppException, Exception {
		File f = getLogoFile();
		if (!f.exists())
			return;
		SkyImage si = new SkyImageImpl(getLogoFile().getAbsolutePath());
		double r = (double) persent / 100;
		si.scale(r, r);
		si.cut(x, y, w, h);
		si.saveAs(f, SkyImage.FORMAT_JPG);
	}

	public User getTeamLeader() throws AppException, Exception {
		return new User(creator).load();
	}

	public MList<TeamRole> getRoles() throws AppException, Exception {
		return TeamRoleX.findByTeam(this);
	}

	public MList<TeamMember> getNotPassedMembers() throws AppException,
			Exception {
		return TeamMemberX.notPassed(this);

	}

	public void modType(String type) throws AppException, Exception {
		if (type == null || "".equals(type))
			return;

		update("teamType", type);
	}

	public void transferLeader(User srcUser, User descUser)
			throws AppException, Exception {
		load();

		if (srcUser.getUsername().equals(descUser.getUsername()))
			return;

		// 老队长变为新人
		new TeamMember(id, srcUser.getUsername()).setToNewer();

		// 设置新队长
		TeamMember descMember = new TeamMember(id, descUser.getUsername());
		descMember.setToLeader();

		// 发消息给新队长
		// msg_toTeamMember(TeamMsg.MsgType.MEMBER, null,
		// "恭喜您, 您现在是${TEAM}战队的新队长了", descMember);

		this.update("creator", descMember.getUsername());
	}

	public Object getTeamProInfo(MatchType matchType) throws AppException,
			Exception {
		TeamProInfo info = new TeamProInfo(id, matchType.getId());
		if (info.exists())
			info.load();
		else
			info = TeamProInfoX.createCertificate(this,matchType);
		return info;
	}

	public MList<TeamMember> getFirstingMembersTop9() throws AppException,
			Exception {
		return TeamMemberX.getFirstingByTeamId(id, 6);
	}

	public MList<TeamMember> getSecondingMembersTop9() throws AppException,
			Exception {
		return TeamMemberX.getSecondingByTeamId(id, 6);
	}

	public int getRepRank() {
		int ret = 0;
		if (reputation < 0) {
			ret = (-reputation / 50 >= 5) ? 5 : -reputation / 50 + 1;
		} else if (reputation >= 0 && reputation <= 250) {
			ret = reputation / 50 + 6;
		} else if (reputation >= 251 && reputation <= 750) {
			ret = (reputation - 250) / 100 + 11;
		} else if (reputation >= 751 && reputation <= 3250) {
			ret = (reputation - 750) / 500 + 16;
		} else if (reputation >= 3251 && reputation <= 8250) {
			ret = (reputation - 3250) / 1000 + 21;
		} else {
			ret = 26;
		}
		return ret;
	}

	public int getKulou() {
		int rank = getRepRank();
		if (rank <= 5)
			return rank;
		return 0;
	}

	public int getSignHeart() {
		int rank = getRepRank();
		if (rank > 5 && rank <= 10) {
			return rank - 5;
		}
		return 0;
	}

	public int getDoubleHeart() {
		int rank = getRepRank();
		if (rank > 10 && rank <= 15) {
			return rank - 10;
		}
		return 0;
	}

	public int getDiamond() {
		int rank = getRepRank();
		if (rank > 15 && rank <= 20) {
			return rank - 15;
		}
		return 0;
	}

	public int getCrown() {
		int rank = getRepRank();
		if (rank > 20 && rank <= 25) {
			return rank - 20;
		}
		return 0;
	}

	public int getSword() {
		int rank = getRepRank();
		if (rank == 26)
			return 1;
		return 0;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public void incTeamContribute(int i) throws AppException, Exception {
		this.update("teamcontribute", teamcontribute + i);
	}
	
	public WarZone getWarZone () {
		return WarZoneX.getItemsMap().get(areaId);
	}
}
