<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h1>Org - [添加成功]</h1>
	<div>
		<c:out value="${org.name}" />添加成功
	</div>
	
	<a href="org.add.jsplayout.vi" >继续添加</a>
</div>