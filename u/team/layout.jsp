<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<c:import url="../_pub/common_head.jsp"></c:import>

<style type="text/css">
#page-aide-box {
}

#page-body-box {
	width:1000px;
	background-color: #EFEFEF;
}
</style>
</head>

<body>
	<c:import url="../portal/head.jsp.vi"></c:import>
	<div id='page-body-box'>
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td id="page-main_box" valign="top">
					<c:import url="${PL}"></c:import>
				</td>
			</tr>
		</table>
	</div>
	<c:import url="../portal/tail.jsp.vi"></c:import>
</body>

<script type="text/javascript">
$(document).ready(function() {
	$('#egplayer-logo-box').addClass('logo-box-selected');
});
</script>
</html>
