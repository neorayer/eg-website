<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">

#teams-top-box {
	border:1px solid gray;
	background-color: #ffffff;
	margin-bottom: 10px;
	line-height: 25px;
	height: 25px;
	padding: 5px;
	padding-left:5px;
	style="text-align: left;"
}

#portal-index-teams-list {
	text-align: center;
}

#portal-index-teams-list thead th {
	border:1px solid gray;
}
#portal-index-teams-list td{
	border: 1px solid gray;
}

</style>

<div>
	<div id="teams-top-box">
		<form id="teams-team-search-form" action="../portal/searchTeams.jsplayout.vi" method="post">
			<span class="label">战队名称：</span> 
			<input type="text" name="teamname" value=""/>
			<input type="submit" value="搜索" onclick="Portal_Teams_Page.search();return false;"/>
		</form>	
	</div>

	<table id="portal-index-teams-list" width="100%" cellspacing="0" cellpadding="5">
		<thead>
			<tr>
				<th width="20%">战队</th>
				<th width="15%">人数</th>
				<th width="15%">队长</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="team" items="${teams}">
			<tr>
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
				 	<a href="<c:out value='../player/index.jsplayout.vi?username=${user.username}'/>">
				 		<c:out value='${user.nickname}'/>
					</a>
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
Portal_Teams_Page = {
	search: function(){
		var $form = $('#teams-team-search-form');
		var query = $form.formSerialize();
		var url ='../portal/searchTeams.jsplayout.vi?'+query;
		window.location= url;
	}
}

$(document).ready(function() {
	
});

</script>