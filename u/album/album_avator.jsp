<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>

</style>


<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<a href="../album/albums.jsplayout.vi"><strong>相册</strong></a>--<c:out value="${album.title}"/>
		</div>
		<div class="floatRight"><a href="javascript:Album.realbums();">相册列表&#62;&#62;</a></div>
	</div>
	<div id="page-photos-body-box">
		<ul id="page-photos-toolbar-box">
			<li><a href="../user/user_avator_edit.jsplayout.vi" >修改头像</a></li>
			<li><a href="../user/user_avator.jsplayout.vi"  >上传头像</a></li>
		</ul>
		
		<div style="clear: both;"></div>
		<ul id="page-album-avator-list">
			
			<c:if test='${empty photos}'>
				<li>
					<a href="../user/user_avator.jsplayout.vi"  class="empty-photo-link">你还没有头像，点击此处上传！</a>
				</li>
			</c:if>
			
			<c:if test='${not empty photos}'>
				<c:forEach var='photo' items='${photos}'>
					<li>
						<a  href="../album/photo_avator.jsplayout.vi?id=<c:out value="${photo.id}"/>">
							<img class="photos-img" src="<c:out value="${photo.avatorPhotoimg}" />" />
						</a>
						<br>
						<a href="javascript:" onclick="Album.setavator('<c:out value="${photo.id}"/>');"  class="">设置头像</a>
						<a href="javascript:" onclick="Album.delavator('<c:out value="${photo.id}"/>');" >删除</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
		
		<div style="clear: both;"></div>
		<br>
		<div align="right" id="page-photos-pagebar-box">
			<c:out	value="${pageBar}" escapeXml="false" />
		</div>
		
	</div>
</div>

 
<script type="text/javascript">
 
</script>