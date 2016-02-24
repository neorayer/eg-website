<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<div id="team-certificate-box">
	<div class="team-certi-title">
		<div class="team-certificate-title"><c:out value='${team.teamName}'/>的战队空间</div>
		
		<div class="team-certificate-sign">
			<!-- 战队签名: 
			<c:if test="${not empty team.memo}">
				<c:out value="${team.memo}" escapeXml="false"/>
			</c:if>
			<c:if test="${empty team.memo}">
				
			</c:if>
			-->
		</div>
		 
		<div id="team-certificate-operator-box">
			
			<!-- 已经登录并且别人的战队 -->
			<c:if test="${not empty ACTOR && empty me}">
				<c:set var="hasTeam" value="${ACTOR.hasTeam}" />
				<c:if test="${hasTeam}">
					<a class="button" href="../team/index.jsplayout.vi">我的战队</a>
				</c:if>
				<c:if test="${!hasTeam}">
					<a class="button" href="javascript:" onclick="<c:out value='Team_TeamCerti_Page.apply_to_team("${team.id }");'/>">申请加入</a>
				</c:if>
			</c:if>
			
			<!-- 自己的战队 -->
			<c:if test="${not empty me}">
				<c:if test="${!me.passed}">
					你正在申请加入 <a href="javascript:Team_TeamCerti_Page.vi_team('<c:out value='${team.id}'/>');">
					<c:out value="${team.teamName}" /></a> 战队，
					状态：<span style="color:red">等待队长审批。</span>
					<a class="button" href="javascript:" onclick="Team_TeamCerti_Page.cancel_applyTo_team('<c:out value='${team.id }'/>')">取消申请</a>
				</c:if>
				
				<c:if test="${me.passed}">
					<!-- 队长 -->
					<c:if test="${me.isLeader}">
						<a class="button" href="javascript:" onclick="Team_TeamCerti_Page.disband_team('<c:out value='${team.id}'/>');">解散战队</a>
					</c:if>
					
					<!-- 成员 -->
					<c:if test="${!me.isLeader}">
						<a class="button" href="javascript:" onclick="Team_TeamCerti_Page.leave_team('<c:out value='${team.id}'/>')">退出战队</a>
					</c:if>
				</c:if>
			</c:if>
			<!-- 
			<a class="button" href="../team/teams.jsplayout.vi">所有战队</a>
			 -->
		</div>
	</div>
	
	<ul class="team-certificate-items-box">
		<c:forEach var="type" items="${matchtypes}">
			<li id="type_<c:out value='${type.id}' />" >
				<a href="javascript:" onclick="<c:out value='TeamMatchType.open("${type.id }")'/>">
					<c:out value="${type.name}" />
				</a>
			</li>
		</c:forEach>
	</ul>
	<div id="team-certificate-main-box">
	</div>
</div>

<script type="text/javascript">
TeamMatchType = {
	open: function(matchtypeId) {
		$('#type_' + matchtypeId)
			.parent().children().removeClass('selected').end().end()
			.addClass('selected');
		$('#team-certificate-main-box').load('../team/team_project_certificate.jsp.vi?matchtypeid=' + matchtypeId + '&teamid=' + Team.id);
	},
	
	selectFirst: function() {
		var proId = $('.team-certificate-items-box :first-child').attr('id').replace('type_', '');
		TeamMatchType.open(proId);
	}
}

Team_TeamCerti_Page = {
	disband_team: function(teamId) {
		var $page = $('<div  class="message-win"/>').load('../team/team.disband.jsp.vi?id=' + teamId);
		PopupWinX.create($page).show();	
	},
	
	leave_team: function(teamId) {
		TeamMember.leave_team(teamId, function() {
			window.location.reload();
		});
	},
	
	vi_team: function(teamId) {
		Message.vi_team(teamId);
	},
	
	cancel_applyTo_team: function() {
		TeamMember.cancel_apply(function() {
			window.location.reload();
		});
	},
	
	apply_to_team: function(teamid) {
		TeamMember.apply(teamid, function() {
			window.location.reload();
		});
	}
}


$(document).ready(function() {
	Team.id = "<c:out value='${team.id}'/>";
	TeamMatchType.selectFirst();
});
</script>