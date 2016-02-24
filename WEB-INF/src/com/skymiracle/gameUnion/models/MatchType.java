package com.skymiracle.gameUnion.models;

import java.util.HashMap;
import java.util.Map;

import com.skymiracle.mdo5.Mdo;
import static com.skymiracle.gameUnion.Singletons.*;

//游戏项目：魔兽1v1 2v2 真三 dota
public class MatchType extends Mdo<MatchType> {

	public static class X {
		private Map<String, MatchType> itemsMap = new HashMap<String, MatchType>();

		public Map<String, MatchType> getItemsMap() {
			return itemsMap;
		}

		public void setItemsMap(Map<String, MatchType> itemsMap) {
			this.itemsMap = itemsMap;
		}
	}

	private String id;

	private String name;

	public MatchType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MatchType() {
		super();
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_matchType";
	}

	public MatchType load() {
		return MatchTypeX.getItemsMap().get(id);
	}
}
