package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import static com.skymiracle.gameUnion.Singletons.*;
public class RootAdmin extends Mdo<RootAdmin>{
	public static class X extends Mdo_X<RootAdmin> {
		
	}
	
	@Title("用户名")
	private String username;
	
	@Title("密码")
	private String password;
	
	public RootAdmin() {
		super(RootAdminX);
	}
	
	public RootAdmin(String username) {
		this();
		this.username = username;
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
		return new String[]{"username"};
	}

	@Override
	public String table() {
		return "tb_rootadmin";
	}

}
