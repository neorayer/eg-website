<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!-- 
<div id="page-articles-box"  class="">
	<h3 class="hd-box green">
		<span class="h3-left">
			<img src="../_css/images/article/atr-icon.gif" /> 
		</span>
		<span class="h3-center">
			我的日志-<label class="label">日志列表</label>
		</span>
		<span class="h3-right">
			<input type="button" title="写新日志" value="写新日志" onclick="Article.add();" class="button"/>
		</span>
	</h3>
	<div style="clear: both;"></div>
	
	<div id="page-articles-body-box" class="bd-box">
	
		<c:if test="${empty articles}">
			<div align="center" class="empty-box">
				你当前没有日志&nbsp;
				<a href="javascript:Article.add();">点击此处，添加你的个人日志！</a>
			</div>
		</c:if>
		
		<c:if test="${not empty articles}">
			<ul  id="page-articles-list">
				<c:forEach var="article" items="${articles}">
				<li>
					<h3 class="page-article-title-box">
						<span class="editarticle">
							<a href="javascript:Article.mod('<c:out value="${article.id}"/>');" >&nbsp;编辑&nbsp;</a>
							|
							<a href="javascript:Article.del('<c:out value="${article.id}" />')" onclick="">&nbsp;删除&nbsp;</a>
						</span>
						<a href="../article/article.jsplayout.vi?id=<c:out value="${article.id}"/>">
							<c:out value="${article.title}"/>	
						</a>
						<br>
						<span>
							<c:out value="${article.createDateTime}"/>发表   
						</span>
					</h3>
					
					<div style="clear:both;"></div>
					
					<div class="page-article-content-box small" >
						<c:out value='${article.content200}' escapeXml='false'/>
						<br>
						<a href="../article/article.jsplayout.vi?id=<c:out value="${article.id}"/>">阅读全文...</a>
					</div>
					<div class="page-article-footer">
						评论(<c:out value="${article.readCount}"/>)  
					</div>
				</li>
				</c:forEach>
			</ul>
			
			<div id="page-articles-pageBar-box"align="right">
				<c:out value="${pageBar}" escapeXml="false" />
			</div>
			<div style="clear:both"></div>
		</c:if>
	</div>
</div>
 -->

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>战斗日记--列表</strong>
		</div>
		<div class="floatRight"><input type="button" title="写新日志" value="写新日志" onclick="Article.add();" class="button"/></div>
	</div>
	<div id="page-photos-body-box">
		<c:if test="${empty articles}">
			<div align="center" class="empty-box">
				你当前没有日志&nbsp;
				<a href="javascript:Article.add();">点击此处，添加你的个人日志！</a>
			</div>
		</c:if>
		
		<c:if test="${not empty articles}">
			<ul  id="page-articles-list">
				<c:forEach var="article" items="${articles}">
				<li>
					<h3 class="page-article-title-box">
						<span class="editarticle">
							<a href="javascript:Article.mod('<c:out value="${article.id}"/>');" >&nbsp;编辑&nbsp;</a>
							|
							<a href="javascript:Article.del('<c:out value="${article.id}" />')" onclick="">&nbsp;删除&nbsp;</a>
						</span>
						<a href="../article/article.jsplayout.vi?id=<c:out value="${article.id}"/>">
							<c:out value="${article.title}"/>	
						</a>
						<br>
						<span>
							<c:out value="${article.createDateTime}"/>发表   
						</span>
					</h3>
					
					<div style="clear:both;"></div>
					
					<div class="page-article-content-box small" >
						<c:out value='${article.content200}' escapeXml='false'/>
						<br>
						<a href="../article/article.jsplayout.vi?id=<c:out value="${article.id}"/>">阅读全文...</a>
					</div>
					<div class="page-article-footer">
						评论(<c:out value="${article.readCount}"/>)  
					</div>
				</li>
				</c:forEach>
			</ul>
			<c:if test='${articlecount>10}'>
				<div id="page-articles-pageBar-box"align="right">
					<c:out value="${pageBar}" escapeXml="false" />
				</div>
			</c:if>
			<div style="clear:both"></div>
		</c:if>
	</div>
</div>

 

<script type="text/javascript">

$(document).ready(function(){
	 
 
});
</script>
