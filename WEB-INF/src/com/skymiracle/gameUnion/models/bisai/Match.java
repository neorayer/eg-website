package com.skymiracle.gameUnion.models.bisai;


import static com.skymiracle.gameUnion.Singletons.MatchX;

import static com.skymiracle.gameUnion.Singletons.SignUpUserX;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import com.skymiracle.gameUnion.models.AbsMdo;

import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.io.StreamPipe;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

public class Match extends AbsMdo<Match>{
	
	public final static String MATCH_DRAW = "-1$";
	
	private String id = UUID.randomUUID().toString();
	
	@Title("teamId1")
	private String teamId1;
	
	@Title("teamId2")
	private String teamId2;
	
	@Title("裁判")
	private String referee;
	
	@Title("轮Id")
	private String roundId;
	
	private String curRoundMatchNo;
	
	@Title("小组Id")
	private String groupId;
	
	@Title("该比赛的战报")
	private String reportId;
	
	@Title("team1比分")
	private int team1score;
	
	@Title("team2比分")
	private int team2score;
	
	@Title("比赛时间")
	private long matchTime;
	
	private String loser;
	
	@Title("获胜方")
	private String winner;
	
	private String bisaiId;
	
	@Title("阶段Id")
	private String stageId;
	
	//左节点指针
	private String match1Node;
	
	//右节点指针
	private String match2Node;
	
	//用于双败赛 预定败者组位置,向前的指针，循环赛，座位的预订
	
	private String arrangeMatchLeftNode;
	private String arrangeMatchRightNode;
	
	public boolean searchLeftFlag;
	public boolean searchRightFlag;
	
	public final static int RES_NOTSTART = 0;
	public final static int RES_ENDNOTSURE = 1;
	public final static int RES_BESURE = 2;
	
	@Title("比赛结果")
	private int res = RES_NOTSTART;
	public Match leftNode;
	public Match rightNode;
	public boolean rootNodeFlag; 
	private boolean commented;
	
	public boolean getCommented() {
		return commented;
	}

	public void setCommented(boolean commented) {
		this.commented = commented;
	}

	public Match getLeftNode() {
		return leftNode;
	}

	public Match getRightNode() {
		return rightNode;
	}

	public boolean getRootNodeFlag() {
		return rootNodeFlag;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public boolean getSearchLeftFlag() {
		return searchLeftFlag;
	}

	public boolean getSearchRightFlag() {
		return searchRightFlag;
	}

	public String getLoser() {
		return loser;
	}

	public void setLoser(String loser) {
		this.loser = loser;
	}

	public String getDrawParam(){
		return MATCH_DRAW;
	}

	public String getArrangeMatchLeftNode() {
		return arrangeMatchLeftNode;
	}

	public void setArrangeMatchLeftNode(String arrangeMatchLeftNode) {
		this.arrangeMatchLeftNode = arrangeMatchLeftNode;
	}

	public String getArrangeMatchRightNode() {
		return arrangeMatchRightNode;
	}

	public void setArrangeMatchRightNode(String arrangeMatchRightNode) {
		this.arrangeMatchRightNode = arrangeMatchRightNode;
	}

	public Match(String id){
		this();
		this.id = id;
	}
	

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getStageId() {
		return stageId;
	}

	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

	public String getBisaiId() {
		return bisaiId;
	}

	public void setBisaiId(String bisaiId) {
		this.bisaiId = bisaiId;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getTeam1score() {
		return team1score;
	}

	public void setTeam1score(int team1score) {
		this.team1score = team1score;
	}

	public int getTeam2score() {
		return team2score;
	}

	public void setTeam2score(int team2score) {
		this.team2score = team2score;
	}

	public long getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(long matchTime) {
		this.matchTime = matchTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeamId1() {
		return teamId1;
	}

	public void setTeamId1(String teamId1) {
		this.teamId1 = teamId1;
	}

	public String getTeamId2() {
		return teamId2;
	}

	public void setTeamId2(String teamId2) {
		this.teamId2 = teamId2;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getCurRoundMatchNo() {
		return curRoundMatchNo;
	}

	public void setCurRoundMatchNo(String curRoundMatchNo) {
		this.curRoundMatchNo = curRoundMatchNo;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMatch1Node() {
		return match1Node;
	}
	public void setMatch1Node(String match1Node) {
		this.match1Node = match1Node;
	}
	public String getMatch2Node() {
		return match2Node;
	}
	public void setMatch2Node(String match2Node) {
		this.match2Node = match2Node;
	}
	public String getReportId() {
		return reportId;
	}
	
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getRealLoser(){
		if(winner!=null&&winner.equals(teamId1)){
			return teamId2;
		}
		if(winner!=null&&winner.equals(teamId2)){
			return teamId1;
		}
		return "";
	}
	public Match() {
		super(MatchX);
	}
	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_match";
	}
	
	
	
	
//	public SignUpUser getTeamId1Info(){
//		SignUpUser s1=null;
//		try{
//			Bisai bisai=new Bisai(bisaiId).load();
//			if(bisai.getSolo()){
//				if(teamId1!=null&&!"".equals(teamId1)){
//					s1=new SignUpUser(bisaiId,teamId1).load();
//					
//				}
//			}else{
//				if(teamId1!=null&&!"".equals(teamId1)){
//					s1=SignUpUserX.getSignUpTeam(bisaiId,teamId1);
//				}
//			}
//		}catch (Exception e) {
//			//Logger.debug("match teamId1不存在");
//		}
//		return s1;
//	}
//	
//	public SignUpUser getTeamId2Info(){
//		SignUpUser s2=null;
//		try{
//			Bisai bisai=new Bisai(bisaiId).load();
//			if(bisai.getSolo()){
//				if(teamId2!=null&&!"".equals(teamId2)){
//					s2=new SignUpUser(bisaiId,teamId2).load();
//					
//				}
//			}else{
//				if(teamId2!=null&&!"".equals(teamId1)){
//					s2=SignUpUserX.getSignUpTeam(bisaiId,teamId2);
//				}
//			}
//		}catch (Exception e) {
//			//Logger.debug("match teamId1不存在");
//		}
//		return s2;
//	}
	
	//显示在赛程页面上的名字:单人赛
	public String getTeamId1SoloName(){
		return getTeamIdSoloName(teamId1);
	}
	public String getTeamId2SoloName(){
		return getTeamIdSoloName(teamId2);
	}
	public String getWinnerSoloName(){
		return getTeamIdSoloName(winner);
	}
	
	public String getWinnerDispName(){
		Bisai bisai=new Bisai(bisaiId);
		String name="";
		if(bisai.getSolo()){
			name=getWinnerSoloName();
		}else{
			name=getTeamIdTeamName(winner);
		}
		return name;
	}
	public String getTeamIdSoloName(String teamIdx){
		User user=new User(teamIdx);
		String username="";
		try {
			if(user.exists()){
				user.load();
			   if(user.getNickname()!=null&&!"".equals(user.getNickname())){
				   username=user.getNickname();
			   }else{
				   username=user.getUsername();
			   }
			}
		} catch (Exception e) {
			
		}
		return username;
	}
	
	//显示在赛程页面上的名字:团队赛
	public String getTeamId1TeamName(){
		return getTeamIdTeamName(teamId1);
	}
	public String getTeamId2TeamName(){
		return getTeamIdTeamName(teamId2);
	}
	public String getWinnerTeamName(){
		return getTeamIdTeamName(winner);
	}
	public String getTeamIdTeamName(String teamIdx){
		GameTeam team=new GameTeam(teamIdx);
		String teamname="";
		try {
			team.load();
			teamname=team.getTeamName();
			} catch (Exception e) {
				
			}
		return teamname;
	}
	
	public String getTeamId1DispName(){
		Bisai bisai=new Bisai(bisaiId);
		try {
			bisai.load();
		}catch (Exception e) {
			
		}
		String name="";
		if(bisai.getSolo()){
			name=getTeamId1SoloName();
		}else{
			name=getTeamId1TeamName();
		}
		return name;
	}
	
	public String getTeamId2DispName(){
		Bisai bisai=new Bisai(bisaiId);
		try {
			bisai.load();
		}catch (Exception e) {
			
		}
		String name="";
		if(bisai.getSolo()){
			name=getTeamId2SoloName();
		}else{
			name=getTeamId2TeamName();
		}
		return name;
	}
	public String getTeamId1DispPicUrl(){
		Bisai bisai=new Bisai(bisaiId);
		String url="";
		try{
			if(bisai.exists()){
				bisai.load();
			}
		
			if(bisai.getSolo()){
				url=getUser1().getAvatorUrl();
			}else{
				url=new GameTeam(teamId1).load().getLogoPath();
			}
		}catch(Exception e){
			
		}
		return url;
	}
	
	public String getTeamId2DispPicUrl(){
		Bisai bisai=new Bisai(bisaiId);
		String url="";
		try{
			if(bisai.exists()){
				bisai.load();
			}
			if(bisai.getSolo()){
				url=getUser2().getAvatorUrl();
			}else{
				url=new GameTeam(teamId2).load().getLogoPath();
			}
		}catch(Exception e){
			
		}
		return url;
	}
	
	
//	public SignUpUser getWinnerInfo(){
//		SignUpUser s=null;
//		if(winner!=null&&!"".equals(winner)){
//			try {
//				s=new SignUpUser(bisaiId,winner).load();
//			} catch (Exception e) {
//			//	
//			}
//		}
//		return s;
//	}
	public User getUser1(){
		User user=null;
		try {
			user=new User(teamId1).load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public User getUser2(){
		User user=null;
		try {
			user=new User(teamId2).load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public GameTeam getTeam1(){
		GameTeam bt=null;
		try {
			bt=getUser1().getTeam();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bt;
	}
	
	public GameTeam getTeam2(){
		GameTeam bt=null;
		try {
			bt= getUser2().getTeam();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bt;
	}
	public Match getMatch1(){
		Match match =null;
		try{
			if(!"".equals(match1Node)){
			match= new Match(match1Node).load();
			this.leftNode=match;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}
	public Match getMatch2(){
		Match match=null;
		try{
			if(!"".equals(match2Node)){
				match= new Match(match2Node).load();
				this.rightNode=match;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}
	
//	public BisaiTeam getWinnerTeam() throws AppException, Exception{
//		return new BisaiTeam(winner).load();
//	}
	
//	public MList<Replay> getReplays() throws AppException, Exception{
//		return ReplayX.find("appType,appId,matchid",App.TYPE.bisai,bisaiId,id);
//	}
	
	public boolean isEnded(){
		if("".equals(winner)||winner==null){
			return false;
		}
		return true;
	}
//	/**
//	 * 删除，同时删除相关战报，录像
//	 */
//	public void delete() throws AppException, Exception{
//		if(reportId!=null&&!"".equals(reportId)){
//			Article article=new Article(reportId).load();
//			if(article!=null)
//				article.deleteReport();
//		}
//		super.delete();
//	}
	/**
	 * format比赛时间
	 * @return
	 */              
	public String getFormatime(){
		if(matchTime==0)
			return "";
		String strTime=String.valueOf(matchTime);
		String year=strTime.substring(0,4);
		String month=strTime.substring(4,6);
		String day=strTime.substring(6,8);
		String hour=strTime.substring(8,10);
		String min=strTime.substring(10,12);
		return year+"-"+month+"-"+day+"  "+hour+":"+min;
	}
	/**
	 * 取得比赛战报
	 * @return
	 */
//	public Article getReport(){
//		if(reportId==null||"".equals(reportId))
//			return null;
//		Article article=null;
//		try {
//			article=new Article(reportId).load();
//			} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return article;
//	}
	
//	/**
//	 * 什么情况下该比赛能换座位
//	 * @return
//	 * @throws Exception 
//	 * @throws AppException 
//	 */
//	public boolean getCanExchangeSeat() throws AppException, Exception{
//		boolean ret=false;
//		if("".equals(winner)||winner==null){
//			BisaiStage bs=getStage();
//			if(bs.isEliminationMode()){
//				ret="1".equals(roundId)?true:false;
//			}
//			if(bs.isDoubleLoseMode()){
//				BisaiGroup bg=new BisaiGroup(bisaiId,stageId,groupId).load();
//				if(bg.isWinnerGroup()){
//					ret="1".equals(roundId)?true:false;
//				}
//			}
//		}
//		return ret;
//	}
	/**
	 * 检查是否可以换座
	 * @throws AppException
	 */
	public void checkStartWhenExchangeSeat() throws AppException{
		if(winner!=null&&!"".equals(winner)){
			throw new AppException("目标队伍比赛已经进行，无法换座");
		}
	}
//	public void createArticle() throws AppException, Exception {
//		Article ar = new Article();
//		ar.setId(UUID.randomUUID().toString());
//		ar.setAppType(App.TYPE.bisai);
//		ar.setAppId(bisaiId);
//		ar.setAuthor("");
//		ar.setType(Article.TYPE.reply);
//		ar.setTitle(getTeam1().getTeamName()+" vs " +getTeam2().getTeamName());
//		StringBuffer content=new StringBuffer();
//		content.append("日期："+matchTime);
//		content.append("比分：team1 "+team1score+":"+team1score+"team2");
//		ar.setContent(content);
//		ar.create();
//	}

//	public void upLoadReplay(File file) throws AppException, Exception {
//		Replay replay = new Replay(App.TYPE.bisai, bisaiId);
//		String name1=getTeam1().getTeamName();
//		String name2=getTeam2().getTeamName();
//		replay.setName(name1+"与"+name2+"的比赛录像");
//		replay.setMatchId(id);
//		replay.updateReplay(replay, file);
//	}
//	
//	public BisaiRound getRound() throws AppException, Exception{
//		return new BisaiRound(groupId,roundId).load();
//	}
//	
//	public BisaiStage getStage() throws AppException, Exception{
//		return new BisaiStage(bisaiId,stageId).load();
//	}
	
	/**
	 * 普通更新，不包括积分和晋级操作
	 * @param match
	 */
	public MdoMap baseUpdate(Match match){
		MdoMap mdoMap= new MdoMap();
		//mdoMap.put("teamId1", match.getTeamId1());
		//mdoMap.put("teamId2", match.getTeamId2());
		mdoMap.put("team1score", match.getTeam1score());
		mdoMap.put("team2score", match.getTeam2score());
		mdoMap.put("matchTime", match.getMatchTime());
		if("team1".equals(match.getWinner())){
			match.setWinner(match.getTeamId1());
			mdoMap.put("winner",match.getTeamId1());
		}
		if("team2".equals(match.getWinner())){
			match.setWinner(match.getTeamId2());
			mdoMap.put("winner",match.getTeamId2());
		}
		if("draw".equals(match.getWinner())){
			match.setWinner(Match.MATCH_DRAW);
		}
		return mdoMap;
	}
	/**
	 * 场次更新，包括积分和晋级操作
	 * @param match
	 * @throws AppException
	 * @throws Exception
	 */
//	public void updateBySchedule(Match match) throws AppException, Exception{
//		MdoMap mdoMap=baseUpdate(match);
//		BisaiStage bs=this.getStage();
//		if(bs.isEliminationMode()){
//			eliMatchUpdate(bs,match,mdoMap);
//		}
//		if(bs.isDoubleLoseMode()){
//			doubleloseMatchUpdate(bs,match,mdoMap);
//		}
//		if(bs.isLoopMode()){
//			loopMatchUpdate(bs,match,mdoMap);
//		}
//	}
	
	
	/**
	 * 循环赛比赛更新方式
	 * @param bs
	 * @param match
	 * @param mdoMap
	 * @throws Exception 
	 * @throws AppException 
	 */
//	private void loopMatchUpdate(BisaiStage bs,Match match,MdoMap mdoMap) throws AppException, Exception{
//		//通过比分进一步判断,淘汰赛必分胜负
//		if(match.getTeam1score()>match.getTeam2score()){
//			match.setWinner(match.getTeamId1());
//			mdoMap.put("winner", match.getTeamId1());
//		}
//		if(match.getTeam1score()<match.getTeam2score()){
//			match.setWinner(match.getTeamId2());
//			mdoMap.put("winner", match.getTeamId2());
//		}
//		//当没有胜者的时候mdoMap里winner存的是""，所以只是更新其他信息,这里不改队伍，remove掉
//		mdoMap.put("teamId1",teamId1);
//		mdoMap.put("teamId2",teamId2);
//		this.update(mdoMap);
//		
//		if(match.getWinner()!=null&&!"".equals(match.getWinner())){
//			updateLoopMatch(bs,match);
//		}
//	}
	
	
//	private void updateLoopMatch(BisaiStage bs,Match match) throws AppException, Exception {
//		int winscore=bs.getWinscore();
//		int drawscore=bs.getDrawscore();
//		int losescore=bs.getLosescore();
//		
//		BisaiGroupMember member1=BisaiGroupMemberX.loadLoopMember(bs.getId(), teamId1);
//		BisaiGroupMember member2=BisaiGroupMemberX.loadLoopMember(bs.getId(), teamId2);
//		
//		callbackLastResult(member1,member2,bs);
//		member1.load();
//		member2.load();
//		if(Match.MATCH_DRAW.equals(match.getWinner())){
//			member1.updateLoop(drawscore);
//			member2.updateLoop(drawscore);
//			this.update("winner,res",match.getWinner(),Match.RES_BESURE);
//		}
//		if(teamId1.equals(match.getWinner())){
//			member1.updateLoop(winscore);
//			member2.updateLoop(losescore);
//			this.update("winner,res",match.getWinner(),Match.RES_BESURE);
//		}
//		if(teamId2.equals(match.getWinner())){
//			member2.updateLoop(winscore);
//			member1.updateLoop(losescore);
//			this.update("winner,res",match.getWinner(),Match.RES_BESURE);
//		}
//		checkJinji(bs);
//		
//	}
	
//	//回置上一场比赛结果
//	private void callbackLastResult(BisaiGroupMember member1,BisaiGroupMember member2,BisaiStage bs) throws AppException, Exception{
//		int winscore=-bs.getWinscore();
//		int losescore=-bs.getLosescore();
//		int draw=-bs.getDrawscore();
//		if(!"".equals(winner)&&res==Match.RES_BESURE){
//			if(winner.equals(teamId1)){
//				member1.updateLoop(winscore);
//				member2.updateLoop(losescore);
//			}
//			if(winner.equals(teamId2)){
//				member1.updateLoop(losescore);
//				member2.updateLoop(winscore);
//			}
//			if(Match.MATCH_DRAW.equals(winner)){
//				member1.updateLoop(draw);
//				member2.updateLoop(draw);
//			}
//		}
//	}
	
//	/**
//	 * 若该场比赛是该小组最后一场比赛，计算晋级队伍.(更好解决方式是每场比赛结束的时候都动态计算一下)
//	 */
//	private void checkJinji(BisaiStage bs) throws AppException, Exception{
//		MList<Match> mlist=MatchX.find("groupid,winner", groupId,"");
//		if(mlist!=null&&mlist.size()==0){
//			//出线名额
//			int quota=bs.getQuota();
//			//最后一场比赛计算 members已按积分倒序排列
//			MList<BisaiGroupMember> members=BisaiGroupMemberX.findLadderByGroup(groupId);
//			JinjiAlgo(quota,members,bs);
//		}
//	}
	
//	/**
//	 *晋级算法:找出第quota名的分数,比这个分数高的晋级，和这个分数相等的，看看余下晋级名额是否够，不够则让组织者自己选
//	 */
//	private void JinjiAlgo(int quota, MList<BisaiGroupMember> members,BisaiStage bs) throws AppException, Exception {
//		int nextstageseat=bs.getSeat()+1;
//		int membersum=members.size();
//		List<BisaiGroupMember> equalScoreCache=new ArrayList<BisaiGroupMember>();
//		//本组人数比出线名额还小，或者相等，直接出线
//		if(membersum<=quota){
//			for(BisaiGroupMember member:members){
//				member.loopSetMemberToNextStage(nextstageseat);
//			}
//		}
//		if(membersum>quota){
//			BisaiGroupMember memberquota=members.get(quota-1);
//			int score=memberquota.getScore();
//			//比这个分高的晋级，和这个分相等的缓存住
//			for(int i=0;i<membersum;i++){
//				BisaiGroupMember member=members.get(i);
//				if(member.getScore()>score){
//					member.loopSetMemberToNextStage(nextstageseat);
//					quota--;
//				}
//				if(member.getScore()==score){
//					equalScoreCache.add(member);
//				}
//				if(member.getScore()<score){
//					continue;
//				}
//			}
//			//余下出线名额够
//			if(equalScoreCache.size()<=quota){
//				for(BisaiGroupMember member:equalScoreCache){
//					member.loopSetMemberToNextStage(nextstageseat);
//				}
//			}
//			//余下出线名额不够:余下名额告诉该组，同时给这几个人checkbox，让组织者自己选
//			if(equalScoreCache.size()>quota&&equalScoreCache.size()>0){
//				BisaiGroupMember aMember=equalScoreCache.get(0);
//				String groupId=aMember.getGroupId();
//				String stageId=aMember.getStageId();
//				BisaiGroup bg=new BisaiGroup(groupId);
//				bg.setStageId(stageId);
//				bg.load();
//				bg.updateRemainQuota(quota);
//				for(BisaiGroupMember member:equalScoreCache){
//					member.updateCheckbox(true);
//				}
//			}
//			
//		}
//	}
//	
//
//	/**
//	 * 双败赛比赛更新方式
//	 * @param bs
//	 * @param match
//	 * @param mdoMap
//	 * @throws Exception 
//	 * @throws AppException 
//	 */
//	private void doubleloseMatchUpdate(BisaiStage bs, Match match, MdoMap mdoMap) throws AppException, Exception {
//		
//		//通过比分进一步判断,淘汰赛必分胜负
//		if(match.getTeam1score()>match.getTeam2score()){
//			match.setWinner(match.getTeamId1());
//			mdoMap.put("winner", match.getTeamId1());
//		}
//		if(match.getTeam1score()<match.getTeam2score()){
//			match.setWinner(match.getTeamId2());
//			mdoMap.put("winner", match.getTeamId2());
//		}
//		//当没有胜者的时候,mdoMap里winner存的是""，所以只是更新其他信息
//		this.update(mdoMap);
//		
//		if(match.getWinner()!=null&&!"".equals(match.getWinner())){
//			BisaiGroup bg = new BisaiGroup(bisaiId,stageId,groupId).load();
//			if(bg.isWinnerGroup()){
//		    	winnerGroupUpdate(match.getWinner());
//		    }
//		    if(bg.isLoserGroup()){
//		    	loserGroupUpdate(match.getWinner());
//		    }
//		    if(bg.isFinalGroup()){
//		    	this.update("winner,res", match.getWinner(),Match.RES_BESURE);
//		    }
//		}
//	}
//	//胜者组胜利的继续，负的进入负者组
//	private void winnerGroupUpdate(String winnerId) throws AppException, Exception{
//		updateWinnerAndLoser(winnerId);
//		updatePlayerToArrangedMatch();
//	}
//	//负者组胜利的继续，负的淘汰
//	private void loserGroupUpdate(String winnerId) throws AppException, Exception{
//		updateWinnerAndLoser(winnerId);
//	}
//	//更新取胜的队伍，设定负的队伍
//	private void updateWinnerAndLoser(String winnerId) throws AppException, Exception{
//		String loser ="";
//		if(teamId1.equals(winnerId)){
//			loser = teamId2;
//		}else{
//			loser = teamId1;
//		}
//		this.setLoser(loser);
//		this.update("winner,loser,res", winnerId,loser,Match.RES_BESURE);
//		
//		List<Match> leftNodes = MatchX.find("match1Node",id);
//		if(leftNodes.size()>0){
//			Match toLeftMatch =leftNodes.get(0);
//			updateResult(toLeftMatch,"teamId1",winnerId);
//			return; 
//		}
//		List<Match> rightNodes = MatchX.find("match2Node", id);
//		if(rightNodes.size()>0){
//			Match toRightMatch =rightNodes.get(0);
//			updateResult(toRightMatch,"teamId2",winnerId);
//		}
//	}
//	//负的队伍更新到它的前置节点
//	private void updatePlayerToArrangedMatch() throws AppException, Exception{
//		List<Match> leftArrangeds = MatchX.find("arrangeMatchLeftNode",id);
//		if(leftArrangeds.size()>0){
//			Match toLeftMatch = leftArrangeds.get(0);
//			updateResult(toLeftMatch,"teamId1",loser);
//			return;
//		}
//		List<Match> rightArrangeds = MatchX.find("arrangeMatchRightNode", id);
//		if(rightArrangeds.size()>0){
//			Match toRightMatch = rightArrangeds.get(0);
//			updateResult(toRightMatch,"teamId2",loser);
//		}
//	}
//	/**
//	 * 淘汰赛比赛更新方式
//	 * @param bs
//	 * @param match
//	 * @param mdoMap
//	 * @throws AppException
//	 * @throws Exception
//	 */
//	private void eliMatchUpdate(BisaiStage bs,Match match,MdoMap mdoMap) throws AppException, Exception{
//		//通过比分进一步判断,淘汰赛必分胜负
//		if(match.getTeam1score()>match.getTeam2score()){
//			match.setWinner(match.getTeamId1());
//			mdoMap.put("winner", match.getTeamId1());
//		}
//		if(match.getTeam1score()<match.getTeam2score()){
//			match.setWinner(match.getTeamId2());
//			mdoMap.put("winner", match.getTeamId2());
//		}
//		//先更新本场比赛
//		this.update(mdoMap);
//		//再判断胜者进入到哪一场比赛，是不是晋级==
//		List<Match> leftNodes = MatchX.find("match1Node",id);
//		
//		if(leftNodes.size()>0){
//			Match toLeftMatch =leftNodes.get(0);
//		//	Logger.debug("=======winner is="+match.getWinner());
//			if(match.getWinner()!=null&&!"".equals(match.getWinner())){
//				this.update("res",Match.RES_BESURE);
//				updateResult(toLeftMatch,"teamId1",match.getWinner());
//				checkEliTeamJinji(bs,toLeftMatch,match.getWinner());
//			}
//			return; 
//		}
//		List<Match> rightNodes = MatchX.find("match2Node", id);
//		if(rightNodes.size()>0){
//			Match toRightMatch =rightNodes.get(0);
//			if(match.getWinner()!=null&&!"".equals(match.getWinner())){
//				this.update("res",Match.RES_BESURE);
//				updateResult(toRightMatch,"teamId2",match.getWinner());
//				checkEliTeamJinji(bs,toRightMatch,match.getWinner());
//			}
//		}
//		//如果是决赛节点
//		if(leftNodes.size()==0&&rightNodes.size()==0){
//			this.update("res",Match.RES_BESURE);
//		}
//	}
//	private void updateResult(Match match,String fieldFormat,Object... values) throws Exception{
////		if(match.getWinner()!=null&&!"".equals(match.getWinner())){
////			throw new AppException("该队伍的下一场比赛已经进行，无法更改胜负");
////		}
//		match.update(fieldFormat,values);
//		//如阶段未开始，则开始
//		new BisaiStage(match.getBisaiId(),match.getStageId()).load().start();
//	}
//	/**
//	 * 判断该队伍是否晋级:如果该场比赛处于这一阶段的最后一轮，则晋级,有空位的时候，自动入座
//	 * @param bs
//	 * @param match
//	 * @param winner
//	 * @throws Exception 
//	 * @throws AppException 
//	 */
//	private void checkEliTeamJinji(BisaiStage bs,Match match,String winner) throws AppException, Exception{
//		int matchRound=Integer.parseInt(match.getRoundId());
//		int lastRound=bs.getEliLastRoundNumber();
//		if(matchRound==lastRound){
//			BisaiTeamProg btp=new BisaiTeamProg(bs.getBisaiId(),winner).load();
//			int nextStageseat=bs.getSeat()+1;
//			btp.update("stageseat",nextStageseat);
//			btp.sitdownToStage(nextStageseat);
//		}
//	}
//	
//	public Match eliCreate() throws AppException, Exception{
//		Logger.debug("===create new Match");
//		if(MatchX.creatingTemp.containsKey(groupId)){
//			List<Match> matchs = MatchX.creatingTemp.get(groupId);
//			matchs.add(this);
//		}else{
//			List<Match> mats=new ArrayList<Match>();
//			mats.add(this);
//			MatchX.creatingTemp.put(groupId, mats);
//		}
//		return super.create();
//	}
//	
//	public Match doubleLoseWCreate() throws AppException, Exception {
//		MatchX.winnerCreatingTemp.add(this);
//		return super.create();
//	}
//	
//	/**
//	 * 左边结点入座
//	 * @param username
//	 * @throws Exception 
//	 * @throws AppException 
//	 */
//	public void putOneUserToTeamId1(String username) throws AppException, Exception{
////		Bisai bisai=new Bisai(bisaiId).load();
////		if(bisai.getRaid()){
////			username=new User(username).load().getTeam().getId();
////		}
//		this.update("teamId1",username);
//	}
//	/**
//	 * 右边结点入座
//	 * @param username
//	 * @throws AppException
//	 * @throws Exception
//	 */
//	public void putOneUserToTeamId2(String username) throws AppException, Exception{
////		Bisai bisai=new Bisai(bisaiId).load();
////		if(bisai.getRaid()){
////			username=new User(username).load().getTeam().getId();
////		}
//		this.update("teamId2",username);
//	}
//	
////	public String getFindScore() {
////		try {
////			MList<Match> matchs= MatchX.find("groupId,teamid1,teamid2", this.groupId, this.teamId1, this.teamId2);
////			if(matchs.size()==1){
////				return matchs.get(0).getTeam1score() + ":" + matchs.get(0).getTeam2score();
////			}else {
////				 matchs= MatchX.find("groupId,teamid2,teamid1", this.groupId, this.teamId1, this.teamId2);
////				if(matchs.size()==1){
////					return matchs.get(0).getTeam2score() + ":" + matchs.get(0).getTeam1score();
////				}else {
////					return "无比分";
////				} 
////			}
////		} catch (Exception e) {
////			return "无比分";
////		}
////	}

	public void dealTeam1Score(String strteam1score) {
		if(strteam1score!=null&&!"".equals(strteam1score)){
			int score1=0;
			try{
				score1=Integer.parseInt(strteam1score);
			}catch (Exception e) {
				e.printStackTrace();
			}
			this.setTeam1score(score1);
		}
	}

	public void dealTeam2Score(String strteam2score) {
		if(strteam2score!=null&&!"".equals(strteam2score)){
			int score2=0;
			try{
				score2=Integer.parseInt(strteam2score);
			}catch (Exception e) {
				e.printStackTrace();
			}
			this.setTeam2score(score2);
		}
	}

//	//循环赛创建
//	public void loopCreate(BisaiGroupMember left, BisaiGroupMember right,int loopRound) throws AppException, Exception {
//		//偶数轮主客场翻转
//		if((loopRound+1)%2!=0){
//			this.setArrangeMatchLeftNode(""+left.getGroupseat());
//			this.setTeamId1(left.getUsername());
//			this.setArrangeMatchRightNode(""+right.getGroupseat());
//			this.setTeamId2(right.getUsername());
//		}else{
//			this.setArrangeMatchLeftNode(""+right.getGroupseat());
//			this.setTeamId1(right.getUsername());
//			this.setArrangeMatchRightNode(""+left.getGroupseat());
//			this.setTeamId2(left.getUsername());
//		}
//		this.create();
//	}
	
	
	static public class X extends Mdo_X<Match> {
		
		private String fileStoreRootPath = "/gf/tomcat/webapps/51bisaifiles/";

		public String getFileStoreRootPath() {
			return fileStoreRootPath;
		}

		public void setFileStoreRootPath(String fileStoreRootPath) {
			this.fileStoreRootPath = fileStoreRootPath;
		}

		public MList<Match> getHeadLineMatchs(User user) throws AppException,
				Exception {
			// TODO 目前暂时不用User,以后如果要根据用户来判断
			return find("username", "eg_admin");
		}
		
		public X() throws AppException, Exception {
		}
		
		
		
//		/**
//		 * 查找某一比赛某阶段的第一轮所有比赛
//		 * @param bisaiId
//		 * @param seat
//		 * @return
//		 * @throws Exception 
//		 * @throws AppException 
//		 */
//		public MList<Match> getFirstRoundMatchsByBisaiStage(String bisaiId,int seat,String username) throws AppException, Exception{
//			MList<Match> matchs=new MList<Match>();
//			BisaiStage bs=BisaiStageX.getStageByBisaiAndSeat(bisaiId,seat);
//			if(bs==null)
//				return matchs;
//			if(bs.isEliminationMode()){
//				matchs=this.find("bisaiId,stageId,roundId", bisaiId,bs.getId(),"1");
//			}
//			//找到胜者组第一轮的所有比赛
//			if(bs.isDoubleLoseMode()){
//				String winnerGroup=BisaiGroupX.getDoubleLoseGroupId(bs.getId(), BisaiGroup.GROUPTYPE.winnerGroup);
//				matchs=this.find("bisaiId,stageId,groupId,roundId", bisaiId,bs.getId(),winnerGroup,"1");
//			}
//			if(bs.isLoopMode()){
//				BisaiGroupMember bgm=BisaiGroupMemberX.findOneEmptySeat(bisaiId,bs.getId());
//				if(bgm!=null){
//					bgm.update("username", username);
//					int seatNo=bgm.getGroupseat();
//					matchs=findLoopMatchsByGroupseat(bisaiId,bs.getId(),bgm.getGroupId(),seatNo);
//				}
//			}
//			return matchs;
//		}
//		public List<Match> getAllMatch(String stageId) throws AppException, Exception{
//			return MatchX.find("stageId", stageId);
//		}
		public MList<Match> findMatchInfo(String groupId,String roundId) throws AppException, Exception{
			return this.find("groupId,roundId",groupId, roundId);
		}
		//没有举行的比赛,winner为空
		public MList<Match> findMatchOfNotHold(String groupId,String roundId) throws AppException, Exception{
			return this.find("groupId,roundId,winner",groupId, roundId,"");
		}
		//查找该组某一轮的比赛
		public MList<Match> findMatchsOfAGroupInARound(String groupId, String roundId) throws AppException, Exception {
			return this.find("groupId,roundId", groupId,roundId);
		}
		public String findDoubleLoseFinal(String groupId) throws AppException, Exception{
			List<Match> match=this.find("groupId", groupId);
			if(match.size()>0)
				return match.get(0).getId();
			return null;
		}
		public MList<Match> findTowTeamid(String groupId,  String teamid1,String teamid2) throws AppException, Exception {
			 MList<Match> matchs=this.find("groupId,teamid1,teamid2", groupId, teamid1, teamid2);
			 matchs.addAll(this.find("groupId,teamid2,teamid1", groupId, teamid1, teamid2));
			return matchs;
		}
//		//寻找前一节点by 前一轮次和前一场的场次
//		public String findPreMatchNodeId(String newGroupId,int preLun,int preRoundMatchNo){
//			if(creatingTemp.containsKey(newGroupId)){
//				List<Match> matchs = creatingTemp.get(newGroupId);
//				for(Match match:matchs){
//					String preRoundId =  String.valueOf(preLun);
//					if(match.getRoundId().equals(preRoundId)&&
//							match.getCurRoundMatchNo().equals(String.valueOf(preRoundMatchNo))){
//						return match.getId();
//					}
//				}
//			}
//			return null;
//		}
//		//寻找最后一组前置节点
//		public String findFinalPreNode(String finalGroupId,int perMatchNo){
//			Match ret=groupRootNodes.get(perMatchNo-1);
//			if(creatingTemp.containsKey(finalGroupId)){
//				List<Match> matchs = creatingTemp.get(finalGroupId);
//				matchs.add(ret);
//			}else{
//				List<Match> mas=new ArrayList<Match>();
//				mas.add(ret);
//				creatingTemp.put(finalGroupId, mas);
//			}
//			return ret.getId();
//		}
//		public void visitTree(List<Match> matchs,List<Match> visitedList) {
//			String maxMatchId = getRootNode(matchs);
////			for(Match mats:matchs){
////				System.out.println("matchid:"+mats.getId()+" leftNode:"+mats.getLeftNode()+" rightNode:"+mats.getRightNode());
////			}
//			for(Match match:matchs){
//				if(match.getId().equals(maxMatchId)){
//					match.rootNodeFlag=true;
//					MatchX.eliRootTemp=match;
//					Logger.debug("visitTree,eliRootTempId="+MatchX.eliRootTemp.getId());
//					getPreorder(match,visitedList);
//				}
//			}
//		}
//		public void visitWinnerTree(List<Match> matchs,List<Match> visitedList) {
//			String maxMatchId = getRootNode(matchs);
//			//long t1=System.currentTimeMillis();
//			for(Match match:matchs){
//				match.getMatch1();
//				match.getMatch2();
//				if(match.getId().equals(maxMatchId)){
//					match.rootNodeFlag=true;
//					MatchX.winnerRoot=match;
//					//Logger.debug("visitTree,winnerRootTempId="+MatchX.winnerRoot.getId());
//					getPreorder(match,visitedList);
//				}
//			}
//			//long t2=System.currentTimeMillis();
//			//System.out.println("winnerPreorder cost time:"+(t2-t1));
//		}
//		public void visitLoserTree(List<Match> matchs,List<Match> visitedList) {
//			String maxMatchId = getRootNode(matchs);
//			for(Match match:matchs){
//				match.getMatch1();
//				match.getMatch2();
//				if(match.getId().equals(maxMatchId)){
//					match.rootNodeFlag=true;
//					MatchX.loserRoot=match;
//					//Logger.debug("visitTree,loserRootTempId="+MatchX.loserRoot.getId());
//					//long t1=System.currentTimeMillis();
//					getPreorder(match,visitedList);
//					//long t2=System.currentTimeMillis();
//					//System.out.println("loserPreorder cost time:"+(t2-t1));
//				}
//			}
//		}
		
		private String getRootNode(List<Match> matchs){
			int max=0;
			String maxMatchId=null;
			for(Match match:matchs){
				String round=match.getRoundId();
				int curRound = Integer.parseInt(round);
				if(curRound>max){
					max=curRound;
					maxMatchId = match.getId();
				}
			}
			return maxMatchId;
		}
		
		//访问节点
		private void visitNode(Match match,List<Match> visitedList){
			visitedList.add(match);
		}
		
		//中序遍历二叉树
		private void getPreorder(Match node,List<Match> visitedList){
			if(node!=null){
				getPreorder(node.getMatch1(),visitedList);
				visitNode(node,visitedList);
				getPreorder(node.getMatch2(),visitedList);
			}
		}
//		public String findDLPreMatchNodeId(int preLun,int preRoundMatchNo) {
//			for(Match match:winnerCreatingTemp){
//				String preRoundId =  String.valueOf(preLun);
//				if(match.getRoundId().equals(preRoundId)&&
//						match.getCurRoundMatchNo().equals(String.valueOf(preRoundMatchNo))){
//					return match.getId();
//				}
//			}
//			return null;
//		}
		
		/**
		 * 创建败者组第一轮
		 * @param br
		 * @param curLunMatNo
		 * @param leftNode
		 * @param rightNode
		 * @throws AppException
		 * @throws Exception
		 */
//		public void createLoserGroupFristRoundMatch(BisaiRound br,int curLunMatNo,String leftNode,String rightNode) throws AppException, Exception{
//			int round=Integer.parseInt(br.getId());
//			Match match = new Match();
//				match.setCurRoundMatchNo(String.valueOf(curLunMatNo));
//				match.setBisaiId(br.getBisaiId());
//				match.setStageId(br.getStageId());
//				match.setMatch1Node(leftNode);
//				match.setMatch2Node(rightNode);
//				match.setRoundId(br.getId());
//				match.setGroupId(br.getGroupId());
//				String leftMatchId = getWillBeArrangeMatchId(round,2*curLunMatNo-1,winnerOverall);
//				match.setArrangeMatchLeftNode(leftMatchId);
//				String rightMatchId = getWillBeArrangeMatchId(round,2*curLunMatNo,winnerOverall);
//				match.setArrangeMatchRightNode(rightMatchId);
//				match.create();
//			putCreatedMatchs(match,loserOverall);
//		}
		public String getWillBeArrangeMatchId(int round,int curLunMatNo,Map<Integer,Map<Integer,Match>> fmap){
			Map<Integer,Match> map = fmap.get(Integer.valueOf(round));
			return map.get(Integer.valueOf(curLunMatNo)).getId();
		}
		public void putCreatedMatchs(Match matc,Map<Integer,Map<Integer,Match>> map) throws AppException, Exception{
			int round=Integer.parseInt(matc.getRoundId());
			String matchId=matc.getId();
			int curLunMatNo=Integer.parseInt(matc.getCurRoundMatchNo());
			Integer iRound = Integer.valueOf(round);
			Integer iCurLunMatNo = Integer.valueOf(curLunMatNo);
			if(map.containsKey(iRound)){
				Map<Integer,Match> match=map.get(iRound);
				match.put(iCurLunMatNo, new Match(matchId).load());
			}else{
				Map<Integer,Match> mat=new HashMap<Integer,Match>();
				mat.put(iCurLunMatNo, new Match(matchId).load());
				map.put(iRound,mat);
			}
		}
		/**
		 * 创建败者组奇数次轮(除开第一轮)
		 * @param br
		 * @param curLunMatNo
		 * @param leftNode
		 * @param rightNode
		 * @throws AppException
		 * @throws Exception
		 */
//		public void createLoserGroupOddRoundMatch(BisaiRound br,int curLunMatNo,String leftNode,String rightNode) throws AppException, Exception{
//			int round=Integer.parseInt(br.getId());
//			Match match = new Match();
//			match.setCurRoundMatchNo(String.valueOf(curLunMatNo));
//			match.setBisaiId(br.getBisaiId());
//			match.setStageId(br.getStageId());
//			match.setMatch1Node(leftNode);
//			match.setMatch2Node(rightNode);
//			match.setRoundId(br.getId());
//			match.setGroupId(br.getGroupId());
//			String leftMatchId = getWillBeArrangeMatchId(round-1,2*curLunMatNo-1,loserOverall);
//			match.setArrangeMatchLeftNode(leftMatchId);
//			String rightMatchId = getWillBeArrangeMatchId(round-1,2*curLunMatNo,loserOverall);
//			match.setArrangeMatchRightNode(rightMatchId);
//			match.create();
//			putCreatedMatchs(match,loserOverall);
//		}
//		public String createLoserGroupEvenRoundMatch(BisaiRound br,int curLunMatNo,String leftNode,String rightNode) throws AppException, Exception{
//			int round=Integer.parseInt(br.getId());
//			Match match = new Match();
//			match.setCurRoundMatchNo(String.valueOf(curLunMatNo));
//			match.setBisaiId(br.getBisaiId());
//			match.setStageId(br.getStageId());
//			match.setMatch1Node(leftNode);
//			match.setMatch2Node(rightNode);
//			match.setRoundId(br.getId());
//			match.setGroupId(br.getGroupId());
//			String leftMatchId=getWillBeArrangeMatchId(round-1,curLunMatNo,loserOverall);
//			match.setArrangeMatchLeftNode(leftMatchId);
//			String rightMatchId=getWillBeArrangeMatchId(round/2+1,curLunMatNo,winnerOverall);
//			match.setArrangeMatchRightNode(rightMatchId);
//			match.create();
//			putCreatedMatchs(match,loserOverall);
//			return match.getId();
//		}
//		
//		/**
//		 *创建淘汰赛 
//		 */
//		public void createEli(BisaiRound br,int eachArea,int stageseat) throws AppException, Exception {
//			int i=Integer.parseInt(br.getId());
//			String groupId=br.getGroupId();
//			String bisaiId=br.getBisaiId();
//			String stageId=br.getStageId();
//			
//			for(int curLunMatNo=1;curLunMatNo<=eachArea/Math.pow(2,i);curLunMatNo++){
//				//初始化第一轮场次
//				if(i==1){
//					Match match=new Match();
//					match.setGroupId(groupId);
//					match.setRoundId(br.getId());
//					match.setCurRoundMatchNo(curLunMatNo+"");
//					match.setBisaiId(bisaiId);
//					match.setStageId(stageId);
//					
//					//把已进入该阶段的人抓进来
//					//入座1号
//					BisaiTeamProg user1=BisaiTeamProgX.findOnePersonWhoNotSitdownByStage(bisaiId, stageseat);
//					
//					
//					String username1=user1.getUsername();
//					if(username1!=null&&!"".equals(username1)){
//						match.setTeamId1(username1);
//						new BisaiTeamProg(bisaiId,username1).load().shiftToSitdown();
//					}
//					//入座2号
//					BisaiTeamProg user2=BisaiTeamProgX.findOnePersonWhoNotSitdownByStage(bisaiId, stageseat);
//					String username2=user2.getUsername();
//					if(username2!=null&&!"".equals(username2)){
//						match.setTeamId2(username2);
//						new BisaiTeamProg(bisaiId,username2).load().shiftToSitdown();
//					}
//					
//					
//					match.eliCreate();
//				}else{
//				//初始化其他轮次
//					String leftNodeId = MatchX.findPreMatchNodeId(groupId,i-1,2*curLunMatNo-1);
//					String rightNodeId = MatchX.findPreMatchNodeId(groupId,i-1,2*curLunMatNo);
//					Match match=new Match();
//					match.setGroupId(groupId);
//					match.setRoundId(br.getId());
//					match.setCurRoundMatchNo(curLunMatNo+"");
//					match.setBisaiId(bisaiId);
//					match.setStageId(stageId);
//					match.setMatch1Node(leftNodeId);
//					match.setMatch2Node(rightNodeId);
//					match.eliCreate();
//				}
//			}
//		}
//		
//		public Match findEliRootMatch(String id) {
//			List<Match> matchs=creatingTemp.get(id);
//			int sum=matchs.size()-1;
//			Match match=matchs.get(sum-1);
//			return match;
//		}
//		/**
//		 * 创建淘汰赛决赛组场次
//		 * @param bg
//		 * @param finalTeamSum
//		 * @param round
//		 * @throws AppException
//		 * @throws Exception
//		 */
//		public void createFinalEli(BisaiRound bg, int finalTeamSum,int round) throws AppException, Exception {
//			String groupId=bg.getId();
//			String bisaiId=bg.getBisaiId();
//			String stageId=bg.getStageId();
//			int j=Integer.parseInt(bg.getId());
//			for(int curLunMatNo=1;curLunMatNo<=finalTeamSum/Math.pow(2,j-round);curLunMatNo++){
//				String leftNodeId =null;
//				String rightNodeId = null;
//				if(j-round==1){
//					leftNodeId = findFinalPreNode(groupId,2*curLunMatNo-1);
//					rightNodeId = findFinalPreNode(groupId,2*curLunMatNo);
//					}else{
//					leftNodeId = findPreMatchNodeId(groupId,j-1,2*curLunMatNo-1);
//					rightNodeId=findPreMatchNodeId(groupId,j-1,2*curLunMatNo);
//				}
//			
//				Match match=new Match();
//				match.setGroupId(groupId);
//				match.setCurRoundMatchNo(curLunMatNo+"");
//				match.setStageId(stageId);
//				match.setBisaiId(bisaiId);
//				match.setMatch1Node(leftNodeId);
//				match.setMatch2Node(rightNodeId);
//				match.create();
//			}
//		}
		/**
		 * 创建双败赛胜者组场次
		 * @param br
		 * @throws Exception 
		 * @throws AppException 
		 */
//		public String createDlWinnerGroup(BisaiRound br,int teamCount,int stageseat) throws AppException, Exception {
//			int round=Integer.parseInt(br.getId());
//			String stageId=br.getStageId();
//			String bisaiId=br.getBisaiId();
//			String groupId=br.getGroupId();
//			String rootMatchId=null;
//			for(int curLunMatNo=1;curLunMatNo<=teamCount/Math.pow(2,round);curLunMatNo++){
//				//初始化第一轮场次
//				if(round==1){
//					Match match=new Match();
//					match.setRoundId(round+"");
//					match.setStageId(stageId);
//					match.setCurRoundMatchNo(curLunMatNo+"");
//					match.setBisaiId(bisaiId);
//					match.setGroupId(groupId);
//					//把已进入该阶段的人抓进来
//					//入座1号
//					BisaiTeamProg user1=BisaiTeamProgX.findOnePersonWhoNotSitdownByStage(bisaiId, stageseat);
//					String username1=user1.getUsername();
//					if(username1!=null&&!"".equals(username1)){
//						match.setTeamId1(username1);
//						new BisaiTeamProg(bisaiId,username1).load().shiftToSitdown();
//					}
//					//入座2号
//					BisaiTeamProg user2=BisaiTeamProgX.findOnePersonWhoNotSitdownByStage(bisaiId, stageseat);
//					String username2=user2.getUsername();
//					if(username2!=null&&!"".equals(username2)){
//						match.setTeamId2(username2);
//						new BisaiTeamProg(bisaiId,username2).load().shiftToSitdown();
//					}
//					match.doubleLoseWCreate();
//					rootMatchId=match.getId();
//					putCreatedMatchs(match,MatchX.winnerOverall);
//				}else{
//					//初始化其他轮次
//					String leftNodeId = MatchX.findDLPreMatchNodeId(round-1,2*curLunMatNo-1);
//					String rightNodeId =MatchX.findDLPreMatchNodeId(round-1,2*curLunMatNo);
//					Match match=new Match();
//					match.setRoundId(round+"");
//					match.setStageId(stageId);
//					match.setCurRoundMatchNo(curLunMatNo+"");
//					match.setBisaiId(bisaiId);
//					match.setGroupId(groupId);
//					match.setMatch1Node(leftNodeId);
//					match.setMatch2Node(rightNodeId);
//					match.doubleLoseWCreate();
//					rootMatchId=match.getId();
//					putCreatedMatchs(match,MatchX.winnerOverall);
//				}
//			}
//			return rootMatchId;
//			
//		}
//		/**
//		 * 创建双败赛决赛
//		 * @param finalGroup
//		 * @param winnerRootMatch
//		 * @param loserRootMatch
//		 * @throws AppException
//		 * @throws Exception
//		 */
//		public void createDlFinal(BisaiGroup finalGroup,
//				String winnerRootMatch, String loserRootMatch) throws AppException, Exception {
//			Match match = new Match();
//			match.setBisaiId(finalGroup.getBisaiId());
//			match.setStageId(finalGroup.getStageId());
//			match.setGroupId(finalGroup.getId());
//			match.setMatch1Node(winnerRootMatch);
//			match.setMatch2Node(loserRootMatch);
//			match.create();
//		}
//		/**
//		 * 是否出现了换队情况
//		 * @param oldMatch
//		 * @param newMatch
//		 * @throws AppException
//		 * @throws Exception
//		 */
//		public void exchangeTeams(Match oldMatch, Match newMatch) throws AppException, Exception {
//			String oldTeamId1=oldMatch.getTeamId1();
//			String newTeamId1=newMatch.getTeamId1();
//			String oldTeamId2=oldMatch.getTeamId2();
//			String newTeamId2=newMatch.getTeamId2();
//			if(!"".equals(newTeamId1)&&!"".equals(newTeamId2)&&newTeamId1.equals(newTeamId2)){
//				throw new AppException("额,不能和自己打哦...");
//			}
//			String bisaiId=oldMatch.getBisaiId();
//			exchangeSeat(bisaiId,oldTeamId1,newTeamId1,oldMatch,true);
//			exchangeSeat(bisaiId,oldTeamId2,newTeamId2,oldMatch,false);
//		}
//		/**
//		 * 交换座位(淘汰赛和双败赛)
//		 * @param bisaiId
//		 * @param oldTeam
//		 * @param newTeam
//		 * @param oldMatch
//		 * @param isLeft
//		 * @throws AppException
//		 * @throws Exception
//		 */
//		private void exchangeSeat(String bisaiId,String oldTeam,String newTeam,Match oldMatch,boolean isLeft) throws AppException, Exception{
//			if(oldTeam==null)
//				oldTeam="";
//			if(newTeam==null)
//				newTeam="";
//			if(!"".equals(oldTeam)&&!"".equals(newTeam)&&oldTeam.equals(newTeam)){
//				return;
//			}
//			//1)这个位置没人->有人：入座,入座的人状态变为true;
//			if("".equals(oldTeam)&&!"".equals(newTeam)){
//				Logger.debug(newTeam);
//				BisaiTeamProg btp=new BisaiTeamProg(bisaiId,newTeam).load();
//				//如果新队以前有座位：以前的座位置空，坐进新座位
//				if(btp!=null&&btp.getSitdown()){
//					Match match=findWhereIsUser(bisaiId,btp.getStageseat(),newTeam);
//					match.checkStartWhenExchangeSeat();
//					if(match.getTeamId1().equals(newTeam)){
//						match.putOneUserToTeamId1("");
//					}
//					if(match.getTeamId2().equals(newTeam)){
//						match.putOneUserToTeamId2("");
//					}
//					if(isLeft){
//						oldMatch.putOneUserToTeamId1(newTeam);
//					}
//					if(!isLeft){
//						oldMatch.putOneUserToTeamId2(newTeam);
//					}
//				}
//				if(btp!=null&&!btp.getSitdown()){
//					btp.shiftToSitdown();
//					if(isLeft){
//						oldMatch.putOneUserToTeamId1(newTeam);
//					}
//					if(!isLeft){
//						oldMatch.putOneUserToTeamId2(newTeam);
//					}
//				}
//			}
//			//2)这个位置有人->没人: 出座,出座的人状态变为false;
//			if(!"".equals(oldTeam)&&"".equals(newTeam)){
//				BisaiTeamProg btp=new BisaiTeamProg(bisaiId,oldTeam).load();
//				if(btp!=null){
//					btp.shiftToNotSitdown();
//					if(oldTeam.equals(oldMatch.getTeamId1())){
//						oldMatch.putOneUserToTeamId1("");
//					}
//					if(oldTeam.equals(oldMatch.getTeamId2())){
//						oldMatch.putOneUserToTeamId2("");
//					}
//				}
//			}
//			//3)这个位置有人->另外一人 a:新来的人以前有座位:交换座位
//			                     //b:新来的人以前没有座位:让座位，出座的人状态变为false
//			if(!"".equals(oldTeam)&&!"".equals(newTeam)){
//				BisaiTeamProg oldBtp=new BisaiTeamProg(bisaiId,oldTeam).load();
//				BisaiTeamProg newBtp=new BisaiTeamProg(bisaiId,newTeam).load();
//				
//				if(newBtp.getSitdown()){
//					//旧人坐新位置去
//					Match newMatch=findWhereIsUser(bisaiId,newBtp.getStageseat(),newTeam);
//					newMatch.checkStartWhenExchangeSeat();
//					if(newMatch.getTeamId1().equals(newTeam)){
//						newMatch.putOneUserToTeamId1(oldTeam);
//					}
//					if(newMatch.getTeamId2().equals(newTeam)){
//						newMatch.putOneUserToTeamId2(oldTeam);
//					}
//				}else{
//					oldBtp.shiftToNotSitdown();
//					newBtp.shiftToSitdown();
//				}
//				//让新人坐旧位置
//				if(isLeft){
//					oldMatch.putOneUserToTeamId1(newTeam);
//				}
//				if(!isLeft){
//					oldMatch.putOneUserToTeamId2(newTeam);
//				}
//			}
//		}
//		private Match findWhereIsUser(String bisaiId,int stageseat,String username) throws AppException, Exception{
//			BisaiStage bs=BisaiStageX.getStageByBisaiAndSeat(bisaiId, stageseat);
//			Match match=null;
//			if(BisaiStage.MODE.elimination.equals(bs.getMode())){
//				String filter=" teamId1='"+username+"' or teamId2='"+username+"'";
//				MdoMap mm=new MdoMap();
//				mm.put("bisaiId", bisaiId);
//				mm.put("stageId", bs.getId());
//				mm.put("roundId", "1");
//				MList<Match> elis= this.find(mm, filter);
//				match=elis.getFirst();
//			}
//			if(BisaiStage.MODE.doubleLose.equals(bs.getMode())){
//				String winnerId=BisaiGroupX.getDoubleLoseGroupId(bs.getId(), BisaiGroup.GROUPTYPE.winnerGroup);
//				String filter=" teamId1='"+username+"' or teamId2='"+username+"'";
//				MdoMap mm=new MdoMap();
//				mm.put("bisaiId", bisaiId);
//				mm.put("stageId", bs.getId());
//				mm.put("roundId", "1");
//				mm.put("groupId", winnerId);
//				MList<Match> elis= this.find(mm, filter);
//				match=elis.getFirst();
//			}
//			return match;
//		}
		/**
		 * 循环赛按组查询的比赛赛程
		 * @param groupId
		 * @return  Map<轮次,该轮次的所有比赛>
		 * @throws AppException
		 * @throws Exception
		 */
		public Map<Integer, MList<Match>> findLoopMatchsByGroup(String groupId) throws AppException, Exception {
			MList<Match> matchs=this.find("groupId", groupId);
			Map<Integer,MList<Match>> map=new TreeMap<Integer,MList<Match>>();
			for(Match match:matchs){
				String roundId=match.getRoundId();
				if(roundId!=null&&!"".equals(roundId)){
					Integer iRound=Integer.parseInt(roundId);
					if(map.containsKey(iRound)){
						MList<Match> mlist=map.get(iRound);
						mlist.add(match);
					}else{
						MList<Match> mlists=new MList<Match>();
						mlists.add(match);
						map.put(iRound, mlists);
					}
				}
			}
			return map;
		}
		/**
		 * 按小组位置号查找所有拥有该位置的比赛
		 * @param bisaiId
		 * @param stageId
		 * @param groupId
		 * @param seatNo
		 * @return
		 * @throws AppException
		 * @throws Exception
		 */
		public MList<Match> findLoopMatchsByGroupseat(String bisaiId,String stageId,String groupId,int seatNo) throws AppException, Exception{
			String filter=" (arrangeMatchLeftNode="+seatNo+" or arrangeMatchRightNode="+seatNo+")";
			MdoMap mm=new MdoMap();
			mm.put("bisaiId", bisaiId);
			mm.put("stageId", stageId);
			mm.put("groupId", groupId);
			return this.find(mm, filter);
		}
		
//		public void loopOutSeat(BisaiGroupMember bisaiGroupMember) throws AppException, Exception {
//			dealLoopOutOrInSeat(bisaiGroupMember,"");
//		}
//		public void loopInSeat(BisaiGroupMember bisaiGroupMember) throws AppException, Exception{
//			String username=bisaiGroupMember.getUsername();
//			dealLoopOutOrInSeat(bisaiGroupMember,username);
//		}
//		private void dealLoopOutOrInSeat(BisaiGroupMember bisaiGroupMember,String username) throws AppException, Exception{
//			String bisaiId=bisaiGroupMember.getBisaiId();
//			String stageId=bisaiGroupMember.getStageId();
//			String groupId=bisaiGroupMember.getGroupId();
//			int groupseat=bisaiGroupMember.getGroupseat();
//			MList<Match> matchs=this.findLoopMatchsByGroupseat(bisaiId, stageId, groupId, groupseat);
//			String arrangeSeat=String.valueOf(groupseat);
//			for(Match match:matchs){
//				if(arrangeSeat.equals(match.getArrangeMatchLeftNode())){
//					match.putOneUserToTeamId1(username);
//				}
//				if(arrangeSeat.equals(match.getArrangeMatchRightNode())){
//					match.putOneUserToTeamId2(username);
//				}
//			}
//		}
		/**
		 * 查找2场最近的比赛显示在首页的赛事预告烂上
		 * @param bisaiId
		 * @return
		 * @throws Exception 
		 * @throws AppException 
		 */
		public MList<Match> findTwoRecentlyMatchs(String bisaiId) throws AppException, Exception{
			Calendar current=Calendar.getInstance();
			String cYear=String.valueOf(current.get(Calendar.YEAR));
			int iMonth=current.get(Calendar.MONTH)+1;
			String cMonth=iMonth<10?"0"+String.valueOf(iMonth):String.valueOf(iMonth);
			int iDay=current.get(Calendar.DAY_OF_MONTH);
			String cDay=iDay<10?"0"+String.valueOf(iDay):String.valueOf(iDay);
			int iHour=current.get(Calendar.HOUR_OF_DAY);
			String cHour=iHour<10?"0"+String.valueOf(iHour):String.valueOf(iHour);
			int iMin=current.get(Calendar.MINUTE);
			String cMin=iMin<10?"0"+String.valueOf(iMin):String.valueOf(iMin);
			String curTime=cYear+cMonth+cDay+cHour+cMin;
			String filter=" matchTime>"+curTime;
		    MdoMap mm=new MdoMap();
		    mm.put("bisaiId", bisaiId);
		    MList<Match> matchs=this.find(mm, filter, "matchTime", true, 0, 2);
		    return matchs;
		}
	}

	public void updateToNotSure(MdoMap mm) throws AppException, Exception {
		mm.put("res", Match.RES_ENDNOTSURE);
		this.update(mm);
	}
	
	public void updateToSure(MdoMap mm) throws AppException,Exception{
		mm.put("res", Match.RES_BESURE);
		this.update(mm);
	}

//	public String getReplayName() throws AppException, Exception {
//		String team1="";
//		String team2="";
//		Bisai bisai=new Bisai(bisaiId).load();
//		if(bisai.getSignupSchedule()){
//			team1=getTeamId1DispName();
//			team2=getTeamId2DispName();
//		}else{
//			team1=teamId1;
//			team2=teamId2;
//		}
//		return team1+"_vs_"+team2;
//	}
	
	public String getMatchColor(){
		if(res==RES_NOTSTART){
			return "match-notstart";
		}else if(res==RES_ENDNOTSURE){
			return "match-endnotsure";
		}else if(res==RES_BESURE){
			return "match-besure";
		}else{
			return "";
		}
		
	}

	
	//pic
	private static String DEFAULT_LOGO_URL = "";

	private File getMainOrgLogoFile() {
		String fname = this.id + ".jpg";
		return new File(getFilesDir(), fname);
	}

	private File getFilesDir() {
		return new File(MatchX.fileStoreRootPath + "/match/");
	}

	public String getMainOrgPicUrl() throws AppException, Exception {
		File logoFile = getMainOrgLogoFile();
		if (logoFile.exists())
			return "/51bisaifiles/match/" + this.id + ".jpg?"
					+ UUID.randomUUID().toString();
		else
			return DEFAULT_LOGO_URL;
	}

	public void updateLogoPath(File srcFile) throws AppException, Exception {
		File filesDir = getFilesDir();
		filesDir.mkdirs();
		String fname = this.id + ".jpg";
		File logoFile = new File(filesDir, fname);
		StreamPipe.fileToFile(srcFile, logoFile);
	}

	public void delete() throws AppException, Exception {
		String fname = this.id + ".jpg";
		File logoFile = new File(getFilesDir(), fname);
		logoFile.delete();
		super.delete();
	}
	
	
}
