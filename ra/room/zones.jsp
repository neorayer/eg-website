<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
	类型:
	<select id="zones-select" onchange="javascript: RoomX.loadRooms('<c:out value="${gameid}" />', $(this).val())" >
		<option value="">类型</option>
		<c:forEach var="zone" items="${zones}">
		<option value="<c:out value="${zone.zoneId}" />">
			<c:out value="${zone.name}" />
		</option>
		</c:forEach>
	</select>
