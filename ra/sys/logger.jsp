<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<h2>当前日志级别：<c:out value="${loggerLevel}" /></h2>
<form class="standard" action="logger.mod.jsplayout.do" method="post">
	<ul>
		<li>
			<label>日志级别</label>
			<select name="level" >
				<option value="DETAIL">细节</option>
				<option value="DEBUG">调试</option>
				<option value="INFO" >信息</option>
				<option value="WARN">警告</option>
				<option value="ERROR">错误</option>
				<option value="FATALERROR">严重错误</option>
			</select>
		</li>
		<li>
			<input class="standard-button" type="submit" value="修改" />
		</li>
	</ul>
</form>
