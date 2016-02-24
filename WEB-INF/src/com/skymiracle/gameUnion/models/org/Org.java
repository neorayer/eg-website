package com.skymiracle.gameUnion.models.org;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.gameUnion.models.hall.*;
import static com.skymiracle.gameUnion.Singletons.*;
public class Org extends Mdo<Org>{

	public static class X extends Mdo_X<Org> {
		
	}
	
	@Title("组织ID")
	private String id;
	
	@Title("组织名称")
	private String name;
	
	@Title("是否拥有私人大厅")
	private boolean hasPrivHall = false;
	
	public Org() {
		super(OrgX);
	}
	
	public Org(String id) {
		this();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean getHasPrivHall() {
		return hasPrivHall;
	}

	public void setHasPrivHall(boolean hasPrivHall) {
		this.hasPrivHall = hasPrivHall;
	}

	public MList<Hall> getHalls() throws AppException, Exception {
		return HallX.find("orgid", id);
	}
	
	@Override
	public void delete() throws AppException, Exception {
		OrgAdminX.find("orgid", id).delete();
		super.delete();
	}

	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_org";
	}

}
