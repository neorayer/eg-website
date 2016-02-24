package com.skymiracle.gameUnion;

import com.skymiracle.mdo4.trans.TransServiceProxyNONE;
import com.skymiracle.sor.SpringBeanGetter;

public class AppBeanGetter extends SpringBeanGetter{

	public AppBeanGetter()
			throws ClassNotFoundException {
		super(TransServiceProxyNONE.class, "com/skymiracle/gameUnion/spring.xml", null);
	}
	

}
