<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h1>Org - Org_add</h1>
	<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
	<form action="org.add.jsplayout.do" method="post">
		<ul>
			<li>
				<label>组织ID</label>
				<input name="id" type="text" />
			</li>
			<li>
				<label>组织名称</label>
				<input name="name" type="text" />
			</li>
			<li>
				<input value="Add" type="submit" />
			</li>
		</ul>
	</form>
</div>