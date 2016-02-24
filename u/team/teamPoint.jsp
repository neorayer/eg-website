<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">

#player-eglevel-list-box {
	clear: both;
	background:#EEEEEE;
	text-align: center;
	border:1px solid #FFFFFF;
}
#player-eglevel-list-box thead {
	padding:5px;
	background:#ECEFF5;
	color:#333333;
	cursor:pointer;
	font-weight:bold;
	line-height: 25px;
	height: 25px;
	border-bottom:1px solid #D8DFEA;
}

#player-eglevel-list-box tbody td{
	height:25px;
	line-height:25px;
	border-bottom:1px dashed #D8DFEA;
}

</style>

<table id="player-eglevel-list-box" width="100%" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<th width="25%" align=center>游戏类型</th>
			<th width="10%" align=center>胜</th>
			<th width="10%" align=center>负</th>
			<th width="15%" align="center">胜率</th>
			<th width="20%" align=center>积分</th>
			<th width="20%" align=center>等级</th>
		</tr>
	</thead>
	<tbody>
	  <c:forEach var="teampoint" items="${teampoints}">
	  	<tr>
	  		<td><c:out value="${teampoint.gameZone.name}"/></td>
	  		<td><c:out value="${teampoint.win}"/></td>
	  		<td><c:out value="${teampoint.lose}"/></td>
	  		<td><c:out value='${teampoint.rate}'/></td>
	  		<td><c:out value="${teampoint.point}"/></td>
	  		<td><c:out value="${teampoint.rank}"/></td>
	  	</tr>
	  </c:forEach>
	</tbody>
</table>

<script type="text/javascript">

;$(document).ready(function() {
	$('#player-eglevel-list-box tr')
		.mouseover(function() {
			$(this).addClass("hover");
		})
		.mouseout(function() {
			$(this).removeClass("hover");
		});
});  
</script>
