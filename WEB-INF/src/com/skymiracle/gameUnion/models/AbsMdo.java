package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;

public abstract class AbsMdo<T extends AbsMdo<T>> extends Mdo<T> {

	public AbsMdo(Mdo_X<T> mdoX) {
		super(mdoX);
	}
}
