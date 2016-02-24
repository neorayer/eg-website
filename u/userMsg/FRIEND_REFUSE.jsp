<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style type="text/css">
 
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
		<td>[系统消息]</td>
	</tr>
	
	<tr>
		<th>内容: </th>
		<td><c:out value='${message.content}' escapeXml="false"/></td>
	</tr>
</table>
