package com.skymiracle.gameUnion;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.skymiracle.gameUnion.models.Album;
import com.skymiracle.gameUnion.models.Article;
import com.skymiracle.gameUnion.models.Attachment;
import com.skymiracle.gameUnion.models.Authority;
import com.skymiracle.gameUnion.models.Comment;
import com.skymiracle.gameUnion.models.FightHistory;
import com.skymiracle.gameUnion.models.Duel;
import com.skymiracle.gameUnion.models.FightLog;
import com.skymiracle.gameUnion.models.FreshMsg;
import com.skymiracle.gameUnion.models.FriendSetting;
import com.skymiracle.gameUnion.models.Game;
import com.skymiracle.gameUnion.models.GameMap;
import com.skymiracle.gameUnion.models.GameRoom;
import com.skymiracle.gameUnion.models.GameZone;
import com.skymiracle.gameUnion.models.IpLoc;
import com.skymiracle.gameUnion.models.MatchType;
import com.skymiracle.gameUnion.models.MessageBoard;
import com.skymiracle.gameUnion.models.Photo;
import com.skymiracle.gameUnion.models.Replay;
import com.skymiracle.gameUnion.models.ReplayHistory;
import com.skymiracle.gameUnion.models.Reputation;
import com.skymiracle.gameUnion.models.RootAdmin;
import com.skymiracle.gameUnion.models.Sequence;
import com.skymiracle.gameUnion.models.SignUpUser;
import com.skymiracle.gameUnion.models.Teampoint;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.UserFriend;
import com.skymiracle.gameUnion.models.Userpoint;
import com.skymiracle.gameUnion.models.WarZone;
import com.skymiracle.gameUnion.models.bisai.Bisai;
import com.skymiracle.gameUnion.models.bisai.Match;
import com.skymiracle.gameUnion.models.bisai.SignUpTeamMember;
import com.skymiracle.gameUnion.models.hall.Hall;
import com.skymiracle.gameUnion.models.hall.HallMenuItem;
import com.skymiracle.gameUnion.models.msg.CommentMsg;
import com.skymiracle.gameUnion.models.msg.LtMsgReqAddFriend;
import com.skymiracle.gameUnion.models.msg.RtMsg;
import com.skymiracle.gameUnion.models.msg.TeamMsg;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.gameUnion.models.org.Org;
import com.skymiracle.gameUnion.models.org.OrgAdmin;
import com.skymiracle.gameUnion.models.post.PubPost;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.gameUnion.models.team.TeamBbs;
import com.skymiracle.gameUnion.models.team.TeamMember;
import com.skymiracle.gameUnion.models.team.TeamProInfo;
import com.skymiracle.gameUnion.models.team.TeamRole;
import com.skymiracle.gameUnion.models.team.TeamRolePower;
import com.skymiracle.gameUnion.models.user.UserBattleFriend;
import com.skymiracle.gameUnion.models.user.UserCaller;
import com.skymiracle.gameUnion.models.user.UserEgHistory;
import com.skymiracle.gameUnion.models.user.UserProInfo;
import com.skymiracle.gameUnion.models.version.CurVersion;
import com.skymiracle.gameUnion.models.version.ServerVersion;
import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.RdbmsStore;

public class Singletons {

	public static RdbmsStore storeEg, storeBisai;
	public static XmlBeanFactory beanFactory;
	static {
		Logger.setLevel(Logger.LEVEL_DEBUG);

		String rootPath = Singletons.class.getPackage().getName().replace('.', '/');
		Resource resource = new ClassPathResource(rootPath + "/spring.xml");
		beanFactory = new XmlBeanFactory(resource);

		PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource(rootPath + "/setting.properties"));
		cfg.postProcessBeanFactory(beanFactory);

		storeEg = (RdbmsStore) beanFactory.getBean("StoreEg");
		storeBisai = (RdbmsStore) beanFactory.getBean("StoreBisai");
	}

	public static Game.X GameX = (Game.X) beanFactory.getBean("GameX");
	public static GameZone.X GameZoneX = (GameZone.X) beanFactory
			.getBean("GameZoneX");
	public static GameRoom.X GameRoomX = (GameRoom.X) beanFactory
			.getBean("GameRoomX");

	public static GameTeam.X GameTeamX = (GameTeam.X) beanFactory
			.getBean("GameTeamX");
	public static User.X UserX = (User.X) beanFactory.getBean("UserX");
	public static TeamMember.X TeamMemberX = (TeamMember.X) beanFactory
			.getBean("TeamMemberX");
	public static TeamRole.X TeamRoleX = (TeamRole.X) beanFactory
			.getBean("TeamRoleX");

	public static Bisai.X BisaiX = (Bisai.X) beanFactory.getBean("BisaiX");
	public static SignUpUser.X SignUpUserX = (SignUpUser.X) beanFactory
			.getBean("SignUpUserX");

	public static Sequence.X SequenceX = (Sequence.X) beanFactory
			.getBean("SequenceX");

	public static TeamMsg.X TeamMsgX = (TeamMsg.X) beanFactory.getBean("TeamMsgX");
public static UserMsg.X UserMsgX = (UserMsg.X) beanFactory.getBean("UserMsgX");
	public static Album.X AlbumX = (Album.X) beanFactory.getBean("AlbumX");
	public static UserFriend.X UserFriendX = (UserFriend.X) beanFactory
			.getBean("UserFriendX");
	public static FriendSetting.X FriendSettingX = (FriendSetting.X) beanFactory
			.getBean("FriendSettingX");

	public static Authority.X AuthorityX = (Authority.X) beanFactory
			.getBean("AuthorityX");
	public static TeamRolePower.X TeamRolePowerX = (TeamRolePower.X) beanFactory
			.getBean("TeamRolePowerX");

	public static RtMsg.X<?> RtMsgX = (RtMsg.X<?>) beanFactory
			.getBean("RtMsgX");
	public static IpLoc.X IpLocX = (IpLoc.X) beanFactory.getBean("IpLocX");
	public static Org.X OrgX = (Org.X) beanFactory.getBean("OrgX");
	public static OrgAdmin.X OrgAdminX = (OrgAdmin.X) beanFactory
			.getBean("OrgAdminX");

	public static RootAdmin.X RootAdminX = (RootAdmin.X) beanFactory
			.getBean("RootAdminX");

	public static Hall.X HallX = (Hall.X) beanFactory.getBean("HallX");
	public static HallMenuItem.X HallMenuItemX = (HallMenuItem.X) beanFactory
			.getBean("HallMenuItemX");

	public static FightLog.X FightLogX = (FightLog.X) beanFactory
			.getBean("FightLogX");

	public static GameMap.X  GameMapX = (GameMap.X) beanFactory.getBean("GameMapX");

	public static LtMsgReqAddFriend.X LtMsgReqAddFriendX = (LtMsgReqAddFriend.X) beanFactory
			.getBean("LtMsgReqAddFriendX");

	public static PubPost.X PubPostX = (PubPost.X) beanFactory
			.getBean("PubPostX");

	public static ServerVersion.X ServerVersionX = (ServerVersion.X) beanFactory
			.getBean("ServerVersionX");
	public static CurVersion.X CurVersionX = (CurVersion.X) beanFactory
			.getBean("CurVersionX");

	public static Match.X MatchX = (Match.X) beanFactory.getBean("MatchX");
	
	// eg2
	
	public static TeamProInfo.X TeamProInfoX = (TeamProInfo.X) beanFactory.getBean("TeamProInfoX");
	
	public static UserProInfo.X UserProInfoX = (UserProInfo.X) beanFactory.getBean("UserProInfoX");
	
	public static Article.X ArticleX = (Article.X) beanFactory.getBean("ArticleX");
	
	public static Attachment.X AttachmentX = (Attachment.X) beanFactory.getBean("AttachmentX");
	public static Photo.X PhotoX = (Photo.X) beanFactory.getBean("PhotoX");
	
	public static MatchType.X MatchTypeX = (MatchType.X) beanFactory.getBean("MatchTypeX");
	
	public static Userpoint.X UserpointX = (Userpoint.X) beanFactory.getBean("UserpointX");
	public static Teampoint.X TeampointX = (Teampoint.X) beanFactory.getBean("TeampointX");
	
	public static Reputation.X ReputationX=(Reputation.X)beanFactory.getBean("ReputationX");
	

	public static UserEgHistory.X UserEgHistoryX=(UserEgHistory.X)beanFactory.getBean("UserEgHistoryX");
	public static UserBattleFriend.X UserBattleFriendX = (UserBattleFriend.X)beanFactory.getBean("UserBattleFriendX");
	public static SignUpTeamMember.X SignUpTeamMemberX=(SignUpTeamMember.X)beanFactory.getBean("SignUpTeamMemberX");
	
	public static TeamBbs.X TeamBbsX=(TeamBbs.X)beanFactory.getBean("TeamBbsX");
	
	public static WarZone.X WarZoneX=(WarZone.X)beanFactory.getBean("WarZoneX");
	public static UserCaller.X UserCallerX = (UserCaller.X) beanFactory.getBean("UserCallerX");
	
	public static ReplayHistory.X ReplayHistoryX=(ReplayHistory.X)beanFactory.getBean("ReplayHistoryX");
	public static FreshMsg.X FreshMsgX=(FreshMsg.X)beanFactory.getBean("FreshMsgX");
	
	public static Replay.X ReplayX = (Replay.X)beanFactory.getBean("ReplayX");
	
	public static MessageBoard.X MessageBoardX = (MessageBoard.X)beanFactory.getBean("MessageBoardX");

	public static FightHistory.X FightHistoryX=(FightHistory.X)beanFactory.getBean("FightHistoryX");
	
	public static Duel.X DuelX = (Duel.X)beanFactory.getBean("DuelX");
	
	public static Comment.X CommentX = (Comment.X) beanFactory.getBean("CommentX");
	public static CommentMsg.X CommentMsgX = (CommentMsg.X) beanFactory.getBean("CommentMsgX");
	
}
