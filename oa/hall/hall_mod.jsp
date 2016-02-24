<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h2><c:out value="${hall.name}" />:修改属性</h2>
	
	<form class="standard" action="hall.mod.jsplayout.do" method="post" >
		<ul>
			<li>
				<label>大厅ID</label>
				<span><c:out value="${hall.id}" /></span>
				<input name="id" type="hidden" value="<c:out value="${hall.id}" />" />
			</li>
			<li>
				<label>大厅名称</label>
				<input name="name" type="text" value="<c:out value="${hall.name}" />" />
			</li>
			<li>
				<label>皮肤</label>
				<select name="skinid" >
					<option value="wow">魔兽风格</option>
					<option value="by1">百有电竞专用</option>
				</select>
			</li>
			<li>
				<input class="standard-button" type="submit" value="mod" />
			</li>
		</ul>
	</form>

	

</div>