package com.skymiracle.gameUnion.models.user;

import static com.skymiracle.gameUnion.Singletons.UserBattleFriendX;

import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;

@Title("玩家关系")
public class UserBattleFriend extends Mdo<UserBattleFriend> {
	
	

	@Title("源用户名")
	private String srcname;
	
	@Title("目的用户名")
	private String descname;
	
	
	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	@Title("创建日期")
	@Auto(Auto.Type.CreateDate)
	private String createDate;

	public UserBattleFriend() {
		super(UserBattleFriendX);
	}
	
	public String getSrcname() {
		return srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	public String getDescname() {
		return descname;
	}

	public void setDescname(String descname) {
		this.descname = descname;
	}


	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public User getFriend(){
		User user=null;
		try {
			user=new User(descname).load();
		} catch (Exception e) {
		}
		return user;
	}
	
	@Override
	public String[] keyNames() {
		return new String[] { "srcname","descname" };
	}
	
	@Override
	public String table() {
		return "tb_userBattleFriend";
	}
	public static class X extends Mdo_X<UserBattleFriend> {
		public void makeBattleFrindInSignUpTeam(String teamid){
			
		}
		public MList<UserBattleFriend> getTop6BattleFriend(String username) throws AppException, Exception{
			return this.find("srcname,createdatetime-:0,6", username);
		}
		
		public PagedList<UserBattleFriend> getBattleFriendsByUsername(int pageNum,int perPage,String username) throws AppException, Exception{
			return this.findPaged(pageNum,perPage,"srcname,createDateTime-", username);
		}
		public long getBattleFriendsCountByUsername(String username) throws AppException, Exception{
			return this.count("srcname", username);
		}
	}
	public static void main(String[] args) {
		String[] str={"a","b","c","d","e"};
		for(int i=0;i<str.length;i++){
			for(int j=i;j<str.length;j++){
				if(i!=j){
					System.out.println(str[i]+"与"+str[j]+"成为战友");
				}
			}
		}
	}
}
