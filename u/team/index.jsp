<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style type="text/css">
#page-index-creating-button {
	border:1px solid gray;
	margin: 10px 0;
}

#page-index-creating-box {
	display: none;
}

</style> 
 

<div id="page-index-box">
	<c:if test="${not empty team}">
		<c:choose>
			<c:when test="${empty team.areaId && team.creator eq ACTOR.username }">
				<c:import url="../team/teamInfor_add.jsp.vi"></c:import>
			</c:when>
			<c:otherwise>
				<c:import url="../team/team_certificate.jsp.vi?teamid=${team.id}"></c:import>
				<div id="team-page-player-cglevel">
					<div class="team-page-title-temp">
						<div class="index-page-user-titlefont floatLeft">
							<b>电竞水平</b>
						</div>
						<div class="floatRight"><a href="../player/ladderRule.jsplayout.vi">积分规则</a></div>
					</div>
					
					<div id="user-page-cglevel-body">
						<c:import url="../team/eglevel.jsp.vi?teamid=${team.id}" />
					</div>
				</div>
				<c:import url="../team/team_space.jsp.vi?teamid=${team.id}"></c:import>
			</c:otherwise>
		</c:choose>
		
	</c:if>
	
	<c:if test="${empty team}">
		<c:if test="${not empty ACTOR}">
			<button id="page-index-creating-button" class="button">创建战队</button>
			<div id="page-index-creating-box">
				<c:import url="../team/creating.jsp.vi"></c:import>
			</div>
		</c:if>
		<div id="PL-teams-box">
			<c:import url="../team/teams.jsp.vi"></c:import>
		</div>
	</c:if>


</div>

<div style="clear: both;"></div>


  
 
 
 
 
<script type="text/javascript">
$(document).ready(function() {
 	$('#page-index-creating-button').click(function() {
 		$('#page-index-creating-box').slideToggle('slow');
 	});
});
 
</script>



