<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<h2>游戏地图</h2>

<form class="standard" action="gameMap.add.jsplayout.do" enctype="multipart/form-data" method="post">
	<input type="hidden" name="redirectTo" value="gameMaps.jsplayout.vi" />
	<ul>
		<li>
			<label>地图名称</label>
			<input name="name" type="text" />
		</li>
		<li>
			<label>地图校验码</label>
			<input name="checksum" type="text" value="0" />
		</li>
		<li>
			<label>游戏类型</label>
			<select name="zoneid" >
				<c:forEach var="game" items="${games}">
					<c:forEach var="gameZone" items="${game.gameZones}" >
						<option value="<c:out value="${gameZone.zoneId}" />" >
							<c:out value="${game.name}" />--<c:out value="${gameZone.name}" />
						</option>
					</c:forEach>
					<option>---------------------</option>
				</c:forEach>
			</select>
		</li>
		<li>
			<label>地图文件</label>
			<input type="file" name="file" />
		</li>
		<li>
			<label>是否可积分：</label>
			<select name="ispointable">
				<option value="true">是</option>
				<option value="false">否</option>
			</select>
		</li>
		<li>
			<input class="standard-button" type="submit" value="增加" />
		</li>
	</ul>
</form>


<table class="standard" width="100%">
	<thead>
		<tr>
			<td>地图名称</td>
			<td>地图校验码</td>
			<td>游戏</td>
			<td>游戏类型</td>
			<td>是否积分</td>
			<td>下载</td>
			<td>-</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="gameMap" items="${gameMaps}" >
		<tr>
			<td><c:out value="${gameMap.name}" /></td>
			<td><c:out value="${gameMap.checksum}" /></td>
			<td><c:out value="${gameMap.game.name}" /></td>
			<td><c:out value="${gameMap.gameZone.name}"/></td>
			<td>
				<c:if test="${gameMap.isPointable}">
					<label>是</label>
					<a class="standard-button" href="<c:out value='gameMap.mod.jsplayout.do?gameid=${gameMap.gameId}&zoneid=${gameMap.zoneId }&checksum=${gameMap.checksum}&ispointable=false'/>">否</a>
				</c:if>
				<c:if test="${!gameMap.isPointable}">
					<a class="standard-button" href="<c:out value='gameMap.mod.jsplayout.do?gameid=${gameMap.gameId}&zoneid=${gameMap.zoneId }&checksum=${gameMap.checksum}&ispointable=true'/>">是</a>
					<label>否</label>
				</c:if>
			</td>
			<td><a href="<c:out value='gameMap.download.down.do?gameid=${gameMap.gameId}&zoneid=${gameMap.zoneId }&checksum=${gameMap.checksum}'/>">下载</a></td>
			<td><a href="<c:out value='gameMap.del.jsplayout.do?gameid=${gameMap.gameId}&zoneid=${gameMap.zoneId }&checksum=${gameMap.checksum}'/>">删除</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>