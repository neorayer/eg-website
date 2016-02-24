package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.util.HashMap;
import java.util.Map;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Title;

@Title("战区")
public class WarZone extends Mdo<WarZone> {

	public static class X extends Mdo_X<WarZone> {

		private Map<String, WarZone> itemsMap = new HashMap<String, WarZone>();

		public Map<String, WarZone> getItemsMap() {
			if (!itemsMap.isEmpty())
				return itemsMap;

			{
				WarZone zone = new WarZone();
				zone.setId("XiBei");
				zone.setName("西北战区");
				zone.setMemo("新疆、西藏、青海、甘肃、内蒙、宁夏、陕西");
				itemsMap.put("XiBei", zone);
			}
			{
				WarZone zone = new WarZone();
				zone.setId("DongBei");
				zone.setName("东北战区");
				zone.setMemo("黑龙江、吉林、辽宁");
				itemsMap.put("DongBei", zone);
			}
			{
				WarZone zone = new WarZone();
				zone.setId("HuaBei");
				zone.setName("华北战区");
				zone.setMemo("河北、河南、山西");
				itemsMap.put("HuaBei", zone);
			}
			{
				WarZone zone = new WarZone();
				zone.setId("BaShu");
				zone.setName("巴蜀战区");
				zone.setMemo("四川、重庆");
				itemsMap.put("BaShu", zone);
			}
			{
				WarZone zone = new WarZone();
				zone.setId("LiangHu");
				zone.setName("华中战区");
				zone.setMemo("湖北、湖南、贵州、江西");
				itemsMap.put("LiangHu", zone);
			}

			{
				WarZone zone = new WarZone();
				zone.setId("ZhuSanJiao");
				zone.setName("珠三角战区");
				zone.setMemo("福建、广东、香港、台湾、海南、云南、广西");
				itemsMap.put("ZhuSanJiao", zone);
			}

			{
				WarZone zone = new WarZone();
				zone.setId("ChangSanJiao");
				zone.setName("长三角战区");
				zone.setMemo("安徽、江苏、浙江、上海");
				itemsMap.put("ChangSanJiao", zone);
			}

			{
				WarZone zone = new WarZone();
				zone.setId("BoHaiWan");
				zone.setName("渤海湾战区");
				zone.setMemo("北京、天津、山东");
				itemsMap.put("BoHaiWan", zone);
			}

			return itemsMap;
		}

		public void setItemsMap(Map<String, WarZone> itemsMap) {
			this.itemsMap = itemsMap;
		}
	}

	public WarZone() {
		super();
	}

	private String id;

	private String name;

	private String memo;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_warzone";
	}

}
