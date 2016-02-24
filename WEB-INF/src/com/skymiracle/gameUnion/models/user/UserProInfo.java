package com.skymiracle.gameUnion.models.user;

import static com.skymiracle.gameUnion.Singletons.MatchTypeX;
import static com.skymiracle.gameUnion.Singletons.UserProInfoX;

import java.util.UUID;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.MatchType;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

public class UserProInfo extends AbsMdo<UserProInfo> {

	public static class X extends Mdo_X<UserProInfo> {

		public UserProInfo createCertificate(User user,MatchType matchType) throws AppException, Exception {
			UserProInfo proInfo =new UserProInfo();
			proInfo.setUsername(user.getUsername());
			proInfo.setMatchTypeId(matchType.getId());
			proInfo.setGrade("1");
			proInfo.setCreditVal("0");
			return proInfo.create();
		}

	}

	@Title("平台用户名")
	private String username;

	@Title("比赛类型编号")
	private String matchTypeId;

	@Title("等级")
	private String grade;

	@Title("信益值")
	private String creditVal;

	@Title("参赛证编号")
	private String certiId =UUID.randomUUID().toString();

	public UserProInfo() {
		super(UserProInfoX);
	}
	
	public UserProInfo(String username, String matchTypeId) {
		this();
		this.username = username;
		this.matchTypeId = matchTypeId;
	}

	public String getMatchTypeId() {
		return matchTypeId;
	}

	public void setMatchTypeId(String matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCreditVal() {
		return creditVal;
	}

	public void setCreditVal(String creditVal) {
		this.creditVal = creditVal;
	}

	public String getCertiId() {
		return certiId;
	}

	public void setCertiId(String certiId) {
		this.certiId = certiId;
	}

	@Override
	public String[] keyNames() {
		return new String[] {"username", "matchtypeid"};
	}

	@Override
	public String table() {
		return "tb_userPro_info";
	}
	
	public MatchType getProject() {
		return new MatchType(matchTypeId).load();
	}

}
