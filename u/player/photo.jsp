<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>



<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft"><strong><c:out value='${player.dispName}'/></strong>的相册
			--
			<a href="../player/photos.jsplayout.vi?username=<c:out value='${player.username}'/>&id=<c:out value="${album.id}" />"><c:out value="${album.title}"/></a>
			</div>
		<div class="floatRight"><a href="../player/index.jsplayout.vi?username=<c:out value='${player.username}'/>"><c:out value='${player.dispName}'/>的首页&#62;&#62;</a></div>
	</div>
	<div id="page-photos-body-box">
		<div id="page-photo-list" align="center">
			 <img src="<c:out value="${photo.photoimg}" />" class="photo-img"  />
			<br>
			
			<div align="center" id="page-photo-avator-description-box">
				 <c:out value='${photo.description}'/>
			</div>
		</div>
		
		<div style="clear: both;"></div>
		
		
		<div align="left" id="page-photos-album-info-box">
			<ul id="page-photo-footer-box">
				<li>
					来自<c:out value="${username}"/>的相册:<a href="../player/photos.jsplayout.vi?username=<c:out value='${player.username}'/>&id=<c:out value="${album.id}" />"><c:out value="${album.title}" /></a>
				</li>
				<li>
					上传于:	<c:out value='${photo.createDateTime}'/>
				</li>
			</ul>
			<div style="clear: both;"></div>
		</div>
		
	</div>
</div>


 
<script type="text/javascript">

$(document).ready(function(){
	 
});
 
</script>