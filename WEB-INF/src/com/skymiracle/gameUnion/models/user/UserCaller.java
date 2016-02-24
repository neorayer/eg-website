package com.skymiracle.gameUnion.models.user;

import static com.skymiracle.gameUnion.Singletons.UserCallerX;

import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;

@Title("最近访问者")
public class UserCaller extends Mdo<UserCaller> {
	
	public static class X extends Mdo_X<UserCaller> {
		public MList<UserCaller> getLatestCaller6(String username) throws AppException, Exception{
			return this.find("username,time-:0,6", username);
		}
		public PagedList<UserCaller> getVisitorsByUsername(int pageNum,int perPage,String username) throws AppException, Exception{
			return this.findPaged(pageNum,perPage,"username,time-", username);
		}
		public long getVisitorCountByUsername(String username) throws AppException, Exception{
			return this.count("username", username);
		}
	}

	@Title("用户名")
	private String username;
	
	@Title("来访者")
	private String callername;
	
	@Title("来访时间")
	private String time = CalendarUtil.getLocalDateTime();
	public UserCaller() {
		super(UserCallerX);
	}

	public UserCaller(String username, String callername) {
		this();
		this.username=username;
		this.callername=callername;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCallername() {
		return callername;
	}

	public void setCallername(String callername) {
		this.callername = callername;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "username","callername" };
	}

	@Override
	public String table() {
		return "tb_usercaller";
	}
	public User getCaller(){
		User user=null;
		try {
			user=new User(callername).load();
		} catch (Exception e) {
			
		}
		return user;
	}
	
	public UserCaller create() throws AppException, Exception {
		return super.create();
	}
	public UserCaller update(MdoMap mdoMap) throws AppException, Exception {
		mdoMap.put("time",CalendarUtil.getLocalDateTime());
		return super.update(mdoMap);
	}
	public String getAvatorSmallUrl() {
		User user= new User(this.callername);
		return user.getAvatorSmallUrl();
		 
	}
	public String getVisitDate(){
		return time.substring(0,10);
	}
	
	 
}
