package com.skymiracle.gameUnion.models.hall;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import static com.skymiracle.gameUnion.Singletons.*;
public class HallMenuItem extends Mdo<HallMenuItem>{
	public static class X extends Mdo_X<HallMenuItem> {
		
	}
	
	@Title("大厅ID")
	private String hallId;
	
	@Title("菜单项编号")
	private String itemId;
	
	@Title("顺序编号")
	private int sortId = 0;
	
	@Title("名称")
	private String name;
	
	@Title("链接地址")
	private String url;
	
	public HallMenuItem() {
		super(HallMenuItemX);
	}
	
	public HallMenuItem(String hallId, String itemId) {
		this();
		this.hallId = hallId;
		this.itemId = itemId;
	}
	
	public String getHallId() {
		return hallId;
	}

	public void setHallId(String hallId) {
		this.hallId = hallId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String[] keyNames() {
		return new String[]{"hallid", "itemid"};
	}

	@Override
	public String table() {
		return "tb_hallmenuitem";
	}

}
