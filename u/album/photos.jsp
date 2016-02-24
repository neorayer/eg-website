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
			<li><a  href="javascript:" onclick="Album.delalbum('<c:out value="${album.id}"/>');" >删除相册</a></li>
			<li><a href="javascript:Album.modalbum('<c:out value="${album.id}"/>');"  >编辑相册</a></li>
			<li><a href="../album/photo_upload.jsplayout.vi?id=<c:out value="${album.id}"/>"  >上传图片</a></li>
		</ul>
		
		<div style="clear: both;"></div>
		<ul id="page-photos-list">
			<c:if test='${empty photos}'>
				<li>
					<a href="../album/photo_upload.jsplayout.vi?id=<c:out value="${album.id}"/>"  class="empty-photo-link">你还没有上传没有相片，点击此处上传！</a>
				</li>
			</c:if>
			<c:if test='${not empty photos}'>
				<c:forEach var='photo' items='${photos}'>
					<li>
						<a  href="../album/photo.jsplayout.vi?id=<c:out value="${photo.id}"/>">
							<img class="photos-img" src="<c:out value="${photo.scalePhotoimg}" />" />
						</a>
						<br>
						<a href="javascript:" onclick="Album.setcover('<c:out value="${photo.id}"/>','<c:out value="${photo.albumid}"/>');" >设置封面</a>
						<a href="javascript:" onclick="Album.delphoto('<c:out value="${photo.id}"/>','<c:out value="${photo.albumid}"/>');" >删除</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
		
		<div style="clear: both;"></div>
		
		<div align="right" id="page-photos-pagebar-box">
			<c:out	value="${pageBar}" escapeXml="false" />
		</div>
		
		<div align="left" id="page-photos-album-info-box">
			描述：<br>
			 &nbsp;&nbsp;&#34;<c:out value="${album.description}" escapeXml="fasle"/>&#34;&nbsp;&nbsp;
			<br>
			创建于：<c:out value="${album.createDate}"/><br>
			更新：<c:out value="${album.updataDate}"/>
		</div>
	</div>
</div>