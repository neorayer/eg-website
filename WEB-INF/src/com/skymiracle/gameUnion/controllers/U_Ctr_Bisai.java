package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.BisaiX;

import com.skymiracle.gameUnion.models.bisai.Bisai;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.exception.AppException;

public class U_Ctr_Bisai extends U_Ctr {
	public void do_bisai_manage(ActResult r) throws  AppException,  Exception {
		MList<Bisai> matchs = BisaiX.find("owner", actorId);
		System.out.println("11111111111111111111111111111111111111");
		for (Bisai bisai:matchs) {
			MdoMap mdoMap =new MdoMap();
			if ($("customselect_"+bisai.getId())==null) {
				mdoMap.put("customselect", false);
				mdoMap.put("sn", 0);
			}else{
				mdoMap.put("customselect", true);
				mdoMap.put("sn", $("sn_"+bisai.getId()));
			}
			bisai.update(mdoMap);
		}
		r.setRedirectTo("../match/index.jsplayout.vi");
	}
	public void do_bisai_mod(ActResult r) throws  AppException,  Exception {
		$M(Bisai.class).update($MM(Bisai.class));
	}
	
}
