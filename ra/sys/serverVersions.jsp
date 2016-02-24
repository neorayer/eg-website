<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>

	<table class="standard">
		<thead>
			<tr>
				<td>版本号</td>
				<td>是否当前版本</td>
				<td>设置</td>
				<td>说明</td>
				<td>删除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="version" items="${versions}" >
			<tr>
				<td><c:out value="${version.vid}" /></td>
				<td><c:out value="${version.isCurVersion}" /></td>
				<td><a href="serverVersion.setCur.jsplayout.do?iscurversion=true&vid=<c:out value="${version.vid}" />" >设为当前版本</a></td>
				<td><c:out value="${version.memotext}" /></td>
				<td><a href="serverVersion.del.jsplayout.do?vid=<c:out value="${version.vid}" />" >删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>
