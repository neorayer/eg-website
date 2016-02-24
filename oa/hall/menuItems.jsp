<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="clear:both">
	<h2>新增导航菜单管理</h2>
	<form class="standard" action="menuItem.add.jsp.do" method="post">
		<input type="hidden" name="hallid" value="<c:out value="${hall.id}" />" />
		<ul>
			<li>
				<label>顺序编号</label>
				<input name="sortid" type="text" value="" />
			</li>
			<li>
				<label>ID</label>
				<input name="itemid" type="text" value="" />
			</li>
			<li>
				<label>名称</label>
				<input name="name" type="text" value="" />
			</li>
			<li>
				<label>URL</label>
				<input name="url" type="text" value="http://" />
			</li>
			<li>
				<input class="standard-button" type="submit" value="增加" />
			</li>
		</ul>
	</form>
	<h2>大厅导航航菜单项</h2>
	<table class="standard">
		<thead>
			<tr>
				<td>顺序编号</td>
				<td>ID号</td>
				<td>标题名</td>
				<td>链接</td>
				<td>操作</td>
			</tr>
		</thead>
		<c:forEach var="item" items="${hall.menuItems}" >
			<tr>
				<td><c:out value="${item.sortId}" /></td>
				<td><c:out value="${item.itemId}" /></td>
				<td><c:out value="${item.name}" /></td>
				<td><c:out value="${item.url}" /></td>
				<td>
				<a href="menuItem.del.jsplayout.do?hallid=<c:out value="${hall.id}" />&itemid=<c:out value="${item.itemId}" />" >删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>