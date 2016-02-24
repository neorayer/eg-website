<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong><a href="../article/articles.jsplayout.vi">战斗日记</a>--<c:out value="${article.title}"/></strong>
		</div>
		<div class="floatRight"><input type="button" title="写新日志" value="写新日志" onclick="Article.add();" class="button"/></div>
	</div>
	
	<div style="clear: both;"></div>
	
	<div id="page-photos-body-box">
		<div id="article-box">
			<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
			<h3 class="page-article-title-box">
				<span class="editarticle">
					<a href="javascript:" onclick="Article.mod('<c:out value="${article.id}"/>');" >&nbsp;编辑&nbsp;</a>
					|
					<a href="javascript:" onclick="Article.del('<c:out value="${article.id}" />');">&nbsp;删除&nbsp;</a>
					|
					<a href="../article/articles.jsplayout.vi" onclick="">&nbsp;返回&nbsp;</a>
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
			
			<div class="page-article-content-box">
				<c:out value='${article.content}' escapeXml='false'/>
			</div>
			<div class="page-article-footer">
				评论(<c:out value="${article.readCount}"/>)  
			</div>
			<div style="clear:both"></div>
		</div>
	</div>
</div>
	
 

<script type="text/javascript">

$(document).ready(function(){
	
 
});
</script>
