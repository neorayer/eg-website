package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.AttachmentX;

import java.awt.Image;
import java.io.File;
import java.util.UUID;

import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;
public class Attachment extends AbsMdo<Attachment> {

	public static class X extends Mdo_X<Attachment> {

		public X() {
			
		}
	}
	
	public Attachment() {
		super(AttachmentX);
	}
	
	public Attachment(String id){
		this();
		this.id = id;
	}

	private String id = UUID.randomUUID().toString();

	public static enum TYPE {
		image, reply, music, flv, map, other
	};

	private TYPE type = TYPE.image;

	private String path;

	private String teamId;

	private String uploaderId;

	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_attachment";
	}

	public Attachment create(File srcFile) throws AppException, Exception {
		File descFile = new File("/tmp/", this.id);
		path =  descFile.getAbsolutePath();

		StreamPipe.fileToFile(srcFile, descFile);

		return super.create();
	}

	public Image loadImg() throws AppException, Exception {
		load();
		return new SkyImageImpl(path, "jpg").getImage();
	}

}
