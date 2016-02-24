package com.skymiracle.gameUnion.models.version;

import java.util.UUID;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;
import static com.skymiracle.gameUnion.Singletons.*;
public class ServerVersion extends Mdo<ServerVersion>{

	public static class X extends Mdo_X<ServerVersion> {
		public ServerVersion getCurVersion() throws AppException, Exception {
			CurVersion cv = new CurVersion("hallserver");
			
			ServerVersion serverVer = new ServerVersion();
			try {
				cv.load();
				serverVer.setVid(cv.getVid());
			}catch(NotExistException e) {
				return serverVer;
			}
			try {
				serverVer.load();
			}catch(NotExistException e) {
				return new ServerVersion();
			}
			return serverVer;
		}
	}
	
	private String vid;
	
	private String publishTime = CalendarUtil.getLocalDateTime();

	private String memotext;
	
	public ServerVersion() {
		super(ServerVersionX);
	}
	

	public ServerVersion(String vid) {
		this();
		this.vid = vid;
	}
	



	public String getVid() {
		return vid;
	}



	public void setVid(String vid) {
		this.vid = vid;
	}



	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getMemotext() {
		return memotext;
	}

	public void setMemotext(String memotext) {
		this.memotext = memotext;
	}

	public boolean getIsCurVersion() throws AppException, Exception {
		return vid.equals(ServerVersionX.getCurVersion().getVid());
	}

	@Override
	public String[] keyNames() {
		return new String[]{"vid"};
	}

	@Override
	public String table() {
		return "tb_serverversion";
	}



	public void setCurVer(boolean isCur) throws AppException, Exception {
		CurVersion cv = new CurVersion("hallserver");
		cv.setVid(this.vid);
		cv.createOrUpdate();
	}

}
