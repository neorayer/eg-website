<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<ul class="standard-buttons-bar">
	<li>
		<a href="javascript:RoomX.timeoutCheck('<c:out value="${roomid}" />')" >超时检查</a>
	</li>
</ul>
<div style="clear:both"></div>
<table id="users-table" class="standard" width="100%" >
	<thead>
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>在线</td>
			<td>虚拟IP</td>
			<td>真实IP</td>
			<td>地点</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${users}" varStatus="status" >
			<tr>
				<td>
					<c:out value="${status.count}" />.
				</td>
				<td>
					<a href="#">
						<c:out value="${user.username}" />
					</a>
				</td>
				<td>
					<c:out value="${user.nickname}" />
				</td>
				<td>
					<c:out value="${user.isOnline}" />
				</td>
				<td>
					<c:out value="${user.VIpAddrStr}" />
				</td>
				<td>
					<c:out value="${user.realIp}" />
				</td>
				<td>
					<c:out value="${user.ipLocation}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>
