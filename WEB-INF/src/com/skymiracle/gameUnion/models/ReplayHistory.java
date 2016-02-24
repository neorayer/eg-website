package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.ReplayHistoryX;

import java.util.UUID;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;

public class ReplayHistory extends Mdo<ReplayHistory>{
	
	private String id=UUID.randomUUID().toString();
	private String userorteam;
	private String replayId;
	private String matchType;
	private int downtimes;
	private String level;
	private String createDate = CalendarUtil.getLocalDate();
	
	private String createDateTime = CalendarUtil.getLocalDateTime();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getReplayId() {
		return replayId;
	}
	public void setReplayId(String replayId) {
		this.replayId = replayId;
	}
	public int getDowntimes() {
		return downtimes;
	}
	public void setDowntimes(int downtimes) {
		this.downtimes = downtimes;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public ReplayHistory() {
		super(ReplayHistoryX);
	}
	public ReplayHistory(String id){
		this();
		this.id=id;
	}
	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_replayhistory";
	}
	
	public String getUserorteam() {
		return userorteam;
	}
	public void setUserorteam(String userorteam) {
		this.userorteam = userorteam;
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
	public Replay getReplay(){
		Replay replay=null;
		try{
			replay=new Replay(replayId).load();
		}catch (Exception e) {
		}
		return replay;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public static class X extends Mdo_X<ReplayHistory>{
		
		public MList<ReplayHistory> getTop10ReplayByUserOrTeam(String userorteam) throws AppException, Exception{
			return this.find("userorteam,createDateTime-:0,10", userorteam);
		}
	}
}
