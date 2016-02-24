package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class GameRoom extends Mdo<GameRoom> {
	public static class X extends Mdo_X<GameRoom> {

		public GameRoom getRoomByAddr(int roomAddr) throws AppException,
				Exception {
			MList<GameRoom> rooms = find("roomAddr", roomAddr);
			if (rooms.size() == 0)
				throw new AppException("服务器上没有这个房间。");
			return rooms.getFirst();
		}
	}

	protected String roomId;

	protected String name;

	protected String gameId;

	protected String zoneId;

	protected int maxGamerCount;

	protected String pipeHost = "";

	protected int pipeTcpPort = 6666;

	protected int pipeUdpPort = 6666;

	protected boolean hasTcp = false;

	protected int udpdigPortBgn = 27015;

	protected int udpdigPortEnd = 27024;

	protected int netmaskC = 3;

	protected int roomAddr;

	protected boolean isAntiMH = false;

	public GameRoom() {
		super(GameRoomX);
	}

	public GameRoom(String roomId) {
		this();
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(int roomAddr) {
		this.roomAddr = roomAddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getMaxGamerCount() {
		return maxGamerCount;
	}

	public void setMaxGamerCount(int maxGamerCount) {
		this.maxGamerCount = maxGamerCount;
	}

	public String getPipeHost() {
		return pipeHost;
	}

	public void setPipeHost(String pipeHost) {
		this.pipeHost = pipeHost;
	}

	public int getPipeTcpPort() {
		return pipeTcpPort;
	}

	public void setPipeTcpPort(int pipeTcpPort) {
		this.pipeTcpPort = pipeTcpPort;
	}

	public int getPipeUdpPort() {
		return pipeUdpPort;
	}

	public void setPipeUdpPort(int pipeUdpPort) {
		this.pipeUdpPort = pipeUdpPort;
	}

	public boolean getHasTcp() {
		return hasTcp;
	}

	public void setHasTcp(boolean hasTcp) {
		this.hasTcp = hasTcp;
	}

	public int getUdpdigPortBgn() {
		return udpdigPortBgn;
	}

	public void setUdpdigPortBgn(int udpdigPortBgn) {
		this.udpdigPortBgn = udpdigPortBgn;
	}

	public int getUdpdigPortEnd() {
		return udpdigPortEnd;
	}

	public void setUdpdigPortEnd(int udpdigPortEnd) {
		this.udpdigPortEnd = udpdigPortEnd;
	}

	public int getNetmaskC() {
		return netmaskC;
	}

	public void setNetmaskC(int netmaskC) {
		this.netmaskC = netmaskC;
	}

	public int getGameRoomAddr() {
		return roomAddr;
	}

	public void setGameRoomAddr(int roomAddr) {
		this.roomAddr = roomAddr;
	}

	public boolean getIsAntiMH() {
		return isAntiMH;
	}

	public void setIsAntiMH(boolean isAntiMH) {
		this.isAntiMH = isAntiMH;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "roomid" };
	}

	@Override
	public String table() {
		return "tb_gameroom";
	}

	public static void main(String[] args) throws AppException, Exception {
		System.out.println(GameRoomX.findAll());
	}

	public MList<User> getUsers() throws AppException, Exception {
		return UserX.find("roomId", this.roomId);
	}

	public int getCurUserCount() throws AppException, Exception {
		// TODO: 假数据
		if (getRoomId().equals("war3rpg-z3-amh-room-1"))
			return 250;

		return (int) UserX.count("roomId", this.roomId);
	}

	public boolean isFull() throws AppException, Exception {
		// TODO: 假数据
		if (getRoomId().equals("war3rpg-z3-amh-room-1"))
			return true;

		int curUserCount = getCurUserCount();
		boolean isFull = curUserCount >= getMaxGamerCount();
		return isFull;
	}

	public synchronized void allocVipAddr(User user) throws AppException,
			Exception {
		for (int i = 1; i < maxGamerCount + 1; i++) {
			int vIpAddr = roomAddr * 0x100 + i;
			if (UserX.count("vIpAddr", vIpAddr) <= 0) {
				user.update("vIpAddr", vIpAddr);
				return;
			}
		}
		throw new AppException("服务器无法向该用户分配虚拟IP");
	}

	public void checkTimeout() throws AppException, Exception {
		MList<User> users = getUsers();
		for(User u: users) {
			u.checkTimeout();
		}
	}

}
