<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h2>Org - [<c:out value="${org.name}" />]</h2>
	
	<c:import url="orgAdmins.jsp.vi?orgid=${org.id}" >
	</c:import>
</div>