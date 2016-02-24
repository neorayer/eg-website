package com.skymiracle.gameUnion.models.hall;

import java.io.File;
import java.io.IOException;

import com.skymiracle.io.StreamPipe;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.sor.exception.AppException;

import static com.skymiracle.gameUnion.Singletons.*;
public class Hall extends Mdo<Hall> {
	public static class X extends Mdo_X<Hall> {

		private String fileStoreDir = "/tmp";

		public String getFileStoreDir() {
			return fileStoreDir;
		}

		public void setFileStoreDir(String fileStoreDir) {
			this.fileStoreDir = fileStoreDir;
		}

		public Hall getDefault() {
			return new Hall();
		}
		
	}
	@Title("大厅ID")
	private String id = "ko1";
	
	@Title("所属组织ID")
	private String orgId = "skymiracle";

	@Title("大厅名称")
	private String name = "KO电竞大厅";
	
	@Title("皮肤ID")
	private String skinId = "wow";
	
	public Hall() {
		super(HallX);
	}
	
	public Hall(String id) {
		this();
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkinId() {
		return skinId;
	}

	public void setSkinId(String skinId) {
		this.skinId = skinId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MList<HallMenuItem> getMenuItems() throws AppException, Exception {
		return HallMenuItemX.find("hallid,sortid+", id);
	}
	
	private File getFileStore() {
		File file =  new File(HallX.getFileStoreDir() + "/" + this.id);
		file.mkdirs();
		return file;
	}

	public File getLogoFile() {
		return new File(getFileStore(), "logo.jpg");
	}
	public void setLogo(File file) throws IOException {
		StreamPipe.fileToFile(file, getLogoFile());
	}


	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_hall";
	}

	
}
