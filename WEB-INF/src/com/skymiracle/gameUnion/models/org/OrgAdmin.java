package com.skymiracle.gameUnion.models.org;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import static com.skymiracle.gameUnion.Singletons.*;
public class OrgAdmin extends Mdo<OrgAdmin>{
	public static class X extends Mdo_X<OrgAdmin> {
		
	}
	private String orgId;
	
	private String username;
	
	private String password;
	
	public OrgAdmin() {
		super(OrgAdminX);
	}
	public OrgAdmin(String orgId, String username) {
		this();
		this.orgId = orgId;
		this.username = username;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String[] keyNames() {
		return new String[]{"orgid", "username"};
	}

	@Override
	public String table() {
		return "tb_orgadmin";
	}

}
