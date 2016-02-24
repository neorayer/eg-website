<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>

</style>
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft"><strong>相册</strong></div>
		<div class="floatRight"><a href="../user/index.jsplayout.vi">首页&#62;&#62;</a></div>
	</div>
	<div id="allvisitor-page-body">
		<div class="allvisitor-page-top-bar">
			<div class="floatRight">
				<a href="javascript:Album.create();"/>新建相册</a>
			</div>
		</div>
		<ul>
			<c:forEach var="album" items="${albums}">
			
					<c:if test="${album.id eq '1'}">
						<li>
							<div class="allvisitor-page-album floatLeft">
								<a href="../album/album_avator.jsplayout.vi">
									<img src="<c:out value="${album.coverPicUrl}" />" />
								</a>	
							</div>
							<div class="allvisitor-page-albumright floatRight">
								<div class="allvisitor-page-name">
									<a   href="../album/album_avator.jsplayout.vi"> 头像相册 </a>
									&#32; &#40;<c:out value="${album.photocount}"/>张&#41;
								</div>
							</div>
						</li>
					</c:if>
					
					<c:if test="${album.id ne '1'}">
						<li>
							<div class="allvisitor-page-album floatLeft">
								<a href="../album/photos.jsplayout.vi?id=<c:out value="${album.id}" />">
									<img src="<c:out value="${album.coverPicUrl}" />" />
								</a>	
							</div>
							<div class="allvisitor-page-albumright floatRight">
								<div class="allvisitor-page-name">
									<a href="../album/photos.jsplayout.vi?id=<c:out value="${album.id}" />">
										<c:out value="${album.title}"/>
									</a>&#32; &#40;<c:out value="${album.photocount}"/>张&#41;</div>
									<div class="allvisitor-page-name"><a href="javascript:Album.modalbum('<c:out value="${album.id}"/>');" >编辑</a>
									<a href="javascript:" onclick="Album.delalbum('<c:out value="${album.id}"/>');">删除</a></div>
							</div>
						</li>
					</c:if>
			</c:forEach>
			
		</ul>
		<div style="clear:both"></div>
		<c:if test='${albumcount>16}'>
			<div id="allvisitors-pagebar">
				<c:out	value="${pageBar}" escapeXml="false" />	
			</div>
		</c:if>
	</div>
</div>