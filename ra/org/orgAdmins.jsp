<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h3>Org - [OrgAdmins]</h3>
	
	<a href="orgAdmin.add.jsplayout.vi?orgid=<c:out value="${id}" />" >增加管理员</a>
	<table>
		<thead>
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="admin" items="${admins}">
				<tr>
					<td><c:out value="${admin.username}" /></td>
					<td><c:out value="${admin.password}" /></td>
					<td><a href="orgAdmin.del.jsplayout.vi?orgid=<c:out value="${id}" />&username=<c:out value="${admin.username}" />">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>