package com.skymiracle.gameUnion.controllers;

import java.io.File;
import java.util.List;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.http.HttpUploader.TempUpFile;
import com.skymiracle.mdo4.DaoStorageException;
import com.skymiracle.mdo4.NullKeyException;
import com.skymiracle.mdo5.MList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.BeanInject;
import com.skymiracle.sor.annotation.NoSessioned;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.controller.WebController;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class Ra_Ctr_Option extends Ra_Ctr {
	public void vi_home(ActResult r) throws AppException, Exception {
	}

	public void vi_sidebar(ActResult r) throws AppException, Exception {

	}

	public void vi_gameZones(ActResult r) throws AppException, Exception {
		r.putMap("games", GameX.findAll());

		String gameId = "".equals($("gameid")) ? null : $("gameid");
		r.putMap("gameZones", GameZoneX.find("gameid+", new Object[] { gameId }));
		r.putMap("gameid", gameId);
	}

	public void do_gameZone_add(ActResult r) throws AppException, Exception {
		$M(GameZone.class).create();
	}

	public void do_gameZone_del(ActResult r) throws AppException, Exception {
		$M(GameZone.class).delete();
		r.setRedirectTo("gameZones.jsplayout.vi");
	}

	public void vi_gameMaps(ActResult r) throws AppException, Exception {
		r.putMap("games", GameX.findAll());
		r.putMap("gameZones", GameZoneX.findAll());
		r.putMap("gameMaps", GameMapX.findAll());
	}

	public void do_gameMap_add(ActResult r) throws AppException, Exception {
		GameMap gm = $M(GameMap.class);
		gm.setGameId(new GameZone($("zoneid")).load().getGameId());
		gm.create();

		List<TempUpFile> ff = $$TFile();
		if(ff.size() == 0)
			return;
		TempUpFile tfile = ff.get(0);

		// 原文件名
		String orginalName = tfile.getOrginalName();

		// 原文件后缀名
		String format = orginalName.substring(orginalName.lastIndexOf(".") + 1);

		// 保存文件
		gm.saveMapFile(new File(tfile.getTmpUpPath()), format);
	}

	public void do_gameMap_del(ActResult r) throws AppException, Exception {
		$M(GameMap.class).delete();
		r.setRedirectTo("gameMaps.jsplayout.vi");
	}

	public void do_gameMap_download(ActResult r) throws AppException, Exception {
		File file = $M(GameMap.class).getMapFile();
		r.putFile(file, file.getName());
	}
	
	public void do_gameMap_mod(ActResult r) throws  AppException,  Exception {
		$M(GameMap.class).update($MM(GameMap.class));
		r.setRedirectTo("gameMaps.jsplayout.vi");
	}
	
	public void vi_gameMap_mapFile_upload(ActResult r) throws  AppException,  Exception {
		r.putMap("gameMap", $M(GameMap.class).load());
	}
	
	public void do_gameMap_mapFile_upload(ActResult r) throws  AppException,  Exception {
		TempUpFile tfile = $TFile();

		// 原文件名
		String orginalName = tfile.getOrginalName();

		// 原文件后缀名
		String format = orginalName.substring(orginalName.lastIndexOf(".") + 1);

		// 保存文件
		$M(GameMap.class).saveMapFile(new File(tfile.getTmpUpPath()), format);
	}
	
	public void vi_gameMap_imgFile_upload(ActResult r) throws  AppException,  Exception {
		r.putMap("gameMap", $M(GameMap.class).load());
	}
	
	public void do_gameMap_imgFile_upload(ActResult r) throws  AppException,  Exception {
		$M(GameMap.class).saveImgFile($File());
	}
	
	
}
