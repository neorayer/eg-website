<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h1>Org - Org_mod</h1>
	<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
	<form action="org.mod.jsplayout.do" method="post">
		<ul>
			<li>
				<label>组织ID:</label>
				<span><c:out value="${org.id}" /></span>
				<input type="hidden" name="id" value="<c:out value="${org.id}" />" />
			</li>
			<li>
				<label>组织名称</label>
				<input name="name" type="text" value="<c:out value="${org.name}" />" />
			</li>
			<li>
				<label>是否拥有大厅</label>
				<input type="radio" name="hasprivhall" value="true" /><span>是</span>
				<input type="radio" name="hasprivhall" value="false" /><span>否</span>
			</li>
			<li>
				<input value="Mod" type="submit" />
			</li>
		</ul>
	</form>
</div>