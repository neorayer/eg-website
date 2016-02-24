package com.skymiracle.gameUnion.models;

import java.io.File;
import java.io.IOException;

import com.skymiracle.http.HttpUploader.TempUpFile;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.NotExistException;
import com.skymiracle.sor.exception.AppException;
import static com.skymiracle.gameUnion.Singletons.*;

public class GameMap extends Mdo<GameMap> {

	public static class X extends Mdo_X<GameMap> {
		private String fileStoreRootPath = "/gf/tomcat/webapps/51bisaifiles/gamemap/";

		public String getFileStoreRootPath() {
			return fileStoreRootPath;
		}

		public void setFileStoreRootPath(String fileStoreRootPath) {
			this.fileStoreRootPath = fileStoreRootPath;
		}

		public MList<GameMap> getPointableMaps() throws AppException, Exception {
			return this.find("isPointable", true);
		}
	}

	private String gameId;

	private int checksum = 0;

	private String zoneId;

	private String name;

	private String format;

	private boolean isPointable = true;

	private long size = 0;

	public GameMap() {
		super(GameMapX);
	}

	/**
	 * 当checksum不存在的时候，既该游戏不区分地图，则用gameId作为唯一标识符 此时，checksum = 0
	 * 
	 * @param gameId
	 * @param checksum
	 */
	public GameMap(String gameId, String zoneId, int checksum) {
		this();
		this.gameId = gameId;
		this.zoneId = zoneId;
		this.checksum = checksum;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean getIsPointable() {
		return isPointable;
	}

	public void setIsPointable(boolean isPointable) {
		this.isPointable = isPointable;
	}

	public Game getGame() throws AppException, Exception {
		try {
			return new Game(gameId).load();
		} catch (NotExistException e) {
			return null;
		}
	}

	@Override
	public String[] keyNames() {
		return new String[] { "gameid", "zoneid", "checksum" };
	}

	@Override
	public String table() {
		return "tb_gamemap";
	}

	public GameZone getGameZone() throws AppException, Exception {
		try {
			return new GameZone(zoneId).load();
		} catch (NotExistException e) {
			return null;
		}
	}

	// 保存地图
	public void saveMapFile(File srcfile, String format) throws AppException,
			Exception {
		setFormat(format);
		setSize(srcfile.length());
		// 创建文件夹
		File filesDir = getFilesDir();
		filesDir.mkdirs();

		// 保存文件
		File mapfile = new File(filesDir, getFileName());
		StreamPipe.fileToFile(srcfile, mapfile);

		update("format, size", format, size);
	}

	// 保存图片
	public void saveImgFile(File srcfile) throws AppException, Exception {
		// 创建文件夹
		File filesDir = getFilesDir();
		filesDir.mkdirs();

		// 保存文件
		File mapfile = new File(filesDir, getImgName());
		StreamPipe.fileToFile(srcfile, mapfile);
	}

	private File getFilesDir() {
		return new File(GameMapX.getFileStoreRootPath());
	}

	// 地图文件名
	public String getFileName() {
		return getGameMapKey() + "." + format;
	}

	// 图片文件名
	public String getImgName() {
		return getGameMapKey() + ".jpg";
	}

	public String getGameMapKey() {
		return gameId + "-" + zoneId + "-" + checksum;
	}

	// 地图文件
	public File getMapFile() throws AppException, Exception {
		// 此处需要load一次
		load();

		return new File(getFilesDir(), getFileName());
	}

	// 图片文件
	public File getImgFile() throws AppException, Exception {
		// 此处需要load一次
		load();

		return new File(getFilesDir(), getImgName());
	}

	public String getImgPath() throws AppException, Exception {
		File imgFile = getImgFile();
		if (imgFile.exists())
			return "/51bisaifiles/gamemap/" + getImgName() + "?"
					+ imgFile.lastModified();
		else
			return null;
	}

	public void delete() throws AppException, Exception {
		// 删除地图
		getMapFile().delete();

		// 删除图片
		getImgFile().delete();

		super.delete();
	}

}
