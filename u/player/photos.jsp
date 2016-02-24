<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>
 

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<a href="../player/albums.jsplayout.vi?username=<c:out value='${player.username}'/>">
				<c:out value='${player.dispName}'/>
			</a>
			的相册--<c:out value="${album.title}"/>
		</div>
		<div class="floatRight">
			<a href="../player/index.jsplayout.vi?username=<c:out value='${player.username}'/>"><c:out value='${player.dispName}'/>的首页&#62;&#62;</a>
		</div>
	</div>
	<div id="page-photos-body-box">
		<ul id="page-photos-list">
			<c:if test='${empty photos}'>
				<li>
					没有相片！
				</li>
			</c:if>
			<c:if test='${not empty photos}'>
				<c:forEach var='photo' items='${photos}'>
					<li>
						<a  href="../player/photo.jsplayout.vi?username=<c:out value='${player.username}'/>&id=<c:out value="${photo.id}"/>">
							<img class="photos-img" src="<c:out value="${photo.scalePhotoimg}" />" />
						</a>
						<br>
						<!--<a href="javascript:" onclick="Album.setcover('<c:out value="${photo.id}"/>','<c:out value="${photo.albumid}"/>');" >设置封面</a>
						<a href="javascript:" onclick="Album.delphoto('<c:out value="${photo.id}"/>','<c:out value="${photo.albumid}"/>');" >删除</a>
					--></li>
				</c:forEach>
			</c:if>
		</ul>
		
		<div style="clear: both;"></div>
		
		<div align="right" id="page-photos-pagebar-box">
			<c:out	value="${pageBar}" escapeXml="false" />
		</div>
		<c:if test="${album.id ne '1'}">
			<div align="left" id="page-photos-album-info-box">
				描述：<br>
				 &nbsp;&nbsp;&#34;<c:out value="${album.description}" escapeXml="fasle"/>&#34;&nbsp;&nbsp;
				<br>
				创建于：<c:out value="${album.createDate}"/><br>
				更新：<c:out value="${album.updataDate}"/>
			</div>
		</c:if>
	</div>
</div>