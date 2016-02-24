<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<c:import url="../_pub/common_head.jsp"></c:import>

<style type="text/css">
#page-teams-list {
	text-align: center;
}

#page-teams-list thead th {
	border:1px solid gray;
}

#teams-top-box {
	border:1px solid gray;
	background-color: #ffffff;
	width: 998px;
	margin-bottom: 10px;
	line-height: 25px;
	height: 25px;
	padding: 5px;
}

#teams-team-search-form {
	padding-left:5px;
	display: inline;
	float: left;
}
#page-teams-numbers-box {
	float: right;
}
.hover{
	background-color: #dedede;
}


</style>
</head>

<body>

<c:import url="../portal/head.jsp.vi"></c:import>

<div id='page-body-box'>
	<div style="text-align: left;">
		<div id="teams-top-box">
			<form id="teams-team-search-form" action="../team/team.checkexists.json.do" method="post">
				<span class="label">战队名称：</span> 
				<input type="text" name="teamname" value="<c:out value='${teamname }'/>"/>
				<input type="submit" value="搜索" onclick="Team_Teams_Page.search();return false;"/>
				<span id="teams-team-search-errors-box" style="color: red;"></span>
			</form>	
			
			<span id="page-teams-numbers-box">
				当前共有
				<span style="color: red;"><c:out value="${teamNum}" /></span>
				支队伍注册！	
			</span>
			<div style="clear: both;"></div>
		</div>
		
	</div>
	<table id="page-teams-list" width="100%" cellspacing="0" cellpadding="5">
		<thead>
			<tr>
				<th width="20%">徽标</th>
				<th width="20%">战队</th>
				<th width="15%">人数</th>
				<th width="15%">队长</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="hasTeam" value="${not empty ACTOR && ACTOR.hasTeam}" />
			<c:forEach var="team" items="${teams}">
			<tr>
				<td align=center>
					<div class="all-team-logo-box">
						<a href="<c:out value='../team/index.jsplayout.vi?teamid=${team.id }'/>">
							<img src="<c:out value='${team.logoPath }'/>" width="46px" height="62px"/>
						</a>
					</div>	
				</td>
				<td>
					<a href="<c:out value='../team/index.jsplayout.vi?teamid=${team.id }'/>">
						<c:out value='${team.teamName}'/>
					</a>
				</td>
				<td>
					<a href="<c:out value='../team/index.jsplayout.vi?teamid=${team.id }'/>">
						<c:out value='${team.memberCount}'/>
					</a>
				</td>
				<td>
					<c:set var="user" value="${team.teamLeader }"/>
				 	<a href="<c:out value='../player/index.jsplayout.vi?username=${user.username }'/>">
				 		<c:out value='${user.nickname}'/>
					</a>
				</td>
				<td>
					<c:if test="${not empty ACTOR && !hasTeam}">
						<a class="button" href="javascript:" onclick="<c:out value='Team_Teams_Page.apply_to_team("${team.id }")'/>">申请加入</a>	
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
</div>

<c:import url="../portal/tail.jsp.vi"></c:import>

</body>

<script type="text/javascript">
Team_Teams_Page = {
	teamname: '<c:out value='${teamname }'/>',
	apply_to_team: function(teamid) {
		TeamMember.apply(teamid, function() {
			window.location.reload();
		});
	},
	search: function(){
		var $form = $('#teams-team-search-form');
		var query = $form.formSerialize();
		var url ='../team/all.jsp.vi?'+query;
		window.location= url;
	}
}

$(document).ready(function() {
	$('#page-teams-list tr')
		.mouseover(function() {
			$(this).addClass("hover");
		})
		.mouseout(function() {
			$(this).removeClass("hover");
		});
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href').replace('jsplayout', 'jsp')+'&teamname=' + Team_Teams_Page.teamname;
		window.location=url;
		return false;
		
	});
});

</script>

</html>
