package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.MessageBoardX;

import java.util.UUID;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;

@Title("留言板")
public class MessageBoard extends Mdo<MessageBoard>{

	public static class X extends Mdo_X<MessageBoard> {

		public void modReaded(String username) throws AppException, Exception {
			MList<MessageBoard> messages = MessageBoardX.find("owner,readed", username,false);
			
			for (MessageBoard message : messages) {
				message.update("readed", true);
			}
		}
		
	}
	
	private String id = UUID.randomUUID().toString();
	
	private String parentId;
	
	@Title("空间主人")
	private String owner;
	
	@Title("空间客人")
	private String guest;
	
	@Title("留言内容")
	private StringBuffer message;
	
	@Title("是否公开")
	private boolean publiced =false;
	
	@Title("是否已读")
	public boolean readed = false;
	
	@Title("留言日期")
	@Auto(Auto.Type.CreateDate)
	private String createDate;

	@Title("留言时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;


	public boolean getReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	public boolean getPubliced() {
		return publiced;
	}

	public void setPubliced(boolean publiced) {
		this.publiced = publiced;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StringBuffer getMessage() {
		return message;
	}

	public void setMessage(StringBuffer message) {
		this.message = message;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public MessageBoard() {
		super(MessageBoardX);
	}
	
	public MessageBoard(String id) {
		setId(id);
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public String[] keyNames() {
		return new String[] {"id"};
	}

	@Override
	public String table() {
		return new String("tb_MessageBoard");
	}
	
	public String getAvatorSmallUrl() throws AppException, Exception {
		User user = new User(guest).load();
		return user.getAvatorSmallUrl();
	}
	
	public User getGuestUser(){
		User user=null;
		try {
			user=new User(guest).load();
		} catch (Exception e) {
			
		}
		return user;
	}
	public void delete() throws AppException, Exception{
		MessageBoardX.find("parentid", id).delete();
		super.delete();
	}
	
	public MList<MessageBoard> getParentMessages() throws AppException, Exception{
		return MessageBoardX.find("parentid,createDateTime+", id, null);
	}
	
	
}
