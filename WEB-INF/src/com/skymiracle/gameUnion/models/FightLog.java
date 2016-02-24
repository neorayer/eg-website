package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.UUID;

import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;
import com.skymiracle.gameUnion.models.team.*;

public class FightLog extends Mdo<FightLog> {

	public static class X extends Mdo_X<FightLog> {

	}

	private String id = UUID.randomUUID().toString();

	private String gameId;

	private String zoneId;

	private String roomId;

	boolean isResSure;

	// 在gf_startgame.c 中定义
	// #define GAME_RES_UNKNOWN 0
	// #define GAME_RES_HOST_WIN 1
	// #define GAME_RES_HOST_LOST 2
	public static final int GAME_RES_UNKNOWN = 0;
	public static final int GAME_RES_HOST_WIN = 1;
	public static final int GAME_RES_HOST_LOST = 2;
	int res;

	String hostGamers;
	String otherGamers;
	int hostTn;
	int otherTn;

	int gameSeconds;
	int mapChecksum;

	//游戏结果原因：未知
	public static final int REASON_UNKNOWN = 0;
	//游戏结果原因：完胜
	public static final int REASON_FULL = 1;
	//游戏结果原因：对手认输（提前退出）
	public static final int REASON_LEAVE = 2;
	int resReason;

	private String beginDate = CalendarUtil.getLocalDate();

	private String beginTime = CalendarUtil.getLocalDateTime();

	private String hostUsername;

	@Title("游戏录象Id")
	private String replayid;
	
	public static enum Type {
		normal, teamvsteam, inteam
	}

	private Type type = Type.normal;

	private String hostTeamId;

	private String otherTeamId;

	public FightLog() {
		super(FightLogX);
	}

	public FightLog(String id) {
		this();
		this.id = id;
	}

	public String getHostTeamId() {
		return hostTeamId;
	}

	public void setHostTeamId(String hostTeamId) {
		this.hostTeamId = hostTeamId;
	}

	public String getOtherTeamId() {
		return otherTeamId;
	}

	public void setOtherTeamId(String otherTeamId) {
		this.otherTeamId = otherTeamId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostUsername() {
		return hostUsername;
	}

	public void setHostUsername(String hostUsername) {
		this.hostUsername = hostUsername;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public boolean getIsResSure() {
		return isResSure;
	}

	public void setIsResSure(boolean isResSure) {
		this.isResSure = isResSure;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getHostGamers() {
		return hostGamers;
	}

	public void setHostGamers(String hostGamers) {
		this.hostGamers = hostGamers;
	}

	public String getOtherGamers() {
		return otherGamers;
	}

	public void setOtherGamers(String otherGamers) {
		this.otherGamers = otherGamers;
	}

	public int getHostTn() {
		return hostTn;
	}

	public void setHostTn(int hostTn) {
		this.hostTn = hostTn;
	}

	public int getOtherTn() {
		return otherTn;
	}

	public void setOtherTn(int otherTn) {
		this.otherTn = otherTn;
	}

	public int getGameSeconds() {
		return gameSeconds;
	}

	public void setGameSeconds(int gameSeconds) {
		this.gameSeconds = gameSeconds;
	}

	public int getMapChecksum() {
		return mapChecksum;
	}

	public void setMapChecksum(int mapChecksum) {
		this.mapChecksum = mapChecksum;
	}

	public int getResReason() {
		return resReason;
	}
	
	public String getResReasonWord() {
		switch(resReason) {
		case REASON_FULL:
			return "击毁对方基地";
		case REASON_LEAVE:
			return "对手先退";
		case REASON_UNKNOWN:
			return "";
		}
		return "";
	}

	public void setResReason(int resReason) {
		this.resReason = resReason;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public MList<User> getHostUsers() throws AppException, Exception {
		MList<User> users = new MList<User>();
		String[] ss = this.hostGamers.split(":");
		for (String s : ss) {
			if (s.length() == 0)
				continue;
			User user = new User(s).load();
			users.add(user);
		}
		return users;
	}

	public MList<User> getOtherUsers() throws AppException, Exception {
		MList<User> users = new MList<User>();
		String[] ss = this.otherGamers.split(":");
		for (String s : ss) {
			if (s.length() == 0)
				continue;
			User user = new User(s).load();
			users.add(user);
		}
		return users;
	}

	// TODO:要加上地图判断
	private String getRaceName(int tn) {
		return tn == 0 ? "蜀国" : "魏国";
	}

	public String getHostRaceName() {
		return getRaceName(this.hostTn);
	}

	public String getOtherRaceName() {
		return getRaceName(this.otherTn);
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_fightlog";
	}

	public String getShortDesc() throws AppException, Exception {
		String s = "";
		String hs = "";
		{
			MList<User> us = getHostUsers();
			for (User u : us)
				hs += u.getNickname() + " ";
		}
		String os = "";
		{
			MList<User> us = getOtherUsers();
			for (User u : us)
				os += u.getNickname() + " ";
		}

		switch (res) {
		case GAME_RES_HOST_WIN:
			s = "< " + hs + "> 战胜 < " + os + ">";
			break;
		case GAME_RES_HOST_LOST:
			s = "< " + os + "> 战胜 < " + hs + ">";
			break;
		default:
			break;
		}
		return s;
	}

	@Override
	public FightLog create() throws AppException, Exception {
		// 获取主队ID
		MList<User> hostUsers = getHostUsers();
		GameTeam hostTeam = GameTeamX.getTeamOfUsers(hostUsers);
		hostTeamId = hostTeam == null ? null : hostTeam.getId();

		// 获取客队ID
		MList<User> otherUsers = getOtherUsers();
		GameTeam otherTeam = GameTeamX.getTeamOfUsers(otherUsers);
		otherTeamId = otherTeam == null ? null : otherTeam.getId();

		// 判断对战类型
		if (hostTeamId == null || otherTeamId == null)
			type = Type.normal;
		else if (hostTeamId.equals(otherTeamId))
			type = Type.inteam;
		else
			type = Type.teamvsteam;

		GameTeam winnerTeam = null;
		GameTeam loserTeam = null;
		MList<User> winnerUsers = null;
		MList<User> loserUsers = null;
		// 更改积分
		switch (res) {
		case GAME_RES_HOST_WIN:
			winnerTeam = hostTeam;
			loserTeam = otherTeam;
			winnerUsers = hostUsers;
			loserUsers = otherUsers;
			break;
		case GAME_RES_HOST_LOST:
			winnerTeam = otherTeam;
			loserTeam = hostTeam;
			winnerUsers = otherUsers;
			loserUsers = hostUsers;
			break;
		default:
			break;
		}

		// GameType gameType = getGameType();
		GameMap gm=new GameMap(gameId,zoneId,mapChecksum);
		if(gm.exists()){
			gm.load();
			if(gm.getIsPointable()){
			// 战队胜负积分
			// 只有战队对抗赛teamvsteam才积分
			if (type.equals(Type.teamvsteam)) {
				changePoint(winnerTeam, loserTeam, winnerUsers, loserUsers);
			}
	
			// 个人胜负积分
			changeUsersPoint(winnerUsers, loserUsers);
			}
		}
		return super.create();
	}

	// public GameType getGameType() throws AppException, Exception {
	// // 根据游戏的ID和地图的校验码来取得游戏类型
	// return GameTypeX.getGameType(this.gameId, this.mapChecksum);
	// }

	/**
	 * @param point
	 * @return 根据积分获取当前等级
	 */
	public int getRankByPoint(int point) {
		double accurateRank = getAccurateRankByPoint(point);
		return formatDouble(accurateRank);

	}

	/**
	 * @param point
	 * @return 积分换算等级公式
	 */
	private static double getAccurateRankByPoint(double point) {
		double ret = 0;
		if (point >= 0 && point <= 200) {
			ret = point / 100 + 1;
		}
		if (point > 200 && point <= 600) {
			ret = point / 200 + 2;
		}
		if (point > 600 && point <= 1200) {
			ret = point / 300 + 3;
		}
		if (point > 1200 && point <= 2000) {
			ret = point / 400 + 4;
		}
		if (point > 2000 && point <= 22500) {
			ret = point / 500 + 5;
		}
		return ret;
	}

	private void changePoint(GameTeam winnerteam, GameTeam loserteam,
			MList<User> winners, MList<User> losers) throws AppException,
			Exception {
		String winnerTeamId = winnerteam.getId();
		String loserTeamId = loserteam.getId();

		Teampoint wtp = new Teampoint(winnerTeamId, zoneId);
		Teampoint ltp = new Teampoint(loserTeamId, zoneId);

		if (!wtp.exists()) {
			wtp.create();
		}
		wtp.load();

		if (!ltp.exists()) {
			ltp.create();
		}
		ltp.load();

		int winnerRank = wtp.getRank();
		int loserRank = ltp.getRank();

		// 更新胜队积分
		{
			int wpoint = getPoint(winnerRank, loserRank, true);
			int newPoint = wtp.getPoint() + wpoint;
			Logger.debug("update team winnerpoint="+wpoint);
			// 表中有记录
			wtp.update("point,rank,win", newPoint, getRankByPoint(newPoint),
					wtp.getWin() + 1);
			
			//计入战队记录
			FightHistory fh=new FightHistory();
			fh.setUserorteam(winnerTeamId);
			fh.setFightLogId(id);
			fh.setIswin(true);
			fh.setScore(wpoint);
			fh.create();
		}

		// 更新负队积分
		{
			int lpoint = getPoint(winnerRank, loserRank, false);
			int newPoint = ltp.getPoint() + lpoint;
			newPoint = newPoint < 0 ? 0 : newPoint;
			Logger.debug("update team loserpoint="+lpoint);
			ltp.update("point,rank,lose", newPoint, getRankByPoint(newPoint),
					ltp.getLose() + 1);
			
			//计入战队记录
			FightHistory fh=new FightHistory();
			fh.setUserorteam(loserTeamId);
			fh.setFightLogId(id);
			fh.setIswin(false);
			fh.setScore(lpoint);
			fh.create();
		}

		winnerteam.incTeamContribute(winners.size());
		loserteam.incTeamContribute(-losers.size());
	}

	private void changeUsersPoint(MList<User> winners, MList<User> losers)
			throws AppException, Exception {
		int winnersAvRank = getAverageRank(winners);
		Logger.debug("winnerAverageRank=" + winnersAvRank);
		int losersAvRank = getAverageRank(losers);
		Logger.debug("losersAverageRank=" + losersAvRank);
		for (User winner : winners) {
			Userpoint wup = winner.getPoint(zoneId);
			int point = getPoint(wup.getRank(), losersAvRank, true);
			point = filterPoint(wup.getPoint() + point, point, true);
			Logger.debug("update user winnerpoint="+point);
			wup.update("point,rank,win", wup.getPoint()+point, getRankByPoint(wup.getPoint()+point), wup.getWin() + 1);
			//战队贡献+1分
			winner.incTeamContribute(1);
			//计入个人记录
			FightHistory fh=new FightHistory();
			fh.setUserorteam(winner.getUsername());
			fh.setFightLogId(id);
			fh.setIswin(true);
			fh.setScore(point);
			fh.create();
		}
		for (User loser : losers) {
			Userpoint lup =  loser.getPoint(zoneId);
			int point = getPoint(winnersAvRank, lup.getRank(), false);
			point = filterPoint(lup.getPoint() + point, point, false);
			Logger.debug("update user loserpoint="+point);
			lup.update("point,rank, lose", lup.getPoint()+point, getRankByPoint(lup.getPoint()+point), lup
					.getLose() + 1);
			loser.incTeamContribute(-1);
			//计入个人记录
			FightHistory fh=new FightHistory();
			fh.setUserorteam(loser.getUsername());
			fh.setFightLogId(id);
			fh.setIswin(false);
			fh.setScore(point);
			fh.create();
		}
	}

	public static int filterPoint(int newPoint, int point, boolean isWinner) {
		int ret = point;
		if (isWinner) {
			if (newPoint >= 22500)
				return 0;
		} else {
			if (newPoint <= 0) {
				return 0;
			}
		}
		if (newPoint + point < 0) {
			return -newPoint;
		}
		if (newPoint + point > 22500) {
			return 22500 - newPoint;
		}
		return ret;
	}

	/**
	 * @param list
	 *            :winnerList or losersList 赢家或者输家的list
	 * @return user average Rank in list 获得赢家或者输家的平均等级
	 * @throws Exception
	 * @throws AppException
	 */
	private int getAverageRank(MList<User> users) throws AppException,
			Exception {
		int sumWinnerRank = 0;
		for (User user : users) {
			Userpoint u = user.getPoint(zoneId);
			sumWinnerRank += u.getRank();
		}
		int averageRank = sumWinnerRank / users.size();
		return averageRank;
	}

	public String getTypeName() {
		if (type.equals(Type.teamvsteam)) {
			return "战队对抗赛";
		} else if (type.equals(Type.inteam)) {
			return "战队内战";
		}
		return "路人野战";
	}

	public GameTeam getHostTeam() throws AppException, Exception {
		if (hostTeamId == null)
			return null;
		try {
			return new GameTeam(hostTeamId).load();
		} catch (NotExistException e) {
		}
		return null;
	}

	public GameTeam getOtherTeam() throws AppException, Exception {
		if (otherTeamId == null)
			return null;
		try {
			return new GameTeam(otherTeamId).load();
		} catch (NotExistException e) {
		}
		return null;
	}

	public String getHostTeamName() throws AppException, Exception {
		GameTeam t = getHostTeam();
		return t == null ? "-" : t.getTeamName();
	}

	public String getOtherTeamName() throws AppException, Exception {
		GameTeam t = getOtherTeam();
		return t == null ? "-" : t.getTeamName();
	}

	private static int getPoint(int winnerRank, int loserRank, boolean isWinner) {
		// 级别相差6以上，不算分
		double ret = 0;
		if (Math.abs(winnerRank - loserRank) > 6) {
			Logger.debug("isWinner=" + isWinner + " getPoint=0");
			return 0;
		}
		// 级别相等,赢100,输100
		if (winnerRank == loserRank) {
			Logger.debug("isWinner=" + isWinner + " getPoint="
					+ (isWinner ? 100 : -100));
			return isWinner ? 100 : -100;
		}
		int levelDiff = Math.abs(winnerRank - loserRank);
		if (winnerRank > loserRank) {
			ret = isWinner ? getHigherWin(levelDiff) : -getLowerLoss(levelDiff)
					* getLossFactorByRank(loserRank);
		}
		if (winnerRank < loserRank) {
			ret = isWinner ? getLowerWin(levelDiff) : -getHigherLoss(levelDiff)
					* getLossFactorByRank(winnerRank);
		}
		int ret_ = formatDouble(ret);
		Logger.debug("isWinner=" + isWinner + " getPoint=" + ret_);
		return ret_;
	}

	public static int formatDouble(double dou) {
		String strRank = String.valueOf(dou);
		NumberFormat nf = NumberFormat.getNumberInstance();
		Number n;
		int ret = 0;
		try {
			n = nf.parse(strRank);
			ret = n.intValue();
		} catch (Exception pe) {
			pe.printStackTrace();
		}
		return ret;
	}

	/**
	 * @param levelDiff
	 *            :相差级别
	 * @return 相差x级别，高级别的赢家应得分数绝对值
	 */
	private static int getHigherWin(int levelDiff) {
		return 100 - levelDiff * 5;
	}

	/**
	 * @param levelDiff
	 *            :相差级别
	 * @return 相差x级别，高级别的输家应得分数绝对值
	 */
	private static int getHigherLoss(int levelDiff) {
		return 100 + levelDiff * 5;
	}

	/**
	 * @param levelDiff
	 *            :相差级别
	 * @return 相差x级别，低级别的赢家应得分数绝对值
	 */
	private static int getLowerWin(int levelDiff) {
		if (levelDiff <= 3 && levelDiff > 0) {
			return 100 + levelDiff * 15;
		} else {
			return 145 + (levelDiff - 3) * 10;
		}
	}

	/**
	 * @param levelDiff
	 *            :相差级别
	 * @return 相差x级别，低级别的输家应得分数绝对值
	 */
	private static int getLowerLoss(int levelDiff) {
		if (levelDiff <= 3 && levelDiff > 0) {
			return 100 - levelDiff * 15;
		} else {
			return 55 - (levelDiff - 3) * 10;
		}
	}

	/**
	 * @param rank
	 *            :等级
	 * @return 经验值系数lossFactor
	 */

	public static double getLossFactorByRank(int rank) {
		double lossFactor = 0.10;
		if (rank >= 0 && rank < 2) {
			lossFactor = 0.10;
		}
		if (rank >= 2 && rank < 4) {
			lossFactor = 0.11;
		}
		if (rank >= 4 && rank < 6) {
			lossFactor = 0.25;
		}
		if (rank >= 6 && rank < 8) {
			lossFactor = 0.43;
		}
		if (rank >= 8 && rank < 10) {
			lossFactor = 0.67;
		}
		if (rank >= 10) {
			lossFactor = 1;
		}
		return lossFactor;
	}

	public String getReplayid() {
		return replayid;
	}

	public void setReplayid(String replayid) {
		this.replayid = replayid;
	}
	
	public Replay getReplay() throws AppException, Exception{
		return new Replay(replayid).load();
	}

	public ArrayList<User> getHostPlayers() throws AppException, Exception{
		ArrayList<User> hostPlayers =new ArrayList<User>();
		String hostGamers[] =this.getHostGamers().split(":");
		for (String host : hostGamers) 
			hostPlayers.add(new User(host).load());
		return hostPlayers;
	}
	
	public ArrayList<User> getOtherPlayers() throws AppException, Exception{
		ArrayList<User> otherPlayers =new ArrayList<User>();
		String otherGamers[] = this.getOtherGamers().split(":");
		for (String other : otherGamers) 
			otherPlayers.add(new User(other).load());
		return otherPlayers;
	}
	
	public GameZone getGameZone () throws AppException, Exception {
		return new GameZone(zoneId).load();
	}
}
