package com.skymiracle.gameUnion.models;

import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.team.TeamMember;
import com.skymiracle.sor.exception.AppException;
import static com.skymiracle.gameUnion.Singletons.*;
import junit.framework.TestCase;

public class Test extends TestCase {

	public void testUsers() throws AppException, Exception {
		for (int i=0; i<100; i++) {
			User u = new User("user" + i);
			u.setNickname("USER-" + i);
			u.setPassword("111111");
			u.createIfNotExist();
			u.enterRoom("war3rpg-z3-nm-room-1", 0, false);
		}
		
	}
	
	public void _testTeam() throws AppException, Exception {
		
		
		User leader = new User("tianliang");
		leader.delete();
		leader.create();
		
		GameTeamX.findAll().delete();
		GameTeam team = new GameTeam();
		team.setTeamName("KO战队");
		team.setCreator(leader.getUsername());
		team.create();
		
		//战队找出来啦
		assertNotNull(leader.getTeam().getId());

		//角色为队长
		assertTrue(leader.isTeamLeader());

		//拥有权限
		assertTrue(leader.getHasPower(new Authority("vi_member")));
		assertTrue(leader.getHasPower(new Authority("approval_apply")));
		assertTrue(leader.getHasPower(new Authority("group_msg")));
		assertTrue(leader.getHasPower(new Authority("manage_role")));
		
		/* 未假如任何战队的老兄 */
		User u = new User("nobody");
		u.delete();
		u.create();
		
		//战队找出来啦
		assertNull(u.getTeam().getId());

		//角色为队长
		assertFalse(u.isTeamLeader());

		//普通用户有vi_member权限
		assertTrue(u.getHasPower(new Authority("vi_member")));
		
		//普通用户无vi_member权限
		assertFalse(u.getHasPower(new Authority("approval_member")));
		//普通用户无group_msg权限
		assertFalse(u.getHasPower(new Authority("group_msg")));
		//普通用户无manage_post权限
		assertFalse(u.getHasPower(new Authority("manage_post")));
		
		
		/* 加入战队啦 */
		TeamMember tm = new TeamMember(team.getId(), u.getUsername());
		tm.userApply();
		
		//战队成员数 
		assertEquals(team.getMemberCount(), 1);
		
		assertTrue(tm.exists());
		
		assertFalse(tm.getPassed());

		assertEquals(tm.getUser().getUsername(), "nobody");
		
		assertEquals(tm.getTeam().getId(), team.getId());
		
		//审批通过
		new TeamMember(team.getId(), u.getUsername()).passApply(leader);
		
		//这里一定要load一次，
		assertEquals(2, team.load().getMemberCount());
		
		
	}
}
