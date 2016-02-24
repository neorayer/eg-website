package com.skymiracle.gameUnion.util;

public class CommonUtils {

	/**
	 * 将IP的实际地理位置名称，换算成一个有隐私保护的的名称。
	 * @param location
	 * @return
	 */
	public static String getPrivacyLocation(String location) {
		if (location == null)
			return "";
		String[] ss = location.split(" ");
		if (ss.length < 2)
			return location;
		for (int i=1; i<ss.length; i++) {
		if (ss[i].indexOf("网吧") >= 0)
			return ss[0] + " **网吧";
		}
		return location;
	}
}
