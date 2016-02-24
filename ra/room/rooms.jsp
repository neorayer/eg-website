<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<select id="rooms-select" onchange="javascript: RoomX.loadUsers($(this).val())" >
		<option value="">类型</option>
		<c:forEach var="room" items="${rooms}">
		<option value="<c:out value="${room.roomId}" />">
			<c:out value="${room.name}" />
		</option>
		</c:forEach>
	</select>	
