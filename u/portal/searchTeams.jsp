<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#page-teams-list {
	text-align: center;
}

#page-teams-list thead th {
	border:1px solid gray;
}
#page-teams-list tbody tr td{
	border-bottom:1px solid #d8dfea;
}
#teams-top-box {
	border:1px solid gray;
	background-color: #ffffff;
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

<div id='page-body-box'>
	<div class="hd-box">
		<h3 class="green">
			战队搜索
			<a href="../portal/index.jsplayout.vi" class="button"  style="margin-left: 800px;">返回</a>
		</h3>
	</div>
	<div class="bd-box">

	<div style="text-align: left;">
		<div id="teams-top-box">
			<form id="teams-team-search-form" action="../portal/searchTeams.jsp.vi" method="post">
				<span class="label">战队名称：</span> 
				<input type="text" name="teamname" value="<c:out value="${teamname }"/>"/>
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
			</tr>
		</thead>
		<tbody>
			<c:forEach var="team" items="${teams}">
			<tr>
				<td align=center>
					<div class="all-team-logo-box">
						
						<a href="../team/index.jsplayout.vi?teamid=<c:out value='${team.id}'/>">
						<img src="<c:out value='${team.logoPath }'/>" width="46px" height="62px"/>
						</a>
					</div>	
				</td>
				<td>
					
						<a href="../team/index.jsplayout.vi?teamid=<c:out value='${team.id}'/>">
						<c:out value='${team.teamName}'/>
						</a>
				</td>
				<td>
						<c:out value='${team.memberCount}'/>
				</td>
				<td>
				 		<c:out value='${user.nickname}'/>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="page-searchusers-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
	
	</div>
</div>


<script type="text/javascript">
Team_Teams_Page = {
	teamName: '<c:out value="${teamname }"/>',
	search: function(){
		var $form = $('#teams-team-search-form');
		var query = $form.formSerialize();
		var url ='../portal/searchTeams.jsplayout.vi?'+query;
		window.location= url;
	}
}

;$(document).ready(function() {

	$('#page-teams-list tr')
		.mouseover(function() {
			$(this).addClass("hover");
		})
		.mouseout(function() {
			$(this).removeClass("hover");
		});
	
	
	// 分页
	$('#page-searchusers-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href')+'&teamname=' +Team_Teams_Page.teamName;
		window.location=url;
		return false;
		
	});
});

</script>
