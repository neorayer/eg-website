package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.UUID;

public abstract class Msg<T extends Msg<T>> extends AbsMdo<T> {
	
	public static abstract class X<T extends Msg<T>> extends Mdo_X<T> {
		public MList<T> findByUser(User user, long count)
				throws AppException, Exception {
			String format = "receiver,createdatetime-";
			if (count > 0)
				format += ":" + count;
			return find(format, user.getUsername());
		}
	}
	

	public String id = new UUID().toShortString();

	@Title("发起者")
	public String sender;

	@Title("接收者")
	public String receiver;

	@Title("主题")
	@Length(255)
	public String subject;

	@Title("消息")
	public StringBuffer content;

	@Title("是否已读")
	public boolean readed = false;

	@Title("分类")
	public String msgType;

	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;

	@Title("创建日期")
	@Auto(Auto.Type.CreateDate)
	private String createDate;

	@Title("是否有效")
	public boolean status = true;

	public static String EG_SYS = "EG_SYS";

	public Msg(Mdo_X<T> mdoX) {
		super(mdoX);
	}

	

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
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

	public boolean getIsSys() {
		// 系统消息
		return EG_SYS.equals(sender);
	}

	public void invalid() throws AppException, Exception {
		this.update("status", false);
	}

	public void reading() throws AppException, Exception {
		this.update("readed", true);
	}
}
