package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.skymiracle.gameUnion.models.FreshMsg.MSGTYPE;
import com.skymiracle.logger.Logger;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;

public class Album extends AbsMdo<Album> {

	@Title("主键")
	private String id = UUID.randomUUID().toString();

	@Title("上传者")
	private String username;

	@Title("创建时间")
	private String createDate = CalendarUtil.getLocalDate();

	@Title("创建时间")
	private String createDateTime = CalendarUtil.getLocalDateTime();

	@Title("更新时间")
	private String updataDateTime = CalendarUtil.getLocalDateTime();

	@Title("更新时间")
	private String updataDate = CalendarUtil.getLocalDate();

	@Title("标题")
	private String title;

	@Title("描述")
	private String description;

	@Title("普通相册 | 头像相册 | 私人相册 | 密码相册")
	public static enum TYPE {
		Normal, Avatar, Personal, CodedLock
	};

	@Title("相册类型")
	private TYPE type = TYPE.Normal;

	@Title("相册密码")
	private String password;

	public Album() {
		super(AlbumX);
	}

	public Album(String id, String username) {
		this();
		this.id = id;
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
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

	@Override
	public String[] keyNames() {
		return new String[] { "id", "username" };
	}

	@Override
	public String table() {
		return "tb_album";
	}

	public static class X extends Mdo_X<Album> {

		public void addAvatorAlbum(User user) throws AppException, Exception {
			Album album = new Album();
			album.setId("1");
			album.setUsername(user.getUsername());
			album.setTitle("头像相册");
			album.create();
		}

		public void addAllAvatorAlbum() throws AppException, Exception {
			MList<User> users = UserX.findAll();
			for (User user : users) {
				Album album = new Album();
				album.setId("1");
				album.setUsername(user.getUsername());
				album.setTitle("头像相册");
				album.create();
			}
		}
	}

	private File getFilesDir() {
		return new File(PhotoX.getFileStoreRootPath() + "/" + this.username
				+ "/" + this.id + "/");
	}

	public void delete() throws AppException, Exception {
		File file = getFilesDir();
		Logger.debug("delete smallPic path=" + file.getPath() + " is Exist="
				+ file.exists());
		deletefile(file.getPath());
		super.delete();
	}

	/*
	 * 删除相册文件夹下所有的文件
	 */
	public static boolean deletefile(String delpath)
			throws FileNotFoundException, IOException {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory())
						delfile.delete();
					else if (delfile.isDirectory())
						deletefile(delpath + "\\" + filelist[i]);
				}
				file.delete();
			}
		} catch (FileNotFoundException e) {
			Logger.debug("deletefile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public MList<Photo> getPhoto(int pageNum, int countPerPage)
			throws AppException, Exception {
		return PhotoX.findPaged(pageNum, countPerPage,
				"username,albumid,updatadatetime-", username, id);
	}

	public Album update(MdoMap mdoMap) throws AppException, Exception {
		mdoMap.put("updataDateTime", CalendarUtil.getLocalDateTime());
		mdoMap.put("updataDate", CalendarUtil.getLocalDate());

		return super.update(mdoMap);
	}

	public void uploadphotos(File[] file) throws AppException, Exception {
		MList<Photo> photos = PhotoX.find("username,albumid", username, id,
				true);
		boolean cover = false;
		if (photos.size() == 0) {
			cover = true;
		}
		for (int i = 0; i < file.length; i++) {
			Photo photo = new Photo();
			photo.setUsername(username);
			photo.setIsCover(cover);
			photo.setAlbumid(id);
			photo.create();
			photo.setPhotoPicPath(file[i]);
		}

	}

	public void uploadphoto(File file, String description) throws AppException,
			Exception {
		MList<Photo> photos = PhotoX.find("username,albumid,iscover", username,
				id, true);
		boolean cover = false;
		if (photos.size() == 0) {
			cover = true;
		}

		Photo photo = new Photo();
		photo.setUsername(username);
		photo.setIsCover(cover);
		photo.setAlbumid(id);
		photo.setDescription(description);
		photo.create();
		photo.setPhotoPicPath(file);

	}

	/** 暂时存放头像** */
	public void uploadAvator(File srcFile) throws AppException, Exception {

		Photo photo = new Photo();
		photo.setUsername(username);
		photo.setIsCover(true);
		photo.setAlbumid(id);
		photo.create();
		photo.setPhotoPicPath(srcFile);

	}

	public Photo getCoverPhoto() throws AppException, Exception {
		MList<Photo> photos = PhotoX.find("username,albumid,isCover", username,
				id, true);
		return photos.size() == 0 ? new Photo() : photos.get(0);
	}

	public String getCoverPicUrl() throws AppException, Exception {
		Photo photo = getCoverPhoto();
		return photo.getScalePhotoimg();
	}

	public Album create() throws AppException, Exception {
		return super.create();
	}

	public int getPhotocount() throws AppException, Exception {
		return (int) PhotoX.count("username,albumid", username, id);
	}
	public MList<Comment> getComments() throws AppException, Exception {
		return CommentX.find("owner,rootid,createDateTime+",username, id);
	}
}
