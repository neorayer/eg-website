package com.skymiracle.gameUnion;
import static com.skymiracle.gameUnion.Singletons.storeEg;

import com.skymiracle.gameUnion.models.Comment;
import com.skymiracle.gameUnion.models.FreshMsg;
import com.skymiracle.gameUnion.models.GameMap;
import com.skymiracle.gameUnion.models.RootAdmin;
import com.skymiracle.gameUnion.models.Userpoint;
import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.sor.exception.AppException;

public class InitDB {

	public static void main(String[] args) throws AppException, Exception {

		storeEg.createTableForce(Comment.class, true);
		
	//storeEg.createTableForce(Album.class, true);
//		
//		storeEg.createTableForce(Photo.class, true);
//		
//		
//		storeEg.createTableForce(IpLoc.class, true);
//		storeEg.createTableForce(UserFriend.class, true);
//		
//		storeEg.createTableForce(FriendSetting.class, true);
//		storeEg.createTableForce(Bisai.class, true);	
//		storeEg.createTableForce(Match.class,true);
//		
//		storeEg.createTableForce(LtMsgReqAddFriend.class,true);
//			
//		storeEg.createTableForce(Game.class,true);
//		storeEg.createTableForce(GameZone.class,true);
//		storeEg.createTableForce(GameRoom.class,true);
//		GameX.initData();
//		
//		storeEg.createTableForce(Org.class,true);
//		storeEg.createTableForce(Hall.class,true);
//		//storeEg.createTableForce(OrgAdmin.class,true);
//		storeEg.createTableForce(RootAdmin.class,true);
//		//storeEg.createTableForce(HallMenuItem.class,true);
//
//		storeEg.createTableForce(CurVersion.class,true);
//		storeEg.createTableForce(ServerVersion.class,true);
		
		
		
		//eg2
		//storeEg.createTableForce(TeamProInfo.class,true);
		//storeEg.createTableForce(UserProInfo.class,true);
		//storeEg.createTableForce(Article.class,true);
		//storeEg.createTableForce(Attachment.class,true);
		//storeEg.createTableForce(Photo.class,true);
		//storeEg.createTableForce(Userpoint.class,true);
		//storeEg.createTableForce(Teampoint.class,true);
		
		
		
		//storeEg.createTableForce(UserEgHistory.class,true);
		//storeEg.createTableForce(UserRelation.class,true);
		
		//storeEg.createTableForce(TeamBbs.class,true);
		//storeEg.createTableForce(WarZone.class,true);
		
		//storeEg.createTableForce(UserMsg.class,true);
		
		//storeEg.createTableForce(TeamMsg.class,true);
		//storeEg.createTableForce(GameMap.class,true);
		
		//System.out.println(storeEg.getCreateTableSQL(UserFriend.class));
		/*RootAdmin admin = new RootAdmin();
		admin.setUsername("admin");
		admin.setPassword("111111");
		admin.createIfNotExist();*/
		
	}

}
