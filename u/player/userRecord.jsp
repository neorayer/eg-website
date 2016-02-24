<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">



</style>

<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>

<table id="player-egreplay-list-box" width="100%" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<th width="18%" align=center>游戏类型</th>
			<th width="40%" align=center>对局</th>
			<th width="10%" align=center>获胜</th>
			<th width="10%" align="center">得分</th>
			<th width="10%" align=center>录像</th>
			<!-- 
			<th width="10%" align=center>操作</th>
			 -->
		</tr>
	</thead>
	<tbody>
	  <c:forEach var="fight" items="${fightHistorys}">
	  	<tr>
	  		<td><c:out value="${fight.fightLog.gameZone.name}"/></td>
	  		<td align="left">
	  			<div class="plat-fight-name-box" title="<c:forEach var="hostPlayer" items="${fight.fightLog.hostPlayers}" varStatus="status"><c:out value="${hostPlayer.dispName}" /><c:if test="${!status.last}">+</c:if></c:forEach>_vs_<c:forEach var="otherPlayer" items="${fight.fightLog.otherPlayers}" varStatus="status"><c:out value="${otherPlayer.dispName}" /><c:if test="${!status.last}">+</c:if></c:forEach>">
	  				<nobr>
		  			<c:forEach var="hostPlayer" items="${fight.fightLog.hostPlayers}" varStatus="status">
		  				<a href="../player/index.jsplayout.vi?username=<c:out value='${hostPlayer.username}'/>"><c:out value="${hostPlayer.dispName}" /></a>
		  				<c:if test="${!status.last}">+</c:if>
		  			</c:forEach>_vs_
		  			<c:forEach var="otherPlayer" items="${fight.fightLog.otherPlayers}" varStatus="status">
		  				<a href="../player/index.jsplayout.vi?username=<c:out value='${otherPlayer.username}'/>"><c:out value="${otherPlayer.dispName}" /></a>
		  				<c:if test="${!status.last}">+</c:if>
		  			</c:forEach>
		  			</nobr>
	  			</div>
	  		</td>
	  		<td>
	  			<div class="<c:if test='${fight.iswin}'>fighthis-win</c:if><c:if test='${!fight.iswin}'>fighthis-lose</c:if>"></div>
	  		</td>
	  		<td><c:out value='${fight.score}'/></td>
	  		<td>无</td>
	  		<!-- 
	  		<td align="center"><a href="#">详细</a></td>
	  		 -->
	  	</tr>
	  </c:forEach>
	</tbody>
</table>
<c:if test='${fightcount>10}'>
<div align="right" class="allrecordbar"><a href="../player/userRecords.jsplayout.vi?username=<c:out value='${player.username }'/>">所有记录>></a></div>
</c:if>
<div style="clear: both;"></div>


  			
<script type="text/javascript">
;$(document).ready(function() {

});  
</script>
