package com.skymiracle.gameUnion.models;
import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;

import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

public class FriendSetting extends AbsMdo<FriendSetting>{
	 
	@Title("好友姓名")
	private String username;

	@Title("申请好友权限")
	private PRIVACY privacy= PRIVACY.ANYONE;
	
	@Title("好友请求提示语")
	private String  friendreqtip="我只加认识人";
	
	
	@Title("是否启用提示语")
	private boolean  usingtip ;
	
	
 
	@Title("未通过|已通过")
	public static enum PRIVACY {
		ANYONE,SOMEONE,NOBODY
	};

	 

	public FriendSetting() {
		super(FriendSettingX);
	}
	
	public FriendSetting(String username) {
		this();
		this.username = username;
	}

	
	public boolean getUsingtip() {
		return usingtip;
	}

	public void setUsingtip(boolean usingtip) {
		this.usingtip = usingtip;
	}

	public PRIVACY getPrivacy() {
		return privacy;
	}

	public void setPrivacy(PRIVACY privacy) {
		this.privacy = privacy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriendreqtip() {
		return friendreqtip;
	}

	public void setFriendreqtip(String friendreqtip) {
		this.friendreqtip = friendreqtip;
	}
	 
	@Override
	public String[] keyNames() {
		return new String[] {"username"};
	}

	@Override
	public String table() {
		return "tb_userfriend";
	}
	public static class X extends Mdo_X<FriendSetting> {
	}
	public String getAvatorUrl() throws AppException, Exception {
		User user = new User(username).load();
		File logoFile = user.getAvatorFile();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/1/" + this.username+ ".jpg?" + logoFile.lastModified();
		else
			return "/51bisaifiles/user/default.jpg";
	}
	public String getDispName() throws AppException, Exception {
		User user = new User(username).load();
		return user.getDispName();
	}

}
