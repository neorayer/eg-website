<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<c:import url="../_pub/common_head.jsp"></c:import>
	</head>
	<body >
		<div id="page-wrap-box">
			<c:import url="../portal/head.jsp.vi"></c:import>
			<table class="standard-body-box" width="100%">
				<tr>
					<td class="standard-body-side-box" >
						<c:import url="sidebar.jsp.vi"></c:import>
					</td>
					<td  class="standard-body-main-box" >
						<c:import url="${PL}"></c:import>
					</td>
				</tr>
			</table>
			<c:import url="../portal/foot.jsp.vi"></c:import>
		</div>
	</body>
	<script type="text/javascript">
	</script>
</html>
