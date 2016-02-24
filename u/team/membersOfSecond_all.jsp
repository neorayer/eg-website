<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#team-mos-all-box {
	padding: 10px;
	background-color: #ffffff;
	border:1px solid gray;
}

#team-mos-all-top-box {
	margin-bottom: 10px;
}

#team-mos-all-top-box h3 {
	float: left;
	border-bottom: 1px solid gray; 
	width: 70%;
	margin: 10px 10px 0px 10px;
}

#team-mos-all-middle-box {
	clear:both;
	height: 600px;
}

#team-mos-all-middle-box li {
	float: left;
	padding: 15px 0px 0px 15px;
}

#team-mos-all-top-back-link {
	display: block;
	float: right;
	padding-right: 10px;
}

</style>

<div id="team-mos-all-box">
	<div id="team-mos-all-top-box">
		<h3>
			所有二线队员
		</h3>	
		<a id="team-mos-all-top-back-link" href="javascript:" onclick="Team_MOS_All_Page.back();">返回</a>
	</div>
	
	<ul id="team-mos-all-middle-box">
		<c:forEach var="member" items="${team.secondingMembersTop9}">
			<c:set var="user" value="${member.user}" />
			<li>
				<div style="padding: 2px; border:1px solid gray;">
					<a href="../player/index.jsplayout.vi?username=<c:out value="${user.username}"/>">
					<img width="100" height="100" src="<c:out value='${user.avatorUrl }'/>" />
					</a>
				</div>
				<div style="text-align: center; padding-top: 5px;">
					<a href="../player/index.jsplayout.vi?username=<c:out value="${user.username}"/>">
						<c:out value='${user.nickname}'/></a>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
</div>

<script type="text/javascript">
Team_MOS_All_Page = {
	url: '../team/membersOfSecond_all.jsp.vi?teamid=' + Team.id,
	
	back: function() {
		Team_TeamSpace_Page.left_load('../team/coll.jsp.vi?teamid=' + Team.id);
	}
}

$(document).ready(function() {
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		Team_Coll_Page.load($(this).attr('href'));		
		return false;
	});
});

</script>