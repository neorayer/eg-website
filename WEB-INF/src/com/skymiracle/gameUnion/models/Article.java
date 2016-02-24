package com.skymiracle.gameUnion.models;

import static com.skymiracle.gameUnion.Singletons.*;

import java.util.UUID;

import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.MdoMap;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.UniqueConstraintException;
import com.skymiracle.mdo5.Mdo.Title;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;
import com.skymiracle.gameUnion.*;
import com.skymiracle.gameUnion.models.FreshMsg.MSGTYPE;
@Title("个人日志")
public  class Article extends AbsMdo<Article> {
	
	public static class X extends Mdo_X<Article>{
		 
	}
	
	@Title("主键")
	private String id = UUID.randomUUID().toString();

	@Title("标题")
	private String title;

	@Title("内容")
	private StringBuffer content;

	@Title("发布者")
	private String author;

	@Title("根贴ID")
	private String rootId = "";

	@Title("是否被收藏")
	private boolean isCollected = false;
	
	
	@Desc("普通 ｜ 通知 ｜ 新闻 ｜ 新闻评论 ｜ 回复 ｜ 视频 ｜ 视频回复 | 战报 |日志 ")
	public static enum TYPE  { 
		normal, notice, news, newsFollow, reply , video ,videoFollow ,report,blog};
	@Title("文章类型")
	public TYPE type = TYPE.normal;
	
	@Desc("综合 | 快讯 | 公告 | 热点")
	public static enum NEWSTYPE {alltype,flashinfo,post,hot};
	
	@Title("新闻类型")
	public NEWSTYPE newsType = NEWSTYPE.alltype;
	
	
	@Desc("普通 ｜ 良好 | 精华")
	public static enum LEVEL { Normal, Good, Excellent };
	@Title("文章级别")
	private LEVEL level = LEVEL.Normal;

	@Title("好评数����")
	private int goodVote;

	@Title("差评数����") 
	private int badVote;

	@Title("阅读数") 
	private int readCount;
	
	@Title("收藏数")
	private int bookmarkCount;
	
	@Title("视频代码")
	private StringBuffer videoCode;
	
	
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

	public Article() {
		super(ArticleX);
	}
	
	public Article(String id) {
		this();
		this.id = id;
	}
	public Article(String author,String id) {
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getBookmarkCount() {
		return bookmarkCount;
	}

	public void setBookmarkCount(int bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}
	public NEWSTYPE getNewsType() {
		return newsType;
	}

	public void setNewsType(NEWSTYPE newsType) {
		this.newsType = newsType;
	}

	public StringBuffer getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(StringBuffer videoCode) {
		this.videoCode = videoCode;
	}
	public boolean getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public LEVEL getLevel() {
		return level;
	}

	public void setLevel(LEVEL level) {
		this.level = level;
	}

	public int getGoodVote() {
		return goodVote;
	}

	public void setGoodVote(int goodVote) {
		this.goodVote = goodVote;
	}

	public int getBadVote() {
		return badVote;
	}

	public void setBadVote(int badVote) {
		this.badVote = badVote;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_article";
	}
	
	
	
	// 获取这个文章的跟贴数
	public long getFollowsCount() throws AppException, Exception {
		return ArticleX.count("rootid", id);
	}
	//截取新闻内容前200个字
	
	public String getTitle14(){
		return getFieldByLength(title,14);
	}
	public String getTitle18(){
		return getFieldByLength(title,18);
	}
	public String getTitle10(){
		return getFieldByLength(title,10);
	}
	public String getContent25(){
		return getFieldByLength(content.toString(),25);
	}
	public String getContent52(){
		String c=StringUtil.removeNbsp(content.toString());
		return getFieldByLength(c,52);
	}
	/**
	 * 新闻列表简短正文
	 * @return 200个左右的字
	 */
	public String getContent200(){
		String content300=StringUtil.removeNbsp(content.toString());
		if(content==null)
			return "";
		if(content.length()<=300)
			return content.toString();
		StringBuffer retSb=new StringBuffer();
		
		String content200=content300.substring(0, 200);
			retSb.append(content200);
		String last100=content300.substring(200);
		  	int spliter=last100.indexOf(">")!=-1?last100.indexOf(">"):0;
		String remainStr=last100.substring(0,spliter);
			retSb.append(remainStr);
		String lastStr=last100.substring(spliter);
		try {
			retSb.append(StringUtil.extractText(lastStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retSb.toString();
	}
 
	public String getContent100(){
		return getFieldByLength(this.content.toString(),100);
	}
	
	private String getFieldByLength(String field,int length){
		if(field==null)
			return "";
		try{
			field = StringUtil.extractText(field);
		}catch(Exception e){
			e.printStackTrace();
		}
		return field.length()>length?field.substring(0,length).toString()+"..":field.toString();
	}
	 
	public void deleteReport() throws AppException, Exception{
		super.delete();
	}
	public Article update(MdoMap mdoMap) throws AppException, Exception {
		mdoMap.put("updataDateTime", CalendarUtil.getLocalDateTime());
		mdoMap.put("updataDate", CalendarUtil.getLocalDate());
		return super.update(mdoMap);
	}
	public Article create() throws AppException, Exception {
		MList<UserFriend> friends=UserFriendX.find("username,state", author,UserFriend.STATE.PASSED);
		for (UserFriend friend:friends) {
			friend.msgArticle(MSGTYPE.ARTICLE, " ${username} 发表新的个人日志 ${title} ", this);
		}
		return super.create();
	}
	public MList<Comment> getComments() throws AppException, Exception {
		return CommentX.find("owner,rootid,createDateTime+",author, id);
	}
	
}