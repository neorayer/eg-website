<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<h2>Server Version</h2>
<div>
	<c:if test="${null == curVersion.vid}">
		<div class="standard-warn">
			注意：您现在没有设置当前版本！
		</div>
	</c:if>
	<c:if test="${null != curVersion.vid}">
		当前版本：<c:out value="${curVersion.vid}" />
	</c:if>
	<form class="standard" method="post" action="serverVersion.add.jsplayout.do">
		<ul>
			<li>
				<label>服务端版本号</label>
				<input name="vid" type="text" />
			</li>
			<li>
				<label>说明</label>
				<input name="memotext" type="text" />
			</li>
			<li>
				<label>是否当前版本</label>
				<select name="iscurversion">
					<option value="false">否</option>
					<option value="true">是</option>
				</select>
			</li>
			<li>
				<input class="standard-button" type="submit" value="增加" />
			</li>
		</ul>
	</form>
</div>
<div>
	<c:import url="serverVersions.jsp.vi" />	
</div>
