<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}"/></div>

<div id="team-image-box" class="editableFile">
	<img id="team-logo-image" class="team-logo-small-image" src="<c:out value='${team.logoPath}'/>">
	<c:if test="${ACTOR.username == team.creator}">
		<div class="team-logo-mod-button-box">
			<a href="javascript:TeamIntro.team_uploadImg('<c:out value='${team.id}'/>');">[修改徽标]</a>
		</div>
	</c:if>
</div>

<ul id="team-intro-box">
	<li>
		<span class="label">战队名称：</span>
		<span>
			<c:out value='${team.teamName}'/>
		</span>
	</li>
	
	<li>
		<span class="label">队长：</span>
		<span>
			<c:out value='${team.creator }'/>
		</span>
	</li>
	
	<li>
		<span class="label">队员数：</span>
		<span>
			<c:out value='${team.memberCount }'/>
		</span>
	</li>
	
	<li>
		<span class="label">QQ群号：</span>
		<span>
			<c:out value='${team.qq}'/>
		</span>
	</li>
</ul>
<table id="team-memo-box">
	<tr>
		<td class="label" width="145" valign="top" align="center">战队简介：</td>
		<td >
			<c:out value='${team.memo}' escapeXml="false"/>
		</td>
	</tr>
</table>

<script type="text/javascript">
<!--
TeamIntro = {
	canel: function() {
		$("#team-logo-image-form").slideUp();
	},
	
	reload: function(teamId) {
		$("#tabs-team-box").load("../team/team.jsp.vi?id=" + teamId);
	},
	
	team_uploadImg: function(teamId) {
		$("#tabs-team-box").load("../team/team.upload.jsp.vi?id=" + teamId);
	}
};

$(document).ready(function() {
	GamesHelper.parse('#gameId', '#hostMapType');
});
//-->
</script>	
