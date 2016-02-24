package com.skymiracle.gameUnion.controllers;

import static com.skymiracle.gameUnion.Singletons.*;

import java.io.File;
import java.util.UUID;

import com.skymiracle.gameUnion.models.*;
import com.skymiracle.image.SkyImageImpl;
import com.skymiracle.io.StreamPipe;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.PagedList;
import com.skymiracle.sor.ActResult;
import com.skymiracle.sor.annotation.Sessioned;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;
import com.skymiracle.gameUnion.models.FreshMsg.MSGTYPE;

public class U_Ctr_Article  extends U_Ctr {
	@Sessioned
	public void vi_articles(ActResult r) throws AppException, Exception {
		int pageNum = $i("pageNum", 1);
		int perPage = $i("perPage", 10); 
		PagedList<Article> articles = ArticleX.findPaged(pageNum, perPage, "author,updatadatetime-", actorId,null);
		long count=ArticleX.count("author", actorId);
		articles.setLinkPrefix("");
		r.putMap("articlecount",count);
		r.putMap("articles", articles);
		r.putMap("pageBar", articles.getPageBarHTML());
	}
	
	
	public void vi_article(ActResult r) throws AppException, Exception {
		r.putMap("article",$M(Article.class).load());
	}
	@Sessioned
	public void vi_article_addormod(ActResult r) throws  AppException,  Exception {
		r.putMap("systime", CalendarUtil.getLocalDate());
		if ("mod".equals($("act"))) {
			r.putMap("article", new Article($("id")).load());
		}
	}
	@Sessioned
	public void do_article_addormod(ActResult r) throws  AppException,  Exception {
		if ("mod".equals($("act"))) {
			Article article=new Article($("id")).load();
			article.update($MM(Article.class));
			
		} else {
			Article ar = $M(Article.class);
			ar.setId(UUID.randomUUID().toString());
			ar.setAuthor(actorId);
			ar.setType(Article.TYPE.blog);
			ar.create();
			
		}
		r.setRedirectTo("../article/articles.jsplayout.vi");
	}
	@Sessioned
	public void do_articleattach_add(ActResult r) throws  AppException,  Exception {
		File f = $File();

		Attachment att = new Attachment();
		File savedFile = new File("/tmp/", att.getId());
		att.setPath(savedFile.getAbsolutePath());

		StreamPipe.fileToFile(f, savedFile);
		att.create().load();
		r.putMap("attach", att);
	}
	public void vi_articleAttachImage(ActResult r) throws AppException, Exception {
		Attachment att = $M(Attachment.class);
		att.load();
		r.setImage(new SkyImageImpl(att.getPath(), "jpg").getImage());
	}
	@Sessioned
	public void do_article_del(ActResult r) throws AppException, Exception {
		new Article($("id")).delete();
	}
}