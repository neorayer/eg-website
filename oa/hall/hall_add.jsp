<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h2>新增电竞大厅</h2>
	
	<form class="standard" action="hall.add.jsplayout.do" method="post" >
		<ul>
			<li>
				<label>大厅ID</label>
				<input name="id" type="text" />
			</li>
			<li>
				<label>大厅名称</label>
				<input name="name" type="text" />
			</li>
			<li>
				<input class="standard-button" type="submit" value="提交" />
			</li>
		</ul>
	</form>
</div>