package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.util.List;


import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.UUID;

public class FreshMsg extends AbsMdo<FreshMsg> {

	public static class X extends Mdo_X<FreshMsg> {
		public MList<FreshMsg> findByUser(User user, long count)
				throws AppException, Exception {
			String format = "receiver,createdatetime-";
			if (count > 0)
				format += ":" + count;
			return find(format, user.getUsername());
		}
	}

	public String id = new UUID().toShortString();

	@Title("相册ID|文章Id")
	public String parentid;
	
	@Title("更新数量")
	public int updatecount=0;
	
	@Title("发起者")
	public String sender;

	@Title("接收者")
	public String receiver;

	@Title("主题")
	@Length(250)
	public String subject;

	@Title("消息")
	public StringBuffer content;

	@Title("是否已读")
	public boolean readed = false;

	@Title("创建|更新")
	public static enum MSGTYPE {
		GENERAL,ALBUM,ARTICLE,FRIEND,AVATOR
	}
	@Title("新鲜事类别")
	public MSGTYPE msgType = MSGTYPE.GENERAL;

	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	@Title("创建日期")
	@Auto(Auto.Type.CreateDate)
	private String createDate;

	@Title("是否有效")
	public boolean status = true;

	public static String EG_SYS = "EG_SYS";
	
	public FreshMsg() {
		super(FreshMsgX);
	}

	public FreshMsg(String id) {
		this();
		this.id = id;
	}
	public FreshMsg(String sender, String receiver,MSGTYPE msgtype) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = msgtype;
	}
	public FreshMsg(String sender, String receiver,MSGTYPE msgtype,String parentid) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = msgtype;
		this.parentid=parentid;
	}
	public FreshMsg(String sender, String receiver,MSGTYPE msgtype,String parentid,int count) {
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = msgtype;
		this.parentid=parentid;
		this.updatecount=count;
	}
	
	public int getUpdatecount() {
		return updatecount;
	}

	public void setUpdatecount(int updatecount) {
		this.updatecount = updatecount;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public MSGTYPE getMsgType() {
		return msgType;
	}

	public void setMsgType(MSGTYPE msgType) {
		this.msgType = msgType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public boolean getReaded() {
		return readed;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_fresh_msg";
	}
	public String getReceiverAvator() throws AppException, Exception{
		return new User(this.sender).load().getAvatorSmallUrl();
	}
	
	public String getArticle() throws AppException, Exception{
		return new Article(parentid).load().getContent100();
	}
	
	public List<Photo>  getPhoto()throws AppException, Exception{
		int updatecount= this.updatecount<=4?this.updatecount:4;
		return PhotoX.findPaged(1,updatecount,"username,albumid,createDateTime-",sender,parentid,null);
	}
}
