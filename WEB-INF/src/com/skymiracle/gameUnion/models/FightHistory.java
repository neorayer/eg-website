package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.FightHistoryX;

import java.util.UUID;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;


public class FightHistory extends Mdo<FightHistory>{
	
	private String id=UUID.randomUUID().toString();
	
	private String userorteam;
	
	@Auto(Auto.Type.CreateDate)
	private String createDate;
	
	@Auto(Auto.Type.CreateDateTime)
	private String cerateDateTime;
	
	private String fightLogId;
	
	//是否获胜
	private boolean iswin;
	
	//产生积分
	private int score;
	
	
	public boolean getIswin() {
		return iswin;
	}

	public void setIswin(boolean iswin) {
		this.iswin = iswin;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getFightLogId() {
		return fightLogId;
	}

	public void setFightLogId(String fightLogId) {
		this.fightLogId = fightLogId;
	}

	@Override
	public String[] keyNames() {
		return new String[] {"id"};
	}

	@Override
	public String table() {
		return "tb_fighthistory";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCerateDateTime() {
		return cerateDateTime;
	}

	public void setCerateDateTime(String cerateDateTime) {
		this.cerateDateTime = cerateDateTime;
	}

	
	public FightHistory() {
		super(FightHistoryX);
	}
	public static class X extends Mdo_X<FightHistory> {

	}
	
	public FightLog getFightLog() throws AppException, Exception{
		return new FightLog(fightLogId).load();
	}
}
