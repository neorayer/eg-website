package com.skymiracle.gameUnion.exception;

public class RoomAddrExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4570266720753219938L;
	int roomAddr;

	public RoomAddrExistException(int roomAddr) {
		this.roomAddr = roomAddr;
	}

}
