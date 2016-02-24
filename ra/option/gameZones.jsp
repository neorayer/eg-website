<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<h2>游戏类型</h2>

<form name="gameZone_search_form" method="post" action="gameZones.jsplayout.vi">
	<ul class="standard-buttons-bar">
		<li>
			所属游戏:
			<select name="gameid" onchange="document.gameZone_search_form.submit();">
				<option value="">-- 所有 --</option>
				<c:forEach var="game" items="${games}">
					<option value="<c:out value="${game.gameId}" />" 
						<c:if test="${game.gameId == gameid}">
							selected="true"
						</c:if>
					>
						<c:out value="${game.name}" />
					</option>
				</c:forEach>
			</select>
		</li>
	</ul>
</form>

<table class="standard" width="100%">
	<thead>
		<tr>
			<td>ID</td>
			<td>类型名称</td>
			<td>GameId</td>
			<td>游戏名</td>
			<td>-</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="gameZone" items="${gameZones}">
		<tr>
			<td><c:out value="${gameZone.zoneId}" /></td>
			<td><c:out value="${gameZone.name}" /></td>
			<td><c:out value="${gameZone.gameId}" /></td>
			<td><c:out value="${gameZone.game.name}" /></td>
			<td><a href="gameZone.del.jsplayout.do?zoneid=<c:out value="${gameZone.zoneId}" />" >删除</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<h3>增加</h3>
<form name="gameZone_add_form" class="standard" method="post" action="gameZone.add.jsplayout.do">
	<input type="hidden" name="redirectTo" value="gameZones.jsplayout.vi" />
	<ul>
		<li>
			<label>ID</label>
			<input name="zoneid" type="text" value=""/>
		</li>
		<li>
			<label>类型名称</label>
			<input name="name" type="text" />
		</li>
		<li>
			<label>所属游戏</label>
			<select name="gameid" onchange="document.gameZone_add_form.zoneid.value = this.value + '-';">
				<option value="">--请选择--</option>
				<c:forEach var="game" items="${games}">
					<option value="<c:out value="${game.gameId}" />" >
						<c:out value="${game.name}" />
					</option>
				</c:forEach>
			</select>
		</li>
		<li>
			<input class="standard-button" type="submit" value="增加" />
		</li>
	</ul>
</form>