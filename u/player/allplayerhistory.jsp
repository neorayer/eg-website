<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft"><strong>职业生涯描述</strong></div>
		<div class="floatRight"><a href="../player/index.jsplayout.vi?username=<c:out value='${username}'/>">返回>></a></div>
	</div>
	<div id="allvisitor-page-body">
		<div style="height:20px;"></div>
		<table width="80%" align="center" cellspacing="0" cellpadding="3" class="player-life-table">
			<c:forEach var='his' items='${history}'>
			<tr>
				<td width="30%" align="center"><c:out value='${his.createDate}'/></td>
				<td><c:out value='${his.event}'/></td>
			</tr>
			</c:forEach>
		</table>
		
		<div style="clear:both"></div>
		<c:if test='${historycount>12}'>
			<div id="allvisitors-pagebar">
				<c:out value='${pagebar}' escapeXml="false"/>		
			</div>
		</c:if>
	</div>
</div>