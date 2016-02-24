package com.skymiracle.gameUnion.models.post;

import com.skymiracle.mdo5.Mdo;

public class PostReadLog extends Mdo<PostReadLog>{

	private String postId;
	
	private String username;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String[] keyNames() {
		return new String[]{"postid", "username"};
	}

	@Override
	public String table() {
		return "tb_postreadlog";
	}
	
	
}
