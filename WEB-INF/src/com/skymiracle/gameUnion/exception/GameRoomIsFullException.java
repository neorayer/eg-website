package com.skymiracle.gameUnion.exception;

import com.skymiracle.gameUnion.models.GameRoom;


public class GameRoomIsFullException extends GameHallException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 312146005757433541L;
	public GameRoom room;

	public GameRoomIsFullException(GameRoom room) {
		super("房间人数已满。");
		this.room = room;
	}

}
