package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.SequenceX;

public class Sequence extends AbsMdo<Sequence> {

	public static class X extends Mdo_X<Sequence> {

		public long currentNum(String table) throws AppException, Exception {
			Sequence sequence = new Sequence(table);
			try {
				sequence.load();
			} catch (Exception e) {
				sequence.setSequenceNum(1000000);
				sequence.create();
			}
			long num = sequence.getSequenceNum();
			sequence.plus(1);
			return num;
		}
	}

	public Sequence() {
		super(SequenceX);
	}

	public void plus(long i) throws AppException, Exception {
		sequenceNum += i;
		this.update("sequencenum", sequenceNum);
	}

	public Sequence(String appId) {
		this();
		this.appId = appId;
	}

	private String appId;

	private long sequenceNum;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(long sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "appid" };
	}

	@Override
	public String table() {
		return "tb_sequence";
	}

}
