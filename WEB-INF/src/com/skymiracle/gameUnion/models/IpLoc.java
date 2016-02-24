package com.skymiracle.gameUnion.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;

public class IpLoc extends Mdo<IpLoc> {

	public static class X extends Mdo_X<IpLoc> {

		public void importFile(File srcFile) throws AppException, Exception {
			FileReader fbr = new FileReader(srcFile);
			BufferedReader br = new BufferedReader(fbr);
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				i++;
				Logger.debug("Importing No." + i);
				try {
					String bs = line.substring(0, 15).trim();
					String es = line.substring(16, 31).trim();
					String ls = line.substring(32);

					long beginAddr = ip2Long(bs);
					long endAddr = ip2Long(es);
					IpLoc il = new IpLoc();
					il.setBeginAddr(beginAddr);
					il.setEndAddr(endAddr);
					il.setLocation(ls);
					il.create();
				} catch (Exception e) {
					Logger.debug("ERROR:Importing No." + i);
					
				}
			}
		}

		public String getLocation(String ip) throws AppException, Exception {
			long ipl = ip2Long(ip);
			MList<IpLoc> ipLos = this.find(null, ipl
					+ " >= beginaddr and endaddr >= " + ipl);
			if (ipLos.size() == 0)
				return null;
			else
				return ipLos.getFirst().getLocation();
		}

		private static long ip2Long(String ip) {
			String[] ss = ip.split("\\.");
			long l = 0;
			for (int i = 0; i < 4; i++) {
				long v = Long.parseLong(ss[i]);
				for (int j = 3 - i; j > 0; j--)
					v = v * 256;
				l += v;
			}
			return l;
		}
	}

	public IpLoc() {
		super(IpLocX);
	}

	private long beginAddr;

	private long endAddr;

	private String location;

	public long getBeginAddr() {
		return beginAddr;
	}

	public void setBeginAddr(long beginAddr) {
		this.beginAddr = beginAddr;
	}

	public long getEndAddr() {
		return endAddr;
	}

	public void setEndAddr(long endAddr) {
		this.endAddr = endAddr;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "beginaddr", "endaddr" };
	}

	@Override
	public String table() {
		return "tb_iplocation";
	}

	public static void main(String[] args) throws AppException, Exception {
		IpLocX.importFile(new File("/tmp/ips.txt"));

	}

}
