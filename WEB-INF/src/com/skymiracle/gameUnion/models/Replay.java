package com.skymiracle.gameUnion.models;
import static com.skymiracle.gameUnion.Singletons.ReplayX;

import java.io.File;
import java.util.UUID;

import com.skymiracle.gameUnion.models.bisai.Bisai;
import com.skymiracle.gameUnion.models.bisai.Match;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.exception.AppException;
public class Replay  extends AbsMdo<Replay>{
	
	public static String REPLAY_STORE_ROOTPATH = "/gf/tomcat/webapps/51bisaifiles/";
	
	public static class X extends Mdo_X<Replay>{
		//查询reportAndReplay首页replay
		public PagedList<Replay> findReplay11ForRepMain(String bisaiId) throws AppException, Exception{
			return this.findPaged(1, 11, "appId,createDateTime-",bisaiId);
		}
		//查找战报的replay
		public MList<Replay> findReplaysByArticle(String articleId) throws AppException, Exception {
			return this.find("articleId,createDateTime+",articleId);
		}
		//查找某比赛所有的replay数量
		public long findAllReplayCountByBisai(String bisaiId) throws AppException, Exception{
			return this.count("appId", bisaiId);
		}
		//查找文章评论
		public PagedList<Replay> findPagedReplay(String bisaiId,
				int pageNum,int countPerPage) throws AppException, Exception {
			return this.findPaged(pageNum, countPerPage, "appId,createDateTime-", bisaiId);
		}
	}
	public Replay() {
		super(ReplayX);
	}
	
	public Replay(String id) {
		this();
		this.id=id;
	}
	public Replay(App.TYPE appType,String appId) {
		this();
		this.appType=appType;
		this.appId=appId;
	}
	 
	@Title("录象id")
	private String id = UUID.randomUUID().toString();
	
	@Title("类型")
	private App.TYPE appType= App.TYPE.bisai;
	
	@Title("比赛id")
	private String appId;
	
	@Title("缩图")
	private String path;
	
	
	@Title("录象名称")
	private String name;
	
	@Title("录象播放次数")
	private String playCount;
	
	@Title("上传者")
	private String creatorName;
	
	@Title("录象长度")
	private String length;
	
	@Title("录象大小")
	private String size;
	
	@Title("游戏主名")
	private String gameName;
	
	@Title("更新时间")
	private String updateTime;
	
	@Title("录像版本")
	private short version;
	
	@Title("录像地图")
	private String map;
	
	@Title("录像种族")
	private String races;
	
	@Title("赛事名称")
	private String matchId;
	
	@Title("录像类型")
	private String teamType;
	
	@Title("录像介绍")
	private String descriptions;
	
	@Title("录像保存者")
	private String playerId;
	
	@Title("对抗方式")
	private String vsName;
	
	@Title("胜利队伍号")
	private int winnerTeamNumber;
	
	@Title("战报ID")
	private String articleId;
	
	@Title("场次号")
	private String changNo;
	
	
	public int getWinnerTeamNumber() {
		return winnerTeamNumber;
	}

	public void setWinnerTeamNumber(int winnerTeamNumber) {
		this.winnerTeamNumber = winnerTeamNumber;
	}
	public String getVsName() {
		return vsName;
	}

	public void setVsName(String vsName) {
		this.vsName = vsName;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public App.TYPE getAppType() {
		return appType;
	}

	public void setAppType(App.TYPE appType) {
		this.appType = appType;
	}


	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayCount() {
		return playCount;
	}

	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}

	public String getRaces() {
		return races;
	}

	public void setRaces(String races) {
		this.races = races;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_replay";
	}
	
	public void delete() throws AppException, Exception{
		String replayPath=new Replay(id).getPath();
		if (replayPath!= null)
			new File(replayPath).deleteOnExit();
		super.delete();
	}
	public Match getMatch(){
		Match match=null;
		try {
			match=new Match(matchId).load();
		} catch (Exception e) {
			
		}
		return match;
	}
	public Bisai getBisai(){
		Bisai bisai=null;
		try {
			bisai=new Bisai(appId).load();
		} catch (Exception e) {
			
		}
		return bisai;
	}
//	private File getFilesDir() {
//		return new File(REPLAY_STORE_ROOTPATH + "/bisai/"+appId+"/bisaiReplay/" );
//	}
	
	
	
	
//	public Replay updateReplay(Replay replay,File file) throws AppException, Exception {
//		File f=getFilesDir();
//		f.mkdirs();
//		File newFile = new File(f , UUID.randomUUID().toString());
//		StreamPipe.fileToFile(file, newFile);
//		replay.create(newFile);
//		return replay;
//	}
//	
//	public void uploadReplay(File file) throws AppException, Exception{
//		File f=getFilesDir();
//		f.mkdirs();
//		File newFile = new File(f,UUID.randomUUID().toString());
//		StreamPipe.fileToFile(file, newFile);
//		this.create(newFile);
//	}
	
//	public void create(File newFile) throws AppException, Exception{
//		try{
//			War3Record record = new W3gService().parse(newFile);
//			//录像为魔兽争霸1.20版本
//			if(record.getBuildNumber()==6052){
//				this.setWinnerTeamNumber(record.findWinnerTeamNumber());
//				this.setSize(ViewTools.getTgmkSize(record.getFileSize()));
//				this.setVersion(record.getBuildNumber());
//				this.setLength(CalendarUtil.getHms(record.getLastActionTime()/1000) );
//				this.setGameName(record.getGameName());
//				this.setMap(record.getMapName());
//				this.setCreatorName(record.getCreatorName());
//				// replay.setPlayerId(record.getPlayerRecord().getPlayerId());
//				this.setVsName(record.fetchVsName());
//			}
//			}catch (Exception e) {
//			e.printStackTrace();
//		}
//		//非魔兽录像，直接创建
//		if (path != null)
//			new File(path).deleteOnExit();
//		this.setPath(newFile.getAbsolutePath());
//		super.create();
//	}

	
	
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getChangNo() {
		return changNo;
	}

	public void setChangNo(String changNo) {
		this.changNo = changNo;
	}
	
	public String getShortMap(){
		String ret="";
		if(map!=null&&!"".equals(map)){
			if(map.indexOf("\\")!=-1){
				int last=map.lastIndexOf("\\")+1;
				ret=map.substring(last);
			}
			if(ret.indexOf(")")!=-1){
				int last=ret.lastIndexOf(")")+1;
				ret=ret.substring(last);
			}
		}
		return ret;
	}
	
	 
}