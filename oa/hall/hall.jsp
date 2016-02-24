<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h2>大厅：<c:out value="${hall.name}" /></h2>
	
	<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
	<ul class="standard-buttons-bar">
		<li>
			<a href="hall.del.jsplayout.do?id=<c:out value="${hall.id}" />" >删除</a>	
		</li>
		<li>
			<a href="hall.mod.jsplayout.vi?id=<c:out value="${hall.id}" />" >修改</a>	
		</li>
	</ul>
	
	<ul class="standard-props">
		<li>
			<label>大厅ID</label>
			<span>
				<c:out value="${hall.id}" />
			</span>
		</li>
		<li>
			<label>大厅名称</label>
			<span>
				<c:out value="${hall.name}" />
			</span>
		</li>
		<li>
			<label>皮肤</label>
			<span>
				<c:out value="${hall.skinId}" />
			</span>
		</li>
	</ul>
	
	<c:import url="menuItems.jsp.vi" />
</div>