<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<c:import url="../_pub/common_head.jsp"></c:import>
</head>

<body>
	<c:import url="../portal/head.jsp.vi"></c:import>
	<div id='page-body-box'>
		<c:import url="${PL}"></c:import>
	</div>
	<c:import url="../portal/tail.jsp.vi"></c:import>
</body>

<script type="text/javascript">
$('#egmatch-logo-box').addClass('logo-box-selected');
var Bisai = {
	del: function(id) {
		if(!confirm("您确实要删除该比赛吗?"))
			return false;
		var url ='../match/bisai.del.json.do?id='+id;
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
			}
		});
		
		return false;
	},
	setFirst: function(id,view) {
		var url ='../match/bisai.mod.json.do?id='+id +'&viewed='+view;
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
			}
		});
		
		return false;
	}
};
</script>
</html>
