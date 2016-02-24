package com.skymiracle.gameUnion.models.bisai;

import static com.skymiracle.gameUnion.Singletons.SignUpTeamMemberX;

import java.util.UUID;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.MList;

import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;

public class SignUpTeamMember extends AbsMdo<SignUpTeamMember> {
	public static class X extends Mdo_X<SignUpTeamMember> {

//		public void create(SignUpUser signUpUser, MList<SignUpTeamMember> tms)
//				throws AppException, Exception {
//			for (SignUpTeamMember tm : tms) {
//				if("".equals(tm.getUsername()))
//					continue;
//				tm.setLeader(signUpUser.getUsername());
//				tm.setAppId(signUpUser.getBisaiId());
//				tm.create();
//			}
//		}
//
//		public void deleteAllMemberByLeader(String username)
//				throws AppException, Exception {
//			MdoMap mm = new MdoMap();
//			mm.put("leader", username);
//			SignUpTeamMemberX.delete(mm);
//		}
		
		public MList<SignUpTeamMember> findMyMemberByBisai(String bisaiid,String leader) throws AppException, Exception{
			return this.find("appId,leader", bisaiid,leader);
		}
		public boolean isUserJoined(String bisaiid,String username) throws AppException, Exception{
			MList<SignUpTeamMember> member=this.find("appId,username", bisaiid,username);
			if(member!=null&&member.size()>0)
				return true;
			return false;
		}
		public boolean isTeamUserJoined(String bisaiid,String teamid,String username) throws AppException, Exception{
			MList<SignUpTeamMember> member=this.find("appId,leader,username", bisaiid,teamid,username);
			if(member!=null&&member.size()>0)
				return true;
			return false;
		}
	}

	public SignUpTeamMember() {
		super(SignUpTeamMemberX);
	}

	public SignUpTeamMember(String id) {
		this();
		this.id = id;
	}

	private String id = UUID.randomUUID().toString();

	@Title("应用的id,如比赛id")
	private String appId;

	@Title("队长id")//实际是装的teamid
	private String leader;

	@Title("成员名")
	private String username;

	@Title("成员qq")
	private String memberQq;

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_signupteammember";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	

	public String getLeader() {
		return leader;
	}
	
	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMemberQq() {
		return memberQq;
	}

	public void setMemberQq(String memberQq) {
		this.memberQq = memberQq;
	}
	public User getUser() throws AppException, Exception {
		if (username == null)
			return new User();
		try {
			return new User(username).load();
		} catch (NotExistException e) {
			return new User();
		}
	}
	
}
