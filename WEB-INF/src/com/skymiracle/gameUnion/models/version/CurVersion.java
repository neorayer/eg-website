package com.skymiracle.gameUnion.models.version;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;

import static com.skymiracle.gameUnion.Singletons.*;
public class CurVersion extends Mdo<CurVersion>{

	public static class X extends Mdo_X<CurVersion> {
		
	}
	private String type;
	
	private String vid;
	
	public CurVersion() {
		super(CurVersionX);
	}

	public CurVersion(String type) {
		this();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	@Override
	public String[] keyNames() {
		return new String[]{"type"};
	}

	@Override
	public String table() {
		return "tb_curversion";
	}
	
	
}
