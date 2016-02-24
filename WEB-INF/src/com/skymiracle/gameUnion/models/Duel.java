package com.skymiracle.gameUnion.models;

import java.util.UUID;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;
import static com.skymiracle.gameUnion.Singletons.*;


public class Duel extends Mdo<Duel>{

	public static class X extends Mdo_X<Duel> {
	}
	
	private String id = UUID.randomUUID().toString();
	
	@Title("挑战游戏")
	private String gameType;
	
	@Title("使用平台")
	private String platform;
	
	@Title("平台大厅")
	private String hall;
	
	@Title("联系方式")
	private String contact;	
	
	@Title("挑战宣言")
	private String content;
	
	@Title("预订时间")
	private String createDateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Duel() {
		super(DuelX);
	}

	@Override
	public String[] keyNames() {
		return new String[] {"id"};
	}

	@Override
	public String table() {
		return new String("tb_duel");
	}
	
	public void delete() throws AppException, Exception{
		super.delete();
	}
	
}
