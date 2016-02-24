package com.skymiracle.gameUnion.models;

import java.util.LinkedList;
import java.util.List;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class Game extends Mdo<Game>{

	public static class X extends Mdo_X<Game> {
		public void initData() throws AppException, Exception {
			initGamesData();
			initGameZonesData();
			initGameRoomsData();
		}

		private void initGamesData() throws AppException, Exception {
			GameX.findAll().delete();

			MList<Game> games = new MList<Game>();
			{
				Game game = new Game();
				game.setGameId("war3rpg");
				game.setName("魔兽争霸RPG");
				game.setGameType(Game.TYPE_JJ);
				games.add(game);
			}

			{
				Game game = new Game();
				game.setGameId("cs15");
				game.setName("反恐精英1.5");
				game.setGameType(Game.TYPE_JJ);
				games.add(game);
			}
			{
				Game game = new Game();
				game.setGameId("war3");
				game.setName("魔兽争霸III");
				game.setGameType(Game.TYPE_JJ);
				games.add(game);
			}
			{
				Game game = new Game();
				game.setGameId("cs16");
				game.setName("反恐精英1.6");
				game.setGameType(Game.TYPE_JJ);
				games.add(game);
			}
			{
				Game game = new Game();
				game.setGameId("starcraft");
				game.setName("星际争霸");
				game.setGameType(Game.TYPE_JJ);
				games.add(game);
			}

			games.create();
		}

		public void initGameZonesData() throws AppException, Exception {
			GameZoneX.findAll().delete();

			MList<GameZone> gameZones = new MList<GameZone>();

			String gameId = "cs15";
			{
				GameZone gameZone = new GameZone( );
				gameZone.setGameId(gameId);
				gameZone.setZoneId("cs15-free");
				gameZone.setName("自由区");
				gameZones.add(gameZone);
			}

			gameId = "cs16";
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("cs16-free");
				gameZone.setName("自由区");
				gameZones.add(gameZone);
			}

			gameId = "starcraft";
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("starcraft-free");
				gameZone.setName("综合区");
				gameZones.add(gameZone);
			}

			gameId = "war3";
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("war3-free");
				gameZone.setName("自由区");
				gameZones.add(gameZone);
			}

			gameId = "war3rpg";
			int sortNum = 10;
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("war3rpg-z3");
				gameZone.setName("真三国无双");
				gameZone.setSortNum(sortNum ++);
				gameZones.add(gameZone);
			}
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("war3rpg-dota");
				gameZone.setName("DotA");
				gameZone.setSortNum(sortNum ++);
				gameZones.add(gameZone);
			}
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("war3rpg-3corc");
				gameZone.setName("3CORC");
				gameZone.setSortNum(sortNum ++);
				gameZones.add(gameZone);
			}
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("war3rpg-ch3c");
				gameZone.setName("澄海3C");
				gameZone.setSortNum(sortNum ++);
				gameZones.add(gameZone);
			}
			{
				GameZone gameZone = new GameZone();
				gameZone.setGameId(gameId);
				gameZone.setZoneId("war3rpg-free");
				gameZone.setName("RPG综合");
				gameZone.setSortNum(sortNum ++);
				gameZones.add(gameZone);
			}

			gameZones.create();
		}

		public void initGameRoomsData() throws AppException, Exception  {
			GameRoomX.findAll().delete();

			String host = "10.1.1.221";
			MList<GameRoom> gameRooms = new MList<GameRoom>();
			{
				String gameId = "cs15";
				String zoneId = "cs15-free";
				for (int i = 0; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("cs15-free-nm-room-" + i);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("普通房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(false);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(27015);
					gameRoom.setUdpdigPortEnd(27024);
					gameRooms.add(gameRoom);
				}
			}
			{
				String gameId = "cs16";
				String zoneId = "cs16-free";
				for (int i = 0; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("cs16-free-nm-room-" + i);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("普通房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(false);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(27015);
					gameRoom.setUdpdigPortEnd(27024);
					gameRooms.add(gameRoom);
				}
			}

			{
				String gameId = "war3";
				String zoneId = "war3-free";
				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3-120e-nm-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("Warcraft 1.20 KO练习场" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}
				for (int i = 1; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3-120e-amh-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("Warcraft 1.20 KO专业场(反MH)" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRoom.setIsAntiMH(true);
					gameRooms.add(gameRoom);
				}
			}
			{
				String gameId = "war3rpg";
				String zoneId = "war3rpg-z3";

				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-z3-nm-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("真三自由房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}
				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-z3-amh-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("真三反MH专房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRoom.setIsAntiMH(true);
					gameRooms.add(gameRoom);
				}
			}
			{
				String gameId = "war3rpg";
				String zoneId = "war3rpg-dota";

				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-dota-nm-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("DotA自由房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}
				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-dota-amh-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("DotA反MH专房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRoom.setIsAntiMH(true);
					gameRooms.add(gameRoom);
				}
			}
			{
				String gameId = "war3rpg";
				String zoneId = "war3rpg-ch3c";

				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-ch3c-nm-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("澄海3C自由房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRoom.setIsAntiMH(true);
					gameRooms.add(gameRoom);
				}
				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-ch3c-amh-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("澄海3C反MH转房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRoom.setIsAntiMH(true);
					gameRooms.add(gameRoom);
				}
			}
			{
				String gameId = "war3rpg";
				String zoneId = "war3rpg-free";

				String[] names = new String[] { "暗黑2守护迪卡凯恩", "信长之野望", "忍者村大战",
						"陆小凤传奇", "仙之侠道", "天地劫", "无序之痕", "歌之守护者", "Boom海战", "超越极限" };
				int id = 0;
				for (String name : names) {
					for (int i = 1; i < 6; i++) {
						id++;
						GameRoom gameRoom = new GameRoom();
						gameRoom.setGameId(gameId);
						gameRoom.setZoneId(zoneId);
						gameRoom.setRoomId("war3rpg-free-nm-room-" + id);
						gameRoom.setPipeHost(host);
						gameRoom.setMaxGamerCount(250);
						String roomName = String.format("[%02d] %s %d 房", id, name,
								i);
						gameRoom.setName(roomName);
						gameRoom.setPipeHost(host);
						gameRoom.setPipeTcpPort(8000);
						gameRoom.setPipeUdpPort(8000);
						gameRoom.setHasTcp(true);
						gameRoom.setNetmaskC(3);
						gameRoom.setUdpdigPortBgn(6112);
						gameRoom.setUdpdigPortEnd(6112);
						gameRooms.add(gameRoom);
					}
				}
				for (int i = 1; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-free-meimei-others-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("无限自由RPG " + i + " 房");
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}
			}
			{
				String gameId = "war3rpg";
				String zoneId = "war3rpg-3corc";
				for (int i = 1; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-3corc-nm-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("3CORC自由房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}

				for (int i = 1; i < 5; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-3corc-cl-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("3CORC策略房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}

				for (int i = 1; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("war3rpg-3corc-amh-room-" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("3CORC反MH专房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(true);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6112);
					gameRoom.setUdpdigPortEnd(6112);
					gameRoom.setIsAntiMH(true);
					gameRooms.add(gameRoom);
				}
			}

			{
				String gameId = "starcraft";
				String zoneId = "starcraft-free";
				for (int i = 0; i < 10; i++) {
					GameRoom gameRoom = new GameRoom();
					gameRoom.setGameId(gameId);
					gameRoom.setZoneId(zoneId);
					gameRoom.setRoomId("starcraft-free-nm-room-" + i);
					gameRoom.setMaxGamerCount(250);
					gameRoom.setName("星际1.13普通房" + i);
					gameRoom.setPipeHost(host);
					gameRoom.setPipeTcpPort(8000);
					gameRoom.setPipeUdpPort(8000);
					gameRoom.setHasTcp(false);
					gameRoom.setNetmaskC(3);
					gameRoom.setUdpdigPortBgn(6111);
					gameRoom.setUdpdigPortEnd(6112);
					gameRooms.add(gameRoom);
				}

			}

			int roomAddr = 0x010000;
			for (GameRoom gr : gameRooms) {
				gr.setRoomAddr(roomAddr);
				roomAddr++;
			}

			gameRooms.create();
		}
	}
	public static final int TYPE_EG = 0; //锟斤拷锟接撅拷锟斤拷
	public static final int TYPE_JJ = 0; //锟街伙拷
	public static final int TYPE_QP = 0; //锟斤拷锟斤拷
	
	private String gameId; //锟斤拷戏ID
	
	private String name; //锟斤拷戏锟斤拷锟�

	private int gameType; //锟斤拷戏锟斤拷锟斤拷

	public Game() {
		super(GameX);
	}
	
	public Game(String gameId) {
		this();
		setGameId(gameId);
	}
	public Game(String gameId, String name, int gameType) {
		this();
		this.gameId = gameId;
		this.name = name;
		this.gameType = gameType;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String[] keyNames() {
		// TODO Auto-generated method stub
		return new String[] { "gameid" };
	}


	
	@Override
	public String table() {
		// TODO Auto-generated method stub
		return new String("tb_game");
	}
	
	public MList<GameZone> getGameZones() throws AppException, Exception {
		return GameZoneX.find("gameid", this.gameId);
	}
	
	
}
