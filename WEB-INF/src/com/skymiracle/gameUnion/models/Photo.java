package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.skymiracle.image.*;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;

public class Photo extends AbsMdo<Photo> {

	@Title("主键")
	private String id = UUID.randomUUID().toString();

	@Title("主键")
	private String albumid;

	@Title("上传者")
	private String username;

	@Title("创建时间")
	private String createDate = CalendarUtil.getLocalDate();

	@Title("创建时间")
	private String createDateTime = CalendarUtil.getLocalDateTime();

	@Title("更新时间")
	private String updataDate = CalendarUtil.getLocalDate();

	@Title("更新时间")
	private String updataDateTime = CalendarUtil.getLocalDateTime();

	@Title("标题")
	private String title;

	@Title("描述")
	private String description;

	@Title("是否做为封面")
	private boolean isCover = false;

	@Title("图片格式")
	private String imageFormat;

	@Title("图片路径")
	private String path;

	public Photo(String id, String albumid) {
		this();
		this.id = id;
		this.albumid = albumid;
	}

	public Photo() {
		super(PhotoX);
	}

	public Photo(String id) {
		this();
		this.id = id;
	}

	public String getUpdataDate() {
		return updataDate;
	}

	public void setUpdataDate(String updataDate) {
		this.updataDate = updataDate;
	}

	public String getUpdataDateTime() {
		return updataDateTime;
	}

	public void setUpdataDateTime(String updataDateTime) {
		this.updataDateTime = updataDateTime;
	}

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsCover() {
		return isCover;
	}

	public void setIsCover(boolean isCover) {
		this.isCover = isCover;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getAlbumid() {
		return albumid;
	}

	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_photo";
	}

	public static class X extends Mdo_X<Photo> {
		private String fileStoreRootPath = "/gf/tomcat/webapps/51bisaifiles/";

		public String getFileStoreRootPath() {
			return fileStoreRootPath;
		}

		public void setFileStoreRootPath(String fileStoreRootPath) {
			this.fileStoreRootPath = fileStoreRootPath;
		}

		public void setAllCover(String username, String id)
				throws AppException, Exception {
			MList<Photo> photos = PhotoX.find("username,albumid,isCover",
					username, id, true);
			for (Photo aphoto : photos) {
				aphoto.update("isCover", false);
			}
		}

	}

	public Photo update(MdoMap mdoMap) throws AppException, Exception {
		// 相册更新
		// Album album=new Album(this.albumid,username).load();
		// album.update(new MdoMap());
		// 图片更新
		mdoMap.put("updataDateTime", CalendarUtil.getLocalDateTime());
		mdoMap.put("updataDate", CalendarUtil.getLocalDate());
		return super.update(mdoMap);
	}

	public String getPhotoPicFile() throws AppException, Exception {
		File logoFile = getFilesDir();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/" + this.albumid + "/"
					+ this.id + "Upload?" + logoFile.lastModified();
		return "/51bisaifiles/user/default.gif";
	}

	private String getFormatNames(Object o) throws AppException, Exception {
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(o);
			Iterator iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				return null;
			}
			ImageReader reader = (ImageReader) iter.next();
			iis.close();
			return reader.getFormatName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 

	public void delete() throws AppException, Exception {
		if (path != null) {
		 
			File file = getUploadAvatorFile();
			Logger.debug("delete smallPic path=" + file.getPath() + " is Exist="
					+ file.exists());
			file.delete();
			File scalefile = getScaleAvatorFile();
			Logger.debug("delete smallPic path=" + scalefile.getPath() + " is Exist="
					+ scalefile.exists());
			scalefile.delete();
		}
		if (isCover) {
			MList<Photo> photos = PhotoX.find("username,albumid", username,
					albumid);
			if (photos.size() == 0)
				return;
			photos.getFirst().update("isCover", true);
		}
		super.delete();
	}

	public void delavator() throws AppException, Exception {
		if (path != null) {
			// 删除普通头像
			File generalavator = getGeneralAvatorFile();
			Logger.debug("delete pic path=" + generalavator.getPath() + " is Exist="
					+ generalavator.exists());
			generalavator.delete();
			// 删除小头像
			File smallavator = getSmallAvatorFile();
			Logger.debug("delete pic path=" + smallavator.getPath() + " is Exist="
					+ smallavator.exists());
			smallavator.delete();
			// 删除原始头像
			File uploadavator =getUploadAvatorFile();
			Logger.debug("delete pic path=" + uploadavator.getPath()+ " is Exist="
					+ uploadavator.exists());
			uploadavator.delete();
		}
		if (isCover) {
			// 删除user头像
			File avator = getUserAvatorFile();
			Logger.debug("delete pic path=" + path + " is Exist="
					+ avator.exists());
			avator.delete();
			// 删除user最小头像
			File smallavator = getUserSmallAvatorFile();
			Logger.debug("delete pic path=" + path + " is Exist="
					+ smallavator.exists());
			smallavator.delete();
		}

		super.delete();
	}

	private File getFilesDir() {
		return new File(PhotoX.fileStoreRootPath + "/" + this.username + "/"
				+ this.albumid + "/");
	}


	public void setPhotoPicPath(File srcFile) throws AppException, Exception {
		/*
		 * 存放在头像相册目录下 已photo.id命名
		 */
		String  format=getFormatNames(srcFile);

		File filesDir = getFilesDir();
		filesDir.mkdirs();
		File logoFile = getUploadAvatorFile();
		StreamPipe.fileToFile(srcFile, logoFile);

		/*
		 * 修改信息
		 */
		MdoMap mdomap = new MdoMap();
		mdomap.put("path", logoFile.getAbsolutePath());
		mdomap.put("imageFormat", format);
		update(mdomap);
		/*
		 * 缩图
		 */
		SkyImage si=new SkyImageImpl(srcFile.getAbsolutePath());
		si.scale(100,100);
		si.saveAs(srcFile, getFormatname(format));
		Logger.debug("tempfilepath="+srcFile.getAbsolutePath());
		StreamPipe.fileToFile(srcFile, getScaleAvatorFile());
		
	}
	public int  getFormatname(String formatname) {
		if(formatname.equalsIgnoreCase("gif")){
			return SkyImage.FORMAT_GIF;
		}else if(formatname.equalsIgnoreCase("bmp")){
			return SkyImage.FORMAT_BMP;
		}else{
			return SkyImage.FORMAT_JPG;
		}
	}
	/*
	 * 原始头像 @return
	 */
	public File getUploadAvatorFile() {
		String filename = this.id + "Upload";
		return new File(getFilesDir(), filename);
	}
	/*
	 * 原始缩小头像 @return
	 */
	public File getScaleAvatorFile() {
		String filename = this.id ;
		return new File(getFilesDir(), filename);
	}
	/*
	 * avator--截取头像
	 */
	public File getGeneralAvatorFile() {
		String filename = this.id;
		return new File(getFilesDir(), filename);
	}

	/*
	 * avator--最小头像
	 */
	private File getSmallAvatorFile() {
		String filename = this.id + "Small";
		return new File(getFilesDir(), filename);
	}

	/*
	 * user 截取头像
	 */
	public File getUserAvatorFile() {
		String filename = this.username + ".jpg";
		return new File(getFilesDir(), filename);
	}

	/*
	 * user最小头像
	 */
	public File getUserSmallAvatorFile() {
		String filename = this.username + "Small.jpg";
		return new File(getFilesDir(), filename);
	}

	public void cutAvator(int persent, int x, int y, int w, int h)
			throws AppException, Exception {
		File updatafile = getUploadAvatorFile();

		/*
		 * 存放图片文件
		 */
		File generalfile = getGeneralAvatorFile();
		StreamPipe.fileToFile(updatafile, generalfile);

		SkyImage si = new SkyImageImpl(generalfile.getAbsolutePath());
		double r = (double) persent / 100;
		si.scale(r, r);
		si.cut(x, y, w, h);
		si.saveAs(generalfile, SkyImage.FORMAT_JPG);

		/*
		 * 更替头像文件
		 */
		File userfile = getUserAvatorFile();
		StreamPipe.fileToFile(generalfile, userfile);

	}

	public void cutAvatorSmall(int persent, int x, int y, int w, int h)
			throws AppException, Exception {
		File updatafile = getUploadAvatorFile();

		/*
		 * 存放图片文件
		 */
		File generalfile = getSmallAvatorFile();
		StreamPipe.fileToFile(updatafile, generalfile);

		SkyImage si = new SkyImageImpl(generalfile.getAbsolutePath());
		double r = (double) persent / 100;
		si.scale(r, r);
		si.cut(x, y, w, h);
		si.saveAs(generalfile, SkyImage.FORMAT_JPG);

		/*
		 * 更替头像文件
		 */
		File userfile = getUserSmallAvatorFile();
		StreamPipe.fileToFile(generalfile, userfile);
	}

	public void setAvator() throws IOException {
		// TODO Auto-generated method stub
		StreamPipe.fileToFile(getGeneralAvatorFile(), getUserAvatorFile());
		StreamPipe.fileToFile(getSmallAvatorFile(), getUserSmallAvatorFile());

	}
 
	 
	/*
	 * 显示图片缩小图
	 * 显示头像截图
	 */
	public String getScalePhotoimg() throws AppException, Exception {
		File scalefile = getScaleAvatorFile();
		File uploadfile = getUploadAvatorFile();
		if (scalefile.exists())
			return "/51bisaifiles/" + this.username + "/" + this.albumid + "/"
			+ this.id + "?" + scalefile.lastModified();
		else if(uploadfile.exists())
			return "/51bisaifiles/" + this.username + "/" + this.albumid + "/"
			+ this.id + "Upload?" + scalefile.lastModified();
		return "/51bisaifiles/album/emptyphoto.jpg";
	}
	/*
	 * 显示头像最小截图
	 */
	public String getSmallPhotoimg() throws AppException, Exception {
		File logoFile = getUploadAvatorFile();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/" + this.albumid + "/"
			+ this.id + "Small?" + logoFile.lastModified();
		return "/51bisaifiles/user/default.jpg";
	}
	
	/*
	 * 显示截图头像
	 * album_avator.jsp
	 */
	public String getAvatorPhotoimg() {
		File logoFile = getGeneralAvatorFile();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/" + this.albumid + "/"
			+ this.id + "?" + logoFile.lastModified();
		return "/51bisaifiles/user/default.jpg";
		
	}
	/*
	 * 显示原始图片
	 * photo.jsp
	 */
	public String getPhotoimg() throws AppException, Exception {
		File logoFile = getUploadAvatorFile();
		if (logoFile.exists())
			return "/51bisaifiles/" + this.username + "/" + this.albumid + "/"
					+ this.id + "Upload?" + logoFile.lastModified();
		return "/51bisaifiles/user/errphoto.jpg";
	}
	public MList<Comment> getComments() throws AppException, Exception {
		return CommentX.find("owner,rootid,createDateTime+",username, id);
	}

	public Album getAlbum() throws AppException, Exception {
		return new Album(albumid,username).load();
	}
}
