<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">



</style>

<div id="halls-box">
	<h2>俱乐部电竞大厅</h2>

	
	<ul class="standard-vertical">
		<c:forEach var="hall" items="${org.halls}">
			<li>
				<a href="hall.jsplayout.vi?id=<c:out value="${hall.id}" />" >
					<c:out value="${hall.name}" />
				</a>
			</li>
		</c:forEach>
	</ul>
	
	<a class="standard-button" href="hall.add.jsplayout.vi" >增加大厅</a>	
</div>