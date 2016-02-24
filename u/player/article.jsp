<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>战斗日记--<c:out value="${article.title}"/></strong>
		</div>
		<div class="floatRight">
			<a href="javascript:history.back(-1)">返回>></a>
		</div>
	</div>
	
	<div style="clear: both;"></div>
	
	<div id="page-photos-body-box">
		<div id="article-box">
			<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
			<h3 class="page-article-title-box">
				<c:out value="${article.title}"/>	
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
