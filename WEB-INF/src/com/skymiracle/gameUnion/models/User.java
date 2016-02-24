package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import com.skymiracle.gameUnion.SHA1;
import com.skymiracle.gameUnion.exception.GameRoomIsFullException;
import com.skymiracle.gameUnion.models.bisai.Bisai;
import com.skymiracle.gameUnion.models.msg.LtMsgReqAddFriend;
import com.skymiracle.gameUnion.models.msg.RtMsg;
import com.skymiracle.gameUnion.models.msg.RtMsgAddFriendRes;
import com.skymiracle.gameUnion.models.msg.RtMsgChat;
import com.skymiracle.gameUnion.models.msg.RtMsgGameInOut;
import com.skymiracle.gameUnion.models.msg.RtMsgRoomInOut;
import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.team.TeamMember;
import com.skymiracle.gameUnion.models.team.TeamRole;
import com.skymiracle.gameUnion.models.user.*;
import com.skymiracle.gameUnion.runtime.MemCache;
import com.skymiracle.image.SkyImage;
import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.logger.Logger;
import com.skymiracle.mdo4.NullKeyException;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.ByteUtils;
import com.skymiracle.util.CalendarUtil;

public class User extends Mdo<User> {

	public static class X extends Mdo_X<User> {

		private String fileStoreRootPath = "/gf/tomcat/webapps/51bisaifiles/";

		private MemCache memCache = null;

		public MemCache getMemCache() {
			return memCache;
		}

		public void setMemCache(MemCache memCache) {
			this.memCache = memCache;
		}

		public String getFileStoreRootPath() {
			return fileStoreRootPath;
		}

		public void setFileStoreRootPath(String fileStoreRootPath) {
			this.fileStoreRootPath = fileStoreRootPath;
		}

	}

	// 超时限制，30秒
	public static int TIMEOUT_MILLSECONDS = 30 * 1000;

	private String username;
	private String password;
	private String email;
	private String question;
	private String answer;
	private String sex;
	private String nickname;
	private String realname;
	private String province;
	private String signature;
	private String telphone;
	private String phone;
	private String idcard;
	private int accountCoins;
	private int account;
	private int level;
	private int userType;
	private int curLevelRate;
	private boolean inGame = false;
	private int point;
	private int rank = 1; // 游戏等级
	private String regDateTime;
	private int vIpAddr;
	private boolean isPracticer; // 是否是陪练员

	private String qq;

	public static enum Profession {
		Player, Referee, Organizer
	}

	@Title("职业")
	private Profession profession = Profession.Player;

	@Title("出生日期")
	private String birthday;

	@Title("战区")
	private String warZoneId;

	private long bbsCount; // 发帖数
	private long bbsEssencePost; // 金精华帖数
	private long bbsBadEssencePost; // 银精华帖数
	private long bbsSilverPost; // 良好帖数
	private long bbsPoint; // 论坛积分
	private long bbsCharm; // 魅力值
	private long bbsMoney; // 论坛金钱
	private long bbsTimeCost; // 在线时间
	private long bbsPrestige; // 威望
	private long bbsContribute; // 贡献
	private long bbsStatus; // 用户禁言时间
	// -1为永久,0为正常,200810101010表示禁止到2008年10月10号10点10分
	private String bbsSignPicUuid; // 个性签名图片uuid
	private String bbsSignPicName; // 个性签名图片名字

	private boolean bbsUseSign = true; // bbs是否启用附件

	public static long BBSTEMPTIME = 0; // 发言间隔时间，不记数据库

	private int power; // 特殊权限

	public final static int POWER_TEAMMANAGER = 101; // 战队管理员，比赛管理员
	// public final static int POWER_PRACTICERMANAGER = 102 ;//陪练管理员
	private String honor; // 所获得的荣誉或者值得称道的东西

	private int teamContribute;

	private int reputation;

	@Title("真实IP")
	private String realIp = null;

	@Title("IP物理地点")
	private String ipLocation = null;
	private String createDate = CalendarUtil.getLocalDate();

	private String createDateTime = CalendarUtil.getLocalDateTime();

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWarZoneId() {
		return warZoneId;
	}

	public void setWarZoneId(String warZoneId) {
		this.warZoneId = warZoneId;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getIpLocation() {
		return ipLocation;
	}

	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public long getBbsTimeCost() {
		return bbsTimeCost;
	}

	public void setBbsTimeCost(long bbsTimeCost) {
		this.bbsTimeCost = bbsTimeCost;
	}

	public User() {
		super(UserX);
	}

	public User(String username) {
		this();
		setUsername(username);
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getVIpAddr() {
		return vIpAddr;
	}

	public void setVIpAddr(int ipAddr) {
		vIpAddr = ipAddr;
	}

	public boolean getInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return (nickname == null || "".equals(nickname))? username : nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCurLevelRate() {
		return curLevelRate;
	}

	public void setCurLevelRate(int curLevelRate) {
		this.curLevelRate = curLevelRate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String fatherDN(String baseDN) throws NullKeyException {
		return null;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "username" };
	}

	public String[] objectClasses() {
		return null;
	}

	public String selfDN() throws NullKeyException {
		return null;
	}

	@Override
	public String table() {
		return new String("tb_gameuser");
	}

	public int getAccountCoins() {
		return accountCoins;
	}

	public void setAccountCoins(int accountCoins) {
		this.accountCoins = accountCoins;
	}

	public String getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}

	public String getVIpAddrStr() {
		return ByteUtils.uint2ip(this.vIpAddr);
	}

	public long getBbsCount() {
		return bbsCount;
	}

	public void setBbsCount(long bbsCount) {
		this.bbsCount = bbsCount;
	}

	public long getBbsEssencePost() {
		return bbsEssencePost;
	}

	public void setBbsEssencePost(long bbsEssencePost) {
		this.bbsEssencePost = bbsEssencePost;
	}

	public long getBbsBadEssencePost() {
		return bbsBadEssencePost;
	}

	public void setBbsBadEssencePost(long bbsBadEssencePost) {
		this.bbsBadEssencePost = bbsBadEssencePost;
	}

	public long getBbsSilverPost() {
		return bbsSilverPost;
	}

	public void setBbsSilverPost(long bbsSilverPost) {
		this.bbsSilverPost = bbsSilverPost;
	}

	public long getBbsPoint() {
		return bbsPoint;
	}

	public void setBbsPoint(long bbsPoint) {
		this.bbsPoint = bbsPoint;
	}

	public long getBbsCharm() {
		return bbsCharm;
	}

	public void setBbsCharm(long bbsCharm) {
		this.bbsCharm = bbsCharm;
	}

	public long getBbsMoney() {
		return bbsMoney;
	}

	public void setBbsMoney(long bbsMoney) {
		this.bbsMoney = bbsMoney;
	}

	public long getBbsPrestige() {
		return bbsPrestige;
	}

	public void setBbsPrestige(long bbsPrestige) {
		this.bbsPrestige = bbsPrestige;
	}

	public long getBbsStatus() {
		return bbsStatus;
	}

	public void setBbsStatus(long bbsStatus) {
		this.bbsStatus = bbsStatus;
	}

	public String getBbsSignPicUuid() {
		return bbsSignPicUuid;
	}

	public void setBbsSignPicUuid(String bbsSignPicUuid) {
		this.bbsSignPicUuid = bbsSignPicUuid;
	}

	public String getBbsSignPicName() {
		return bbsSignPicName;
	}

	public void setBbsSignPicName(String bbsSignPicName) {
		this.bbsSignPicName = bbsSignPicName;
	}

	public boolean getBbsUseSign() {
		return bbsUseSign;
	}

	public void setBbsUseSign(boolean bbsUseSign) {
		this.bbsUseSign = bbsUseSign;
	}

	public long getBbsContribute() {
		return bbsContribute;
	}

	public void setBbsContribute(long bbsContribute) {
		this.bbsContribute = bbsContribute;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean getIsPracticer() {
		return isPracticer;
	}

	public void setIsPracticer(boolean isPracticer) {
		this.isPracticer = isPracticer;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	/* runtime attributes */

	private int slotAddr = -1;

	private long loginTime;

	private long lastHeartbeat = 0;

	private String sessionKey;

	private String roomId = "";

	public int getSlotAddr() {
		return slotAddr;
	}

	public void setSlotAddr(int slotAddr) {
		this.slotAddr = slotAddr;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public long getLastHeartbeat() {
		return lastHeartbeat;
	}

	public void setLastHeartbeat(long lastHeartbeat) {
		this.lastHeartbeat = lastHeartbeat;
	}

	public String getSessionKey() {
		return new SHA1(password).encrypting();
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	private String getHeartBeatKey() {
		return "user-" + username + "-heartBeat";
	}

	// TODO: 注意：当MemCache的两个存储memcached server 和 localMap(app server)都关闭时，有风险。
	// 因此，要保持至少个打开
	public void doHeartBeat() {
		UserX.memCache.put(getHeartBeatKey(), System.currentTimeMillis());
	}

	public void stopHeartBeat() {
		UserX.memCache.remove(getHeartBeatKey());
	}

	public long getCurHeartBeat() {
		long l = 0;
		String key = getHeartBeatKey();
		Object v = UserX.memCache.get(key);
		if (v == null)
			return l;

		try {
			l = Long.parseLong(v.toString());
		} catch (Exception e) {
			Logger.error("getCurHeartBeat of " + key, e);
			return l;
		}
		return l;
	}

	public boolean getIsOnline() {
		return !(isTimeout());
	}

	public void checkTimeout() {
		if (isTimeout())
			logout();
	}

	public boolean isTimeout() {
		return System.currentTimeMillis() - getCurHeartBeat() > TIMEOUT_MILLSECONDS;
	}

	public long getNextHeartBeatLess() {
		return TIMEOUT_MILLSECONDS
				- (System.currentTimeMillis() - getCurHeartBeat());
	}

	public void login(String realIp) throws AppException, Exception {
		if (!isTimeout())
			throw new AppException("该用户名已经登陆到平台中了。如果上次为非正常退出，请等待"
					+ (getNextHeartBeatLess() / 1000 + 1) + "秒后再次登陆。");

		// 获取IP的物理地点
		String ipLocation = IpLocX.getLocation(realIp);

		// 更新数据
		loginTime = System.currentTimeMillis();
		lastHeartbeat = System.currentTimeMillis();
		sessionKey = UUID.randomUUID().toString();
		MdoMap mm = new MdoMap();
		mm.put("inGame", false);
		mm.put("loginTime", loginTime);
		mm.put("lastHeartbeat", lastHeartbeat);
		mm.put("sessionKey", sessionKey);
		mm.put("realip", realIp);
		mm.put("iplocation", ipLocation);
		update(mm).load();

		// 清空消息队列
		RtMsgX.removeUserQueue(this);

		// 查找是否有请求加好友消息放入队列
		MList<LtMsgReqAddFriend> msgs = LtMsgReqAddFriendX.find("dest",
				username);
		for (LtMsgReqAddFriend msg : msgs) {
			msg.delete();
			RtMsgX.sendToDest(msg);
		}

	}

	public void logout() {
		try {
			leaveRoom(true);
		} catch (Exception e) {
			Logger.warn("logout", e);
		}
		RtMsgX.removeUserQueue(this);

		stopHeartBeat();

		sessionKey = "";
		MdoMap mm = new MdoMap();
		mm.put("lastHeartbeat", 0);
		mm.put("sessionKey", sessionKey);
		try {
			update(mm);
		} catch (Exception e) {
			Logger.warn("logout", e);
		}

	}

	public void leaveRoom(boolean isSendMsg) throws AppException, Exception {
		if (this.roomId == null || this.roomId.equals(""))
			return;
		GameRoom room = new GameRoom(roomId).load();
		this.roomId = "";
		this.update("vIpAddr,roomId,lastHeartbeat", 0, roomId, System
				.currentTimeMillis());

		if (isSendMsg) {
			RtMsgRoomInOut msg = new RtMsgRoomInOut();
			msg.setWho(this);
			msg.setDest("__ROOM__");
			msg.setIsIn(false);
			RtMsgX.send(msg, room);
		}
	}

	public GameRoom enterRoom(String roomId, int speed, boolean isSendMessage)
			throws AppException, Exception {
		// 离开现有的房间
		leaveRoom(isSendMessage);

		GameRoom room = new GameRoom(roomId).load();
		if (room.isFull()) {
			throw new GameRoomIsFullException(room);
		}

		this.roomId = roomId;
		this.update("roomId,lastHeartbeat", roomId, System.currentTimeMillis());

		// 分配一个虚拟IP
		room.allocVipAddr(this);

		// 重新载入。TODO: 注意哦，这里可能应该从底层Mdo甚至Store层做起
		load();

		// 发送消息
		if (isSendMessage) {
			// send user enter room msg
			RtMsgRoomInOut msg = new RtMsgRoomInOut();
			msg.setWho(this);
			msg.setDest("__ROOM__");
			msg.setIsIn(true);
			msg.setSpeed(speed);
			RtMsgX.send(msg, room);
		}
		return room;
	}

	public RtMsg<?> getMsg() throws AppException, Exception {
		return RtMsgX.getMsg(this);
	}

	public void sendMsgToRoom(RtMsg<?> msg) throws AppException, Exception {
		if (this.roomId.equals(""))
			throw new AppException("用户不在该房间内，不能向房间发送信息");
		RtMsgX.send(msg, getRoom());
	}

	public int getMsgQueueSize() {
		return RtMsgX.getMsgQueueSize(this);
	}

	public GameRoom getRoom() throws AppException, Exception {
		if (this.roomId.equals(""))
			return null;
		return new GameRoom(this.roomId).load();
	}

	public boolean isInRoom(GameRoom gameRoom) throws AppException, Exception {
		return gameRoom.roomId.equals(this.roomId);
	}

	public void enterGame(int speed) throws AppException, Exception {
		setInGame(true);
		update("ingame", true);

		RtMsgGameInOut msg = new RtMsgGameInOut();
		msg.setIsIn(true);
		msg.setWho(this);
		RtMsgX.send(msg, getRoom());
	}

	public void leaveGame() throws AppException, Exception {
		setInGame(false);
		update("ingame", false);

		RtMsgGameInOut msg = new RtMsgGameInOut();
		msg.setIsIn(false);
		msg.setWho(this);
		RtMsgX.send(msg, getRoom());
	}

	public String getRealIp() {
		return realIp;
	}

	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}

	public User reqAddFriend(String friendname) throws AppException, Exception {
		User fUser = new User(friendname).load();

		LtMsgReqAddFriend msg = new LtMsgReqAddFriend(username, friendname);
		msg.setSendernick(nickname);
		msg.createIfNotExist();

		RtMsgX.sendToDest(msg);

		return fUser;
	}

	public User addFriend(String friendName) throws AppException, Exception {
		User fUser = new User(friendName).load();

		// 给自己加朋友
		{
			UserFriend uf = new UserFriend();
			uf.setUsername(username);
			uf.setFriendname(friendName);
			uf.createIfNotExist();
		}

		// 给好友加自己
		{
			UserFriend uf = new UserFriend();
			uf.setUsername(friendName);
			uf.setFriendname(username);
			uf.createIfNotExist();
		}

		// 发消息给对方
		RtMsgAddFriendRes msg = new RtMsgAddFriendRes(this, friendName);
		RtMsgX.sendToDest(msg);

		return fUser;
	}

	public User delFriend(String friendName) throws AppException, Exception {
		User gu = new User(friendName).load();

		// 双向删除好友关系
		new UserFriend(username, friendName).delete();
		new UserFriend(friendName, username).delete();

		return gu;
	}

	public MList<User> getFriends() throws AppException, Exception {
		MList<User> friendUsers = new MList<User>();
		MList<UserFriend> ufs = UserFriendX.find("username", username);
		for (UserFriend uf : ufs) {
			User u = new User(uf.getFriendname());
			if (!u.exists()) {
				uf.delete();
				continue;
			}
			u.load();
			friendUsers.add(u);
		}
		return friendUsers;
	}

	public void incPoint(int i) throws AppException, Exception {
		this.update("point", point + i);
	}

	public void sendMsg(RtMsgChat msg) throws AppException, Exception {
		if ("__ROOM__".equals(msg.getDest())) {
			// 发到当前房间
			sendMsgToRoom(msg);
		} else {
			// 发给个人
			RtMsgX.send(msg);
		}
	}

	public File getAvatorFile() {
		String filename = this.username + ".jpg";
		return new File(getFilesDir(), filename);
	}

	private File getAvatorSmallFile() {
		String filename = this.username + "Small.jpg";
		return new File(getFilesDir(), filename);
	}

	private File getFilesDir() {
		return new File(UserX.fileStoreRootPath + "/" + this.username + "/" + 1
				+ "/");
	}

	private File getUserFilesDir() {
		return new File(UserX.fileStoreRootPath + "/user/" + this.username
				+ "/");
	}

	public File getoldAvatorFile() {
		String filename = this.username + ".jpg";
		return new File(getUserFilesDir(), filename);
	}

	private File getoldAvatorSmallFile() {
		String filename = this.username + "Small.jpg";
		return new File(getUserFilesDir(), filename);
	}

	public String getAvatorUrl() throws AppException, Exception {
		File logoFile = getAvatorFile();

		File oldLogoFile = getoldAvatorFile();

		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/1/" + this.username
					+ ".jpg?" + logoFile.lastModified();
		else if (oldLogoFile.exists())
			return "/51bisaifiles/user/" + this.username + "/" + this.username
					+ ".jpg?" + logoFile.lastModified();
		else
			return "/51bisaifiles/user/default.jpg";
	}

	public String getAvatorSmallUrl() {
		File logoFile = getAvatorSmallFile();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/1/" + this.username
					+ "Small.jpg?" + logoFile.lastModified();
		else if (getoldAvatorSmallFile().exists())
			return "/51bisaifiles/user/" + this.username + "/" + this.username
					+ "Small.jpg" + "?" + logoFile.lastModified();
		else
			return "/51bisaifiles/user/default34.jpg";
	}

	private File getDefaultFilesDir() {
		return new File(UserX.fileStoreRootPath + "/user/", "default.jpg");
	}

	public String getAvatarAbsolutePath() throws AppException, Exception {
		File filesDir = getFilesDir();
		if (filesDir.exists()) {
			return getAvatorFile().getAbsolutePath();
		} else {
			return getDefaultFilesDir().getAbsolutePath();
		}

	}

	public boolean getHasTeam() throws AppException, Exception {
		TeamMember member = getTeamMember();
		return member.getPassed();
	}

	public GameTeam getTeam() throws AppException, Exception {
		return getTeamMember().getTeam();
	}

	public boolean isTeamLeader() throws AppException, Exception {
		return getTeamMember().getIsLeader();
	}

	public TeamRole getTeamRole() throws AppException, Exception {
		return getTeamMember().getRole();
	}

	public boolean getHasPower(Authority au) throws AppException, Exception {
		return getTeamRole().getHasPower(au);
	}

	// public MList<Bisai> getMyJoinBiSais() throws AppException, Exception {
	// return BisaiX.findMyJoinBiSais(username);
	// }

	public MList<Bisai> getBisais8() throws AppException, Exception {
		return BisaiX.find("bisaiTime-:0,8");
	}

	public TeamMember getTeamMember() throws AppException, Exception {
		return TeamMemberX.getByUsername(username);
	}

	public void sendMemberMsg(TeamMember member, TeamMsg msgSrc)
			throws AppException, Exception {
		// if (username == null || member.getUsername() == null)
		// return;
		//
		// // if(username.equals(member.getUsername()))
		// // return;
		//
		// TeamMsg msg = new TeamMsg();
		// msg.setSender(username);
		// msg.setReceiver(member.getUsername());
		// msg.setSubject(msgSrc.getSubject());
		// msg.setContent(msgSrc.getContent());
		// msg.setMsgType(TeamMsg.MsgType.MEMBER.toString());
		// msg.create();
	}

	public void sendGroupMsg(TeamRole role, TeamMsg msg) throws AppException,
			Exception {
		MList<TeamMember> members = null;

		if (role.getId() == null || "".equals(role.getId()))
			members = getTeam().getTeamMembers();
		else
			members = role.getTeamMembers();

		for (TeamMember member : members) {
			sendMemberMsg(member, msg);
		}

	}

	// public MList<PubPost> getNewPubPosts() throws AppException, Exception {
	// MList<PubPost> posts = PubPostX.find("username, postdatetime+",
	// username);
	// for(PubPost post: posts) {
	// if (post.hasReadBy(this))
	//				 
	// }
	// }

	public Date getCreateDateTime1() {
		return CalendarUtil.stringToDate(createDateTime, "yyyy-MM-dd hh:MM:ss");
	}

	// 获取用户证书
	public UserProInfo getUserProInfo(MatchType matchType) throws AppException,
			Exception {
		UserProInfo info = new UserProInfo(username, matchType.getId());

		if (info.exists())
			info.load();
		else
			info = UserProInfoX.createCertificate(this, matchType);

		return info;
	}

	// 补充证书信息
	public void addCertificateInfo(String profession, String birthday,
			String warZoneId) throws AppException, Exception {
		update("profession,birthday,warZoneId", profession, birthday, warZoneId);
	}

	public int getTeamContribute() {
		return teamContribute;
	}

	public void setTeamContribute(int teamContribute) {
		this.teamContribute = teamContribute;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public String getDispName() {
		if (nickname != null && !"".equals(nickname))
			return nickname;
		return username;
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

	public WarZone getWarZone() {
		return WarZoneX.getItemsMap().get(warZoneId);
	}

	public static void main(String[] args) {
		User user = new User();
		user.setReputation(-1);
		System.out.println(user.getKulou());
		System.out.println(user.getSignHeart());
	}

	public String getAvatorUrlPath() {
		return "/51bisaifiles/" + this.username + "/1/" + this.username
				+ ".jpg" + "?";
	}

	public String getStrProfession() {
		if (User.Profession.Player.equals(profession)) {
			return "玩家";
		} else if (User.Profession.Referee.equals(profession)) {
			return "裁判";
		} else if (User.Profession.Organizer.equals(profession)) {
			return "比赛组织者";
		} else {
			return "未选择";
		}
	}

	public void incTeamContribute(int i) throws AppException, Exception {
		this.update("teamContribute", teamContribute + i);
	}

	public boolean getNeedAddInfo() {
		if (this.profession == null || this.profession.equals(""))
			return true;
		if (this.warZoneId == null || this.warZoneId.equals(""))
			return true;
		if (this.birthday == null || this.birthday.equals(""))
			return true;
		return false;
	}

	public MList<Album> findAlbum() throws AppException, Exception {
		MdoMap mdoMap = new MdoMap();
		mdoMap.put("username", username);
		String filter=" id !='1'";
		return AlbumX.find(mdoMap, filter);
	}

	public Userpoint getPoint(String zoneId) throws AppException, Exception {
		Userpoint up = new Userpoint(username, zoneId);
		return up.exists()? up.load(): up.create();
	}
}
