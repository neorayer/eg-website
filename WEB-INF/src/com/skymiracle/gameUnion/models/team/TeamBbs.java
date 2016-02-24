package com.skymiracle.gameUnion.models.team;

import java.util.UUID;

import com.skymiracle.gameUnion.models.AbsMdo;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class TeamBbs extends AbsMdo<TeamBbs> {
	
	public static class X extends Mdo_X<TeamBbs> {

		public MList<TeamBbs> findByRootId(String rootId) throws AppException, Exception {
			return this.find("rootId", rootId);
		}
		
	}

	@Title("主键")
	private String uuid = UUID.randomUUID().toString();

	@Title("主题")
	private String title;

	@Title("内容")
	private StringBuffer content;

	@Title("发布者")
	private String author;

	@Title("根贴ID")
	private String rootId = "";

	@Title("战队ID")
	private String teamId;

	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	@Title("创建日期")
	@Auto(Auto.Type.CreateDate)
	private String createDate;

	@Title("更新时间")
	@Auto(Auto.Type.ModifyDateTime)
	private String modifyDateTime;

	@Title("更新日期")
	@Auto(Auto.Type.ModifyDate)
	private String modifyDate;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
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

	public String getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(String modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public TeamBbs() {
		super(TeamBbsX);
	}

	@Override
	public String[] keyNames() {
		return new String[]{"uuid"};
	}

	@Override
	public String table() {
		return "tb_teambbs";
	}
	
	public MList<TeamBbs> getReplys() throws AppException, Exception {
		return TeamBbsX.findByRootId(uuid);
	}
	
	public void deleteReplys() throws AppException, Exception {
		// 本身就是回贴
		if(rootId != null && !"".equals(rootId))
			return;
		
		TeamBbsX.findByRootId(uuid).delete();
	}
	
	public void delete() throws AppException, Exception {
		deleteReplys();
		super.delete();
	}

	public User getAuthingUser() throws AppException, Exception {
		try{
			return new User(author).load();
		}
		catch(NotExistException e) {
			return new User();
		}
	}
	

}
