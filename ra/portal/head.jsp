<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="page-head-box" >
	<div id="page-head-top-box">
		<a href="#">综合管理</a>
		<a href="#">大厅管理</a>
		<a href="#">组织内部管理</a>
	</div>
	<div id="page-head-middle-box">
		<h1 id="logo-label">
			EGHall Admin Console
		</h1>
		<div id="page-head-middle-left-box">
		</div>
	</div>
	<c:out value="${module}" />
	<ul id="page-head-menu-list" class="standard-nav">
		<li>
			<a href="../portal/home.jsplayout.vi">首页</a>
		</li>
		<li>
			<a href="../org/home.jsplayout.vi">组织</a>
		</li>
		<li>
			<a href="#">管理员</a>
		</li>
		<li>
			<a href="../room/home.jsplayout.vi">房间</a>
		</li>
		<li>
			<a href="../option/home.jsplayout.vi">设置</a>
		</li>
		<li>
			<a href="../sys/home.jsplayout.vi">系统</a>
		</li>
		<li>
			<a href="../portal/logout.jsplayout.do">退出</a>
		</li>
	</ul>
</div>
