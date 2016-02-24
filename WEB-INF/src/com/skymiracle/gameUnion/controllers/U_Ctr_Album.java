
package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import java.util.Random;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.gameUnion.models.msg.UserMsg;
import com.skymiracle.image.SkyImage;
import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.gameUnion.models.FreshMsg.MSGTYPE;
public class U_Ctr_Album extends U_Ctr {
	
	@Sessioned
	public void vi_albums(ActResult r) throws  AppException,  Exception {
		PagedList<Album> albums = AlbumX.findPaged($i("pageNum", 1), $i("perPage", 16), "username,updatadatetime-", actorId,null);
		long count=AlbumX.count("username", actorId);
		albums.setLinkPrefix("");
		r.putMap("albums", albums);
		r.putMap("albumcount", count);
		r.putMap("pageBar", albums.getPageBarHTML());
		
	}
	@Sessioned
	public void vi_photos(ActResult r) throws  AppException,  Exception {
		Album album=new Album($("id"),actorId).load();
		r.putMap("album",album);
		PagedList<Photo> photos = (PagedList<Photo>) album.getPhoto($i("pageNum", 1), $i("perPage", 15));
		
		photos.setLinkPrefix("?id="+album.getId());
		r.putMap("photos",photos);
		r.putMap("pageBar", photos.getPageBarHTML());
		
	}
	@Sessioned
	public void vi_photo(ActResult r) throws  AppException,  Exception {
		Photo photo=new Photo($("id"),actorId).load();
		Album album=new Album(photo.getAlbumid(),actorId).load();
		r.putMap("album",album);
		r.putMap("photo",photo);
		
	}
	@Sessioned
	public void vi_photo_upload(ActResult r) throws  AppException,  Exception {
		r.putMap("album",new Album($("id"),actorId).load());
	}
	@Sessioned
	public void vi_album_create(ActResult r) throws  AppException,  Exception {
	}
	
	@Sessioned
	public void do_album_create(ActResult r) throws AppException, Exception {
		Album album=$M(Album.class);
		album.setUsername(actorId);
		album.create();
		
	}
	
	
	@Sessioned 
	public void do_photo_upload(ActResult r) throws  AppException,  Exception {
		Album album=new Album($("id"),actorId).load();
		for (int i=0;i<$$File().length;i++) {
			album.uploadphoto($$File()[i],$("description"+i) );
		}
		if (album.getType().equals("Normal")) {
			MList<UserFriend> friends = UserFriendX.find("username,state",
					actorId, UserFriend.STATE.PASSED);
			for (UserFriend friend : friends) {
				friend.msgAlbum(MSGTYPE.ALBUM, " ${username} 上传了 "+$$File().length+" 张照片至  ${title}",
						album,$$File().length);
			}
			album.update($MM(Album.class));
		}
	} 
	
	public void vi_album_mod(ActResult r) throws  AppException,  Exception {
		r.putMap("album",new Album($("id"),actorId).load());
	}
	@Sessioned
	public void do_album_mod(ActResult r) throws  AppException,  Exception {
		new Album($("id"),actorId).load().update($MM(Album.class));
		
	}
	public void vi_photoimg(ActResult r) throws AppException, Exception {
	}
	
	public void vi_photo_mod(ActResult r) throws  AppException,  Exception {
		r.putMap("albums",actor.findAlbum());
		Photo photo=new Photo($("id"),actorId).load();
		Album album=new Album(photo.getAlbumid(),actorId).load();
		r.putMap("curalbum",album);
		r.putMap("photo",photo);
	}
	
	@Sessioned
	public void do_photo_mod(ActResult r) throws  AppException,  Exception {
		Photo photo=new Photo($("id"),actorId).load();
		if ($b("iscover")) {
			PhotoX.setAllCover(actorId,$("albumid"));
		}
		photo.update($MM(Photo.class));
	}
	
	@Sessioned
	public void do_album_del(ActResult r) throws AppException, Exception {
		new Album($("id"),actorId).load().delete();
	}
	
	
	
	@Sessioned
	public void vi_album_avator(ActResult r) throws  AppException,  Exception {
		Album album=new Album("1",actorId).load();
		r.putMap("album",album);
		PagedList<Photo> photos = (PagedList<Photo>) album.getPhoto($i("pageNum", 1), $i("perPage", 15));
		photos.setLinkPrefix("");
		r.putMap("photos",photos);
		r.putMap("pageBar", photos.getPageBarHTML());
	}
	@Sessioned
	public void vi_photo_avator(ActResult r) throws  AppException,  Exception {
		Photo photo=new Photo($("id"),actorId).load();
		Album album=new Album(photo.getAlbumid(),actorId).load();
		r.putMap("album",album);
		r.putMap("photo",photo);
	}
	@Sessioned
	public void do_photo_modDesc(ActResult r) throws AppException, Exception {
		Photo photo = $M(Photo.class).load();
		photo.update($MM(Photo.class));
		
	}
	@Sessioned
	public void do_photo_del(ActResult r) throws AppException, Exception {
		$M(Photo.class).load().delete();
	}
	@Sessioned
	public void do_photo_delavator(ActResult r) throws AppException, Exception {
		$M(Photo.class).load().delavator();
	}
	
	
	
	@Sessioned
	public void vi_avator(ActResult r) throws  AppException,  Exception {
		r.putMap("album", new Album("1",actorId).load());
	}
	@Sessioned
	public void do_avator_upload(ActResult r) throws AppException, Exception {
		Album album= new Album("1",actorId).load();
		PhotoX.setAllCover(actorId,album.getId());
		album.uploadAvator($File());
		
		MList<UserFriend> friends = UserFriendX.find("username,state",
				actorId, UserFriend.STATE.PASSED);
		for (UserFriend friend : friends) {
			friend.msgAvator(MSGTYPE.AVATOR, " ${username} 更新了自己的头像");
		}
		
	}
	@Sessioned
	public void do_user_avator_edit(ActResult r) throws AppException, Exception {
		Photo photo = $M(Photo.class);
		if (photo.exists()) {
			photo.load();
		}else{
			photo= new Photo();
			photo.setAlbumid("1");
			photo.setIsCover(true);
			photo.create();
		}
		photo.cutAvator($i("persent"), $i("x"), $i("y"), $i("w"), $i("h"));
		 
	}
	@Sessioned
	public void do_user_avatorSmall_edit(ActResult r) throws  AppException,  Exception {
		Photo photo = $M(Photo.class).load();
		photo.cutAvatorSmall($i("persent1"), $i("x1"), $i("y1"), $i("w1"), $i("h1"));
	}
	@Sessioned
	public void do_photo_setcover(ActResult r) throws AppException, Exception {
		Photo photo = $M(Photo.class).load();
		PhotoX.setAllCover(actorId,photo.getAlbumid());
		photo.update("isCover", true);
		r.setRedirectTo("../user/index.jsplayout.vi");
	}
	@Sessioned
	public void do_photo_setavator(ActResult r) throws AppException, Exception {
		Photo photo = $M(Photo.class).load();
		PhotoX.setAllCover(actorId,photo.getAlbumid());
		photo.update("isCover", true);
		photo.setAvator();
		r.setRedirectTo("../user/index.jsplayout.vi");
	}

	
}
