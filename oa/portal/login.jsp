<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	</head>

	<body>
		<h1><c:out value="${org.name}" />管理员登陆</h1>
		<DIV id="SOR_EXCEPTION" style="color:red">
			<c:out value="${REASON}" />
		</DIV>		
		<form action="login.jsp.do" method="post" >
			<ul>
				<li>
					<label>username</label>
					<input type="text" name="username" />
				</li>
				<li>
					<label>password</label>
					<input type="password" name="password" />
				</li>
				<li>
					<input type="submit" value="login" />
				</li>
			
			</ul>
		</form>
	</body>
</html>

