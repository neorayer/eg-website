package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class GameZone extends Mdo<GameZone>{

	public static class X extends Mdo_X<GameZone> {
		
	}
	
	private String zoneId;
	
	private String gameId;
	
	private String name; 

	private int sortNum = 0;
	
	public GameZone() {
		super(GameZoneX);
	}

	public GameZone(String zoneid) {
		this();
		setZoneId(zoneid);
	}
	
	
	public GameZone(String zoneId, String gameId, String name, int sortNum) {
		this();
		this.zoneId = zoneId;
		this.gameId = gameId;
		this.name = name;
		this.sortNum = sortNum;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "zoneid" };
	}


	@Override
	public String table() {
		// TODO Auto-generated method stub
		return new String("tb_gamezone");
	}
	
	public Game getGame() throws AppException, Exception {
		try {
			return new Game(gameId).load();
		}catch (NotExistException e) {
			return null;
		}
	}

}
