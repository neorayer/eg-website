package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.ReputationX;

import java.util.UUID;

import com.skymiracle.gameUnion.models.bisai.Bisai;
import com.skymiracle.gameUnion.models.bisai.Match;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;




public class Reputation extends AbsMdo<Reputation>{
	
	
	private String id=UUID.randomUUID().toString();
	//此user可能是队伍id，也可能是username
	private String username;
	//好评中评差评
	private Rtype type;
	//附加评论
	private String comment;
	//来自某人
	private String fromuser;
	//辩解
	private String rexplain;
	//来自比赛
	private String bisaiid;
	//来自比赛场次
	private String matchid;
	
	//是否需要回评 
	private boolean needRecall=true;
	
	//是否已经回评
	private boolean recalled=false;
	
	private long currentTimeMill=System.currentTimeMillis();
	
	private String createDateTime= CalendarUtil.getLocalDateTime();
	private String createDate=CalendarUtil.getLocalDate();
	
	public static enum Rtype {
		good,medium,bad
	};
	public Reputation(String id){
		this();
		this.id=id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Rtype getType() {
		return type;
	}
	public void setType(Rtype type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Reputation() {
		super(ReputationX);
	}
	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_reputation";
	}
	
	

	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRexplain() {
		return rexplain;
	}
	public void setRexplain(String rexplain) {
		this.rexplain = rexplain;
	}
	public void setTypeByString(String type){
		if("1".equals(type)){
			this.setType(Reputation.Rtype.good);
		}
		if("0".equals(type)){
			this.setType(Reputation.Rtype.medium);
		}
		if("-1".equals(type)){
			this.setType(Reputation.Rtype.bad);
		}
	}
	public String getBisaiid() {
		return bisaiid;
	}
	public void setBisaiid(String bisaiid) {
		this.bisaiid = bisaiid;
	}
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
//	public void updateUserRep(Bisai bisai,String s) throws AppException, Exception {
//		if(bisai.getSolo()){
//			User user=new User(username).load();
//			int reputation=user.getReputation();
//			if(isGood(s)){
//				user.update("reputation",reputation+1);
//			}else if(isBad(s)){
//				user.update("reputation",reputation-1);
//			}else{
//				return;
//			}
//		}
//	}
	public boolean isGood(String type){
		return Reputation.Rtype.good.equals(type);
	}
	public boolean isMedium(String type){
		return Reputation.Rtype.medium.equals(type);
	}
	public boolean isBad(String type){
		return Reputation.Rtype.bad.equals(type);
	}



	public boolean getNeedRecall() {
		return needRecall;
	}
	public void setNeedRecall(boolean needRecall) {
		this.needRecall = needRecall;
	}
	public boolean getRecalled() {
		return recalled;
	}
	public void setRecalled(boolean recalled) {
		this.recalled = recalled;
	}
	

	public long getCurrentTimeMill() {
		return currentTimeMill;
	}
	public void setCurrentTimeMill(long currentTimeMill) {
		this.currentTimeMill = currentTimeMill;
	}
	
	public User getfromu() throws AppException,Exception{
		return new User(fromuser).load();
	}
	public Match getMatch(){
		Match match=null;
		try {
			match= new Match(matchid).load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return match;
	}
	public Bisai getBisai(){
		Bisai bisai=null;
		try {
			bisai=new Bisai(bisaiid).load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bisai;
	}
	//public 
	public static class X extends Mdo_X<Reputation>{
		
		public MList<Reputation> getAllNotRecalledRep(long disTime) throws AppException, Exception {
			MdoMap mm=new MdoMap();
			mm.put("needRecall", true);
			mm.put("recalled", false);
			String filter=" currentTimeMill<="+disTime;
		    MList<Reputation> reps=this.find(mm, filter);
			return reps;
		}

		public long findGoodByUser(String username) throws AppException, Exception {
			return this.count("type,username", Reputation.Rtype.good,username);
		}
		
		public long findMediumByUser(String username) throws AppException,Exception{
			return this.count("type,username", Reputation.Rtype.medium,username);
		}
		public long findBadByUser(String username) throws AppException,Exception{
			return this.count("type,username", Reputation.Rtype.bad,username);
		}
	}
	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
