package com.skymiracle.gameUnion.models.post;

import java.util.UUID;

import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.util.CalendarUtil;

import static com.skymiracle.gameUnion.Singletons.*;

public class PubPost extends Mdo<PubPost>{

	public static class X extends Mdo_X<PubPost> {
		
	}
	
	private String id = UUID.randomUUID().toString();

	private static enum RangeType{
		global, hall, team
	}

	private RangeType rangeType = RangeType.global;
	
	private String rangeId;
	
	private String title;
	
	private StringBuffer content;
	
	private String postDate = CalendarUtil.getLocalDate();
	
	private String postDatetime= CalendarUtil.getLocalDateTime();
	
	private String postUsername;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public RangeType getRangeType() {
		return rangeType;
	}

	public void setRangeType(RangeType rangeType) {
		this.rangeType = rangeType;
	}

	public String getRangeId() {
		return rangeId;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
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

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostDatetime() {
		return postDatetime;
	}

	public void setPostDatetime(String postDatetime) {
		this.postDatetime = postDatetime;
	}

	public String getPostUsername() {
		return postUsername;
	}

	public void setPostUsername(String postUsername) {
		this.postUsername = postUsername;
	}

	public PubPost() {
		super(PubPostX);
	}
	
	@Override
	public String[] keyNames() {
		return new String[]{"id"};
	}

	@Override
	public String table() {
		return "tb_hallpost";
	}

}
