<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h2>Org - Orgs</h2>
	
	<table>
		<thead>
			<tr>
				<td>编号</td>
				<td>名称</td>
				<td>是否拥有独立大厅</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="org" items="${orgs}">
				<tr>
					<td>
						<c:out value="${org.id}" />
					</td>
					<td>
						<a href="org.jsplayout.vi?id=<c:out value='${org.id}' />" >
						<c:out value="${org.name}" />
						</a>
					</td>
					<td>
						<c:out value="${org.hasPrivHall}" />
					</td>
					<td>
						<a href="org.del.jsplayout.do?id=<c:out value='${org.id}' />">删除</a>
						<a href="org.mod.jsplayout.vi?id=<c:out value='${org.id}' />">修改</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<ttail>
		
		</ttail>
	</table>
</div>