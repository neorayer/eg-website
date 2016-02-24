<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style type="text/css">
#CreatingTeamMessage-box {
	line-height: 162%;
	width: 100%;
}

#CreatingTeamMessage-box td {
	border-bottom: 1px solid gray;
}
</style>

<table id="CreatingTeamMessage-box">
	<tr>
		<th>时间: </th>
		<td><c:out value='${message.createDateTime}'/></td>
	</tr>
	
	
	<tr>
		<th>主题: </th>
		<td><c:out value='${message.subject}' escapeXml="false"/></td>
	</tr>
	
	<tr>
		<th>发信人: </th>
		<td>
			<c:if test="${message.isSys}">
				<span>
					[系统消息]
				</span>	
			</c:if>
			<c:if test="${!message.isSys}">
				<c:set var="senderUser" value="${message.senderUser}" />
				<a href="javascript:TeamMsg.vi_user('<c:out value='${senderUser.username }'/>');">
					<c:out value='${senderUser.nickname}'/>
				</a>
			</c:if>
		</td>
	</tr>
	
	<tr>
		<th>内容: </th>
		<td><c:out value='${message.content}' escapeXml="false"/></td>
	</tr>
</table>

