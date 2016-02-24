package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.TeampointX;

import java.text.NumberFormat;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

public class Teampoint extends Mdo<Teampoint>{
	
	private String teamid;
	private String gametypeid;
	private int win;
	private int lose;
	private int point;
	private int rank=1;
	



	public String getTeamid() {
		return teamid;
	}



	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}



	public String getGametypeid() {
		return gametypeid;
	}



	public void setGametypeid(String gametypeid) {
		this.gametypeid = gametypeid;
	}



	public int getWin() {
		return win;
	}



	public void setWin(int win) {
		this.win = win;
	}



	public int getLose() {
		return lose;
	}



	public void setLose(int lose) {
		this.lose = lose;
	}



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}



	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Teampoint() {
		super(TeampointX);
	}
	
	public Teampoint(String teamid,String gametypeid){
		this();
		this.teamid=teamid;
		this.gametypeid=gametypeid;
	}
	
	@Override
	public String[] keyNames() {
		return new String[] { "teamid","gametypeid"};
	}

	@Override
	public String table() {
		return "tb_egteampoint";
	}
	
	public static class X extends Mdo_X<Teampoint> {
		
	}
	public String getRate(){
		return getWinrate(win,lose);
	}
	public static String getWinrate(int win,int lose){
		String strRate="";
		if(win==0){
       	  strRate="0%";
        }
		if(win!=0&&lose==0){
       	   strRate="100%";
       	}
        if(win!=0&&lose!=0){
       	  double rate=Integer.valueOf(win).doubleValue()/(win+lose);
          double dd=(double) (Math.round(rate*100)/100.0);
           NumberFormat nf=NumberFormat.getIntegerInstance();
           strRate=nf.format(dd*100)+"%";
        }
       return strRate;    
	}
	public GameZone getGameZone () throws AppException, Exception {
		return new GameZone(gametypeid).load();
	}
}
