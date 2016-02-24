package com.skymiracle.gameUnion.models.test;

import junit.framework.TestCase;
import com.skymiracle.gameUnion.models.*;
import com.skymiracle.sor.exception.AppException;

public class TestFightLog extends TestCase {

	public void testFightLog() throws AppException, Exception {
		User u1 = new User("zhourui").load();
		User u2 = new User("test22").load();
		User u3 = new User("jiayan").load();
		User u4 = new User("diablofrog").load();

		FightLog fl = new FightLog();
		GameRoom room = new GameRoom("war3rpg-z3-nm-room-1").load();
		fl.setRoomId(room.getRoomId());
		fl.setIsResSure(true);
		fl.setHostGamers("zhourui:jiayan");
		fl.setOtherGamers("test22:diablofrog");
		fl.setGameId(room.getGameId());
		fl.setZoneId(room.getZoneId());
		fl.setGameSeconds(20);
		fl.setRes(1);

		
		Userpoint up1= u1.getPoint(room.getZoneId());
		Userpoint up2= u2.getPoint(room.getZoneId());
		Userpoint up3= u3.getPoint(room.getZoneId());
		Userpoint up4= u4.getPoint(room.getZoneId());
		
		System.out.println(up1);
		System.out.println(up2);
		System.out.println(up3);
		System.out.println(up4);
		
		
		fl.create();
		
		Userpoint n_up1= u1.getPoint(room.getZoneId());
		Userpoint n_up2= u2.getPoint(room.getZoneId());
		Userpoint n_up3= u3.getPoint(room.getZoneId());
		Userpoint n_up4= u4.getPoint(room.getZoneId());
		
		System.out.println(n_up1);
		System.out.println(n_up2);
		System.out.println(n_up3);
		System.out.println(n_up4);
		
		
		//比较 user win and lose
		assertEquals(n_up1.getWin(), up1.getWin()  + 1);
		assertEquals(n_up1.getLose(), up1.getLose());

		assertEquals(n_up2.getWin() , up2.getWin());
		assertEquals(n_up2.getLose(), up2.getLose()  + 1);
		
		//比较 user point
		assertTrue(n_up1.getPoint() > up1.getPoint());
		if (up2.getPoint() == 0)
			assertEquals(0, n_up2.getPoint());
		else
		assertTrue(n_up2.getPoint() < up2.getPoint());
		
	}
}
