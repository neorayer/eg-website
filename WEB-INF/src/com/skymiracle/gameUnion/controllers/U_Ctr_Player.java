package com.skymiracle.gameUnion.controllers;
import static com.skymiracle.gameUnion.Singletons.*;

import com.skymiracle.gameUnion.SendMail;
import com.skymiracle.gameUnion.models.Album;
import com.skymiracle.gameUnion.models.Article;
import com.skymiracle.gameUnion.models.Duel;
import com.skymiracle.gameUnion.models.FightHistory;
import com.skymiracle.gameUnion.models.GameZone;
import com.skymiracle.gameUnion.models.MessageBoard;
import com.skymiracle.gameUnion.models.Photo;
import com.skymiracle.gameUnion.models.ReplayHistory;
import com.skymiracle.gameUnion.models.Teampoint;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.Userpoint;
import com.skymiracle.gameUnion.models.user.UserBattleFriend;
import com.skymiracle.gameUnion.models.user.UserCaller;
import com.skymiracle.gameUnion.models.user.UserEgHistory;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr_Player extends U_Ctr { 
	
	protected User player =null;
	
	public void invokeBefore(ActResult r) throws  AppException,  Exception  {
		String username=$("username");
		if(username != null && !"".equals(username)){
				player=new User(username).load();
		}else if(actorId != null && !"".equals(actorId)){
			player=new User(actorId).load();
		}
		r.putMap("player", player);
			
	}
	
	public void vi_index(ActResult r) throws  AppException,  Exception {
		String username = $("username");
		if (actorId == null || username == null || actorId.equals(username)) {
			
		} else {
			UserCaller caller = new UserCaller(username, actorId);
			if (!caller.exists())
				caller.create();
			caller.update(new MdoMap());
		}
		if(username==null||"".equals(username)){
			username=actorId;
		}
		MList<UserCaller> ucs=UserCallerX.getLatestCaller6(username);
		MList<UserBattleFriend> ubfs=UserBattleFriendX.getTop6BattleFriend(username);
		MList<UserEgHistory> uehs=UserEgHistoryX.find("username,createDate-:0,2",username);
		r.putMap("userhistorycount", UserEgHistoryX.count("username",username));
		r.putMap("visitorcount", UserCallerX.count("username",username));
		r.putMap("battlefriendcount", UserBattleFriendX.count("srcname",username));
		r.putMap("userhis", uehs);
		r.putMap("visitors", ucs);
		r.putMap("battlefriends", ubfs);
		if (username != null && player.getUsername() != null && player.getUsername().equals(username)) {
			r.putMap("useremail", new User(username).load().getEmail());
			r.putMap("self", "yes");
		}
	}
	public void vi_info(ActResult r) throws  AppException,  Exception {
		String username=$("username");
		User player=null;
		if(username!=null&&!"".equals(username)){
			player=new User(username).load();
		}else if(actorId != null && !"".equals(actorId)){
			player=new User(actorId).load();
		}
		r.putMap("player", player);
		
	}
	public void vi_allplayerhistory(ActResult actResult) throws  AppException,  Exception {
		String username=$("username");
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 12);
		PagedList<UserEgHistory> history=UserEgHistoryX.getHistoryByName(pageNum, perPage, username);
		long historycount=UserEgHistoryX.getHistoryCountByName(username);
		history.setLinkPrefix("?username="+username);
		actResult.putMap("history", history);
		actResult.putMap("historycount", historycount);
		actResult.putMap("pagebar", history.getPageBarHTML());
	}
	
	public void vi_articles(ActResult r) throws  AppException,  Exception {
		String username=$("username");
		User player=new User(username).load();
		r.putMap("player",player);
		
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 10); 
		PagedList<Article> articles = ArticleX.findPaged(pageNum, perPage, "author,updatadatetime-", username,null);
		long count=ArticleX.count("author", actorId);
		articles.setLinkPrefix("?username="+username);
		r.putMap("articlecount",count);
		r.putMap("articles", articles);
		r.putMap("pageBar", articles.getPageBarHTML());
	}
	public void vi_article(ActResult r) throws  AppException,  Exception {
 
		r.putMap("article",$M(Article.class).load());
	}
	
	/**
	 *留言板
	 * 
	 */
	public void vi_messageBoard(ActResult r) throws  AppException,  Exception {
		r.putMap("username", player.getUsername());
	}
	
	/**
	 *留言板留言 
	 * 
	 */
	public void do_message_add(ActResult r) throws  AppException,  Exception {
		$M(MessageBoard.class).create();
	}
	
	/**
	 *查看留言板留言 
	 * 
	 */
	public void vi_messages(ActResult r) throws  AppException,  Exception {
		r.putMap("messageBoards", MessageBoardX.find("owner,parentId,createDateTime-:0,5", player.getUsername(),"",null));
	}
	
	/**
	 *删除留言板留言 
	 * 
	 */
	@Sessioned
	public void do_message_del(ActResult r) throws  AppException,  Exception {
		$M(MessageBoard.class).delete();
	}
	
	/**
	 *查看我的留言板留言 
	 * 
	 */
	public void vi_myMessageBoard(ActResult r) throws  AppException,  Exception {
		PagedList<MessageBoard> messageBoards = MessageBoardX.findPaged($i("pageNum", 1), $i("perPage", 5), "owner,parentId,createDateTime-:0,5", player.getUsername(),"",null);
		if(player.getUsername().equals(actorId)){
			MessageBoardX.modReaded(actorId);
		}
		messageBoards.setLinkPrefix("../player/myMessageBoard.jsp.vi");
		r.putMap("pageBar", messageBoards.getPageBarHTML());
		r.putMap("messageBoards", messageBoards);
	}
	
	
	public void vi_albums(ActResult r) throws  AppException,  Exception {
		String username=$("username");
		User player=new User(username).load();
		r.putMap("player",player);
		String filter =" type <> 'Personal'";
		MdoMap mdoMap= new MdoMap();
		mdoMap.put("username", username);
		PagedList<Album> albums = AlbumX.findPaged(mdoMap, filter, "updatadatetime", false, $i("pageNum", 1), $i("perPage", 16));
		long count=AlbumX.count("username", username);
		albums.setLinkPrefix("?username="+username);
		r.putMap("albums", albums);
		r.putMap("albumcount", count);
		r.putMap("pageBar", albums.getPageBarHTML());
	}
	public void vi_photos(ActResult r) throws  AppException,  Exception {
		String username=$("username");
		User player=new User(username).load();
		r.putMap("player",player);
		Album album=new Album($("id"),username).load();
		r.putMap("album",album);
		PagedList<Photo> photos = (PagedList<Photo>) album.getPhoto($i("pageNum", 1), $i("perPage", 15));
		photos.setLinkPrefix("?username="+username+"&id="+album.getId());
		r.putMap("photos",photos);
		r.putMap("pageBar", photos.getPageBarHTML());
		
	}
	public void vi_photo(ActResult r) throws  AppException,  Exception {
		String username=$("username");
		User player=new User(username).load();
		r.putMap("player",player);
		Photo photo=new Photo($("id"),username).load();
		Album album=new Album(photo.getAlbumid(),username).load();
		r.putMap("album",album);
		r.putMap("photo",photo);
	} 
	public void vi_allvisitors(ActResult actResult) throws  AppException,  Exception {
		String username=$("username");
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 12);
		PagedList<UserCaller> visitors=UserCallerX.getVisitorsByUsername(pageNum,perPage,username);
		long visitorCount=UserCallerX.getVisitorCountByUsername(username);
		visitors.setLinkPrefix("?username="+username);
		actResult.putMap("visitors",visitors);
		actResult.putMap("visitorCount",visitorCount);
		actResult.putMap("pageBar",visitors.getPageBarHTML());
	}
	
	public void vi_allbattlefriends(ActResult actResult) throws  AppException,  Exception {
		String username=$("username");
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 12);
		PagedList<UserBattleFriend> bfs=UserBattleFriendX.getBattleFriendsByUsername(pageNum,perPage,username);
		long bfCount=UserBattleFriendX.getBattleFriendsCountByUsername(username);
		bfs.setLinkPrefix("?username="+username);
		actResult.putMap("bfs",bfs);
		actResult.putMap("bfCount",bfCount);
		actResult.putMap("pageBar",bfs.getPageBarHTML());
	}
	
	/**
	 *向你挑战
	 * 
	 */
	
	public void do_duel_add(ActResult r) throws  AppException,  Exception {
		if(player != null && actorId != null && !player.getUsername().equals(actorId)){
			$M(Duel.class).create();
			if ($("useremail")!= null && !("").equals($("useremail"))) {
				StringBuffer sb = new StringBuffer();
				sb.append(actorId).append("向您挑战！！\r\n").append("挑战游戏:").append($("gametype")).append("\r\n使用平台:").append($("platform")).append("\r\n平台大厅:").append($("hall"))
				.append("\r\n预订时间:").append($("createdatetime")).append("\r\n联系方式:").append($("contact"))
				.append("\r\n挑战宣言:").append($("content")).append("\r\n");	
				new SendMail().sendMail($("useremail"),"EG电竞比赛挑战书","utf-8","3",sb.toString(),"EG电竞","postmaster@51bisai.com");
			}
		}else{
			r.putMap("login", "yes");
		}
	}
	
	/**
	 * 电竞水平
	 * 
	 */
	public void vi_eglevel(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_userPoint(ActResult r) throws  AppException,  Exception {
		MList<Userpoint> userpoints = UserpointX.find("username", player.getUsername());
		r.putMap("userpoints", userpoints);
	}
	
	public void vi_teamPoint(ActResult r) throws  AppException,  Exception {
		MList<Teampoint> teampoints = TeampointX.find("teamid", $("teamid"));
		r.putMap("teampoints", teampoints);
	}
	
	/**
	 * 电竞记录
	 * 
	 */
	public void vi_egreplay(ActResult r) throws  AppException,  Exception {
	}
	
	public void vi_userRecord(ActResult r) throws  AppException,  Exception {
		MList<FightHistory> fightHistorys = FightHistoryX.find("userorteam,cerateDateTime-:0,10", player.getUsername(),null);
		long fightcount=FightHistoryX.count("userorteam", player.getUsername());
		r.putMap("fightcount", fightcount);
		r.putMap("fightHistorys", fightHistorys);
		
	}

	public void vi_userRecords(ActResult r) throws  AppException,  Exception {
		PagedList<FightHistory> fightHistorys = FightHistoryX.findPaged($i("pageNum", 1), $i("perPage", 10), "userorteam,cerateDateTime-", player.getUsername(),null);
		fightHistorys.setLinkPrefix("../player/userRecords.jsplayout.vi");
		r.putMap("pageBar", fightHistorys.getPageBarHTML());
		r.putMap("fightHistorys", fightHistorys);
	}
	
	public void vi_bisaiRecord(ActResult r) throws  AppException,  Exception {
		MList<ReplayHistory> rhs=ReplayHistoryX.getTop10ReplayByUserOrTeam(player.getUsername());
		r.putMap("replayhis", rhs);

	}
	
	/**
	 * 规则
	 * 
	 */
	public void vi_ladderRule(ActResult r) throws  AppException,  Exception {
	}
}
