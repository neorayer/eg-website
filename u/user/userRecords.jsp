<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
<!--
#allrecords-page{
	border-bottom:1px solid #BBBBBB;
	border-left:1px solid #BBBBBB;
	height:auto !important;
	min-height:560px;
	padding:15px;
}

#allrecords-page-title {
	border-bottom:1px solid #D8DFEA;
	color:black;
	font-size:14px;
	height:45px;
	line-height:45px;
	padding:0 15px;
}

#allrecords-body-box {
}

#player-egreplay-alllist-box {
	clear: both;
	background:#EEEEEE;
	text-align: center;
	border:1px solid #FFFFFF;
}
#player-egreplay-alllist-box thead {
	padding:5px;
	background:#ECEFF5;
	color:#333333;
	cursor:pointer;
	font-weight:bold;
	line-height: 25px;
	height: 25px;
	border-bottom:1px solid #D8DFEA;
}

#player-egreplay-alllist-box tbody td{
	height:25px;
	line-height:25px;
	border-bottom:1px dashed #D8DFEA;
}
-->
</style>

<div id="allrecords-page">
	<div id="index-page-player-life">
		<div id="allrecords-page-title">
			<div class="floatLeft">
					<b>电竞水平</b>
			</div>
				<div class="floatRight"><a href="../player/ladderRule.jsplayout.vi">积分规则</a></div>
			</div>
			<div id="user-page-cglevel-body">
			<c:import url="../player/eglevel.jsp.vi?username=${player.username}" />
		</div>
	</div>
		
	<div id="allrecords-page-title">
		<div class="floatLeft">
			<strong>平台记录--列表</strong>
		</div>
		<div class="floatRight">
			
		</div>
	</div>
	
	<div id="allrecords-body-box">
		<table id="player-egreplay-alllist-box" width="100%" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th width="10%" align=center>时间</th>
					<th width="10%" align=center>游戏类型</th>
					<th width="50%" align=center>对局</th>
					<th width="5%" align=center>获胜</th>
					<th width="10%" align="center">得分</th>
					<th width="10%" align=center>录像</th>
					<th width="5%" align=center>操作</th>
				</tr>
			</thead>
			<tbody>
			  <c:forEach var="fight" items="${fightHistorys}">
			  	<tr>
			  		<td><c:out value='${fight.cerateDateTime}'/></td>
			  		<td><c:out value="${fight.fightLog.gameZone.name}"/></td>
			  		<td>
			  		<div>
			  			<c:forEach var="hostPlayer" items="${fight.fightLog.hostPlayers}" varStatus="status">
			  				<a href="../player/index.jsplayout.vi?username=<c:out value='${hostPlayer.username }'/>"><c:out value="${hostPlayer.dispName }" /></a>
			  				<c:if test="${!status.last}">+</c:if>
			  			</c:forEach>_vs_<c:forEach var="otherPlayer" items="${fight.fightLog.otherPlayers}" varStatus="status">
			  				<a href="../player/index.jsplayout.vi?username=<c:out value='${otherPlayer.username }'/>"><c:out value="${otherPlayer.dispName }" /></a>
			  				<c:if test="${!status.last}">+</c:if>
			  			</c:forEach>
			  		
			  		</td>
			  		
			  		<td><div class="<c:if test='${fight.iswin}'>fighthis-win</c:if><c:if test='${!fight.iswin}'>fighthis-lose</c:if>"></div></td>
			  		<td><c:out value='${fight.score}'/></td>
			  		<td>无</td>
			  		<td align="center"><a href="#">详细</a></td>
			  	</tr>
			  </c:forEach>
			  <tr>
			  	<td colspan="6" align="center">
			  		<div id="allrecords-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
			  	</td>
			  </tr>
			</tbody>
		</table>
		
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	// 分页
	$('#allrecords-pagebar-box a')
		.click(function() {
			var url = $(this).attr('href');
			window.location = url;
			return false;
		});
	
});
</script>
