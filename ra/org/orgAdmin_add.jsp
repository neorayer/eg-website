<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h3>Org - [<c:out value="${org.name}" />] 添加管理员</h3>
	<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
	<form action="orgAdmin.add.jsplayout.do" method="post" >
		<input name="orgid" value="<c:out value="${org.id}" />"  type="hidden" />
		<ul>
			<li>
				<label>用户名</label>
				<input name="username" type="text" />
			</li>
			<li>
				<label>密码</label>
				<input name="password" type="password" />
			</li>
			<li>
				<input value="Add" type="submit" />
			</li>
		</ul>
	</form>
	
</div>