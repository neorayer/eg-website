<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>战友</strong>
		</div>
		<div class="floatRight">
			<a href="javascript:history.back(-1)">返回>></a>
		</div>
	</div>
	<div id="allvisitor-page-body">
		<ul>
			<c:forEach var='battlefriend' items='${bfs}'>
			<li>
				<div class="allvisitor-page-album floatLeft">
					<a href="../player/index.jsplayout.vi?username=<c:out value='${battlefriend.descname}'/>">
						<img src="<c:out value='${battlefriend.friend.avatorSmallUrl}'/>">
					</a>
				</div>
				<div class="allvisitor-page-albumright floatRight">
					<div class="allvisitor-page-name"><a href="../player/index.jsplayout.vi?username=<c:out value='${battlefriend.descname}'/>"><c:out value='${battlefriend.friend.dispName}'/></a></div>
					<div class="allvisitor-page-time">结交:<c:out value='${battlefriend.createDate}'/></div>
				</div>
			</li>
			</c:forEach>
		</ul>
		<div style="clear:both"></div>
		<c:if test='${bfCount>12}'>
			<div id="allvisitors-pagebar">
				<c:out value='${pageBar}' escapeXml="false"/>		
			</div>
		</c:if>
	</div>
</div>