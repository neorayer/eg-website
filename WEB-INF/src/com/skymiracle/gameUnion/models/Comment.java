package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.util.UUID;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;
import com.skymiracle.gameUnion.models.msg.CommentMsg;
import com.skymiracle.gameUnion.models.msg.CommentMsg.MsgType;
@Title("个人日志")
public  class Comment extends AbsMdo<Comment> {
	
	public static class X extends Mdo_X<Comment>{
		 
	}
	
	@Title("主键")
	private String id = UUID.randomUUID().toString();

	@Title("根贴ID")
	private String rootid = "";
	
	@Title("留言者")
	private String author;
	
	@Title("空间主人")
	private String owner;
	
	@Title("内容")
	private StringBuffer comment;

	
	@Title("是否公开")
	private boolean publiced =false;
	
	@Title("是否已读")
	public boolean readed = false;
	
	
	@Desc("普通 ｜ 通知 ｜ 新闻 ｜ 新闻评论 ｜ 回复 ｜ 视频 ｜ 视频回复 | 战报 |日志 ")
	public static enum ROOTTYPE  { 
		NORMAL,ALBUM,PHOTO,ARTICLE};
		
	@Title("文章类型")
	public ROOTTYPE type = ROOTTYPE.NORMAL;
	
	@Title("截取第一张图片的缩略图")
	public String labelPic;
	
	@Title("创建时间")
	private String createDate = CalendarUtil.getLocalDate();
	
	@Title("创建时间")
	private String createDateTime = CalendarUtil.getLocalDateTime();

	@Title("更新时间")
	private String updataDateTime = CalendarUtil.getLocalDateTime();
	
	@Title("更新时间")
	private String updataDate = CalendarUtil.getLocalDate();
	
	
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

	public String getUpdataDateTime() {
		return updataDateTime;
	}

	public void setUpdataDateTime(String updataDateTime) {
		this.updataDateTime = updataDateTime;
	}

	public String getUpdataDate() {
		return updataDate;
	}

	public void setUpdataDate(String updataDate) {
		this.updataDate = updataDate;
	}

	public String getLabelPic() {
		return labelPic;
	}

	public Comment() {
		super(CommentX);
	}
	
	public Comment(String id) {
		this();
		this.id = id;
	}
	public Comment(String author,String id) {
		this();
		this.author = author;
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
 
	 
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	public boolean getReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	public boolean getPubliced() {
		return publiced;
	}

	public void setPubliced(boolean publiced) {
		this.publiced = publiced;
	}
	public StringBuffer getComment() {
		return comment;
	}

	public void setComment(StringBuffer comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
 

	public ROOTTYPE getType() {
		return type;
	}

	public void setType(ROOTTYPE type) {
		this.type = type;
	}
 
	public String getRootid() {
		return rootid;
	}

	public void setRootid(String rootid) {
		this.rootid = rootid;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_comment";
	}
	public MList<Comment> getParentComments() throws AppException, Exception{
		return CommentX.find("parentid,createDateTime+", id, null);
	}
	public String getAvatorSmallUrl() throws AppException, Exception {
		User user = new User(this.author).load();
		return user.getAvatorSmallUrl();
	}
	public String getAuthorDN() throws AppException, Exception {
		User user = new User(this.author).load();
		return user.getDispName();
	}
	public String getOwnerDN() throws AppException, Exception {
		User user = new User(this.owner).load();
		return user.getDispName();
	}
	public void msg(MsgType msgType,String content,Comment comment) throws AppException, Exception {
		CommentMsg msg = new CommentMsg(Msg.EG_SYS, owner,msgType,this);
		msg.putContent(null, content);
		msg.create();
	}
	
}