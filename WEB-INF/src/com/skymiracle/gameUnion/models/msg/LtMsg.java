package com.skymiracle.gameUnion.models.msg;

import com.skymiracle.mdo5.Mdo_X;

public abstract class LtMsg<T extends LtMsg<T>> extends RtMsg<T> {

	public LtMsg(Mdo_X<T> mdoX) {
		super(mdoX);
	}

}
