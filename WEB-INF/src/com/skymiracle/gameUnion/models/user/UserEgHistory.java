package com.skymiracle.gameUnion.models.user;

import static com.skymiracle.gameUnion.Singletons.UserEgHistoryX;

import java.util.UUID;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.team.GameTeam;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.exception.AppException;

public class UserEgHistory extends AbsMdo<UserEgHistory> {

	public static class X extends Mdo_X<UserEgHistory> {
		//添加注册用户历史
		public void addRegUserHistory(User user) throws AppException, Exception {
			UserEgHistory history = new UserEgHistory();
			history.setUsername(user.getUsername());
			history.setMatchTypeId("");
			history.setProfession(user.getProfession().toString());
			StringBuffer sb =new StringBuffer();
			sb.append("开始电子竞技生涯！");
			history.setEvent(sb.toString());
			history.create();
		}
		
		//添加注册战队历史
		public void addRegTeamHistory(User user,GameTeam team) throws AppException, Exception {
			UserEgHistory history = new UserEgHistory();
			history.setUsername(user.getUsername());
			history.setMatchTypeId("");
			history.setProfession(user.getProfession().toString());
			StringBuffer sb =new StringBuffer();
			if(user.isTeamLeader())
				sb.append("创建了");
			else
				sb.append("开始效力于");
			sb.append(team.getTeamName());
			sb.append("战队！");
			history.setEvent(sb.toString());
			history.create();
		}
		public PagedList<UserEgHistory> getHistoryByName(int pageNum,int countPerPage,String username) throws AppException, Exception{
			return this.findPaged(pageNum, countPerPage, "username,createDate+", username);
		}
		public long getHistoryCountByName(String username) throws AppException, Exception{
			return this.count("username", username);
		}
		//添加比赛历史
		public void addBisaiHistory(User user) throws AppException, Exception {
			//TODO 
		}

	}

	@Title("ID")
	private String id =UUID.randomUUID().toString();
	
	@Title("平台用户名")
	private String username;

	@Title("比赛类型编号")
	private String matchTypeId;
	
	@Title("职业")
	private String profession;

	@Title("时间")
	@Auto(Auto.Type.CreateDate)
	private String createDate;
	
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	@Title("历史事件")
	private String event;
	
	public UserEgHistory() {
		super(UserEgHistoryX);
	}
	
	public UserEgHistory(String id) {
		this();
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_user_eg_history";
	}

}
