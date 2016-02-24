package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.BisaiX;
import static com.skymiracle.gameUnion.Singletons.MatchTypeX;
import static com.skymiracle.gameUnion.Singletons.MatchX;
import static com.skymiracle.gameUnion.Singletons.ReputationX;
import static com.skymiracle.gameUnion.Singletons.WarZoneX;

import com.skymiracle.gameUnion.models.Reputation;
import com.skymiracle.gameUnion.models.bisai.Bisai;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr_Match extends U_Ctr {
	
	/**
	 * 比赛首页
	 * 
	 */
	public void vi_index(ActResult r) throws  AppException,  Exception {
		r.putMap("matchtypes", MatchTypeX.getItemsMap().values());
	}
	
	/**
	 * 比赛
	 * 
	 */
	public void vi_bisaisByType(ActResult r) throws  AppException,  Exception {
		r.putMap("warzones", WarZoneX.getItemsMap().values());
		
	}
	
	/**
	 * 根据游戏类型和战区查看比赛
	 * 
	 */
	public void vi_bisaisByTypeandZone(ActResult r) throws  AppException,  Exception {
		MList<Bisai> bisais = BisaiX.find("type,warzoneid,viewed", $("matchtypeid"), $("warzoneid"),true);
		r.putMap("bisais", bisais);
	}
	
	public void vi_bisai_create(ActResult r) throws  AppException,  Exception {
		r.putMap("matchsnum", MatchX.count());
		r.putMap("matchtypes", MatchTypeX.getItemsMap().values());
		r.putMap("warzones", WarZoneX.getItemsMap().values());
	}
	
	/**
	 * 创建比赛
	 * 
	 */
	@Sessioned
	public void do_bisai_create(ActResult r) throws  AppException,  Exception {
		Bisai bisai =$M(Bisai.class);
		bisai.setOwner(actor.getUsername());
		bisai.create();
	}
	
	/**
	 * 删除比赛
	 * 
	 */
	@Sessioned
	public void do_bisai_del(ActResult actResult) throws  AppException,  Exception {
		$M(Bisai.class).delete();
	}
	
	/**
	 * 管理比赛
	 * 
	 */
	@Sessioned
	public void vi_bisai_manage(ActResult r) throws  AppException,  Exception {
		r.putMap("matchtypes", MatchTypeX.getItemsMap().values());
		r.putMap("warzones", WarZoneX.getItemsMap().values());
	}
	
	/**
	 * 比赛
	 * 
	 */
	public void vi_bisais(ActResult r) throws  AppException,  Exception {
		MdoMap mm = new MdoMap();
		String matchtypeid = $("matchtypeid");
		if(!"".equals(matchtypeid))
			mm.put("type", matchtypeid);
		
		String warzoneid = $("warzoneid");
		if(!"".equals(warzoneid))
			mm.put("warZoneId", warzoneid);
		
		String title = $("title");
//		if(!title.equals(""))
//			mm.put("title", title);
		
		String filter =null;
		if (!"".equals(title)) {
			filter=" title LIKE '%" + title + "%'";
		}

		PagedList<Bisai> bisais = BisaiX.findPaged(mm, filter, "createDateTime", false, $i("pageNum", 1), $i("perPage", 10));
		bisais.setLinkPrefix("../match/bisais.jsp.vi");
		r.putMap("pageBar", bisais.getPageBarHTML());
		r.putMap("bisais", bisais);
	}
	
	@Sessioned
	public void do_bisai_mod(ActResult r) throws  AppException,  Exception {
		$M(Bisai.class).update("viewed", $("viewed"));
	}
}