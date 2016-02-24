package com.skymiracle.gameUnion.models;

import java.util.HashMap;
import java.util.Map;

import com.skymiracle.mdo5.Mdo;

public class Authority extends Mdo<Authority> {

	private String code;

	private String desc;

	public static class  X {
		private Map<String, Authority> itemsMap = new HashMap<String, Authority>();

		public Map<String, Authority> getItemsMap() {
			return itemsMap;
		}

		public void setItemsMap(Map<String, Authority> itemsMap) {
			this.itemsMap = itemsMap;
		}
	}
	public Authority() {
		super();
	}
	
	public Authority(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "code" };
	}

	@Override
	public String table() {
		return "tb_eg_authority";
	}

}
