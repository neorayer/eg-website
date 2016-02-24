<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h1>Hall - <c:out value="${org.name}"/></h1>
	
	<c:if test="${not org.hasPrivHall}">
		<div>您的组织还不具备私有大厅的权限</div>
	</c:if>
</div>