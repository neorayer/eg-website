<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#team-mof-any-top-more-link {
	display: block;
	float: right;
}
</style>

<div id="team-mof-any-top-box" class="team-visitor-title">
	<span style="float: left;">
		一线队员:
	</span>	
	<a id="team-mof-any-top-more-link" href="javascript:" onclick="Team_MOF_Any_Page.more();">更多..</a>
</div>
<ul id="team-mof-any-middle-box" class="team-visitors">
	<c:forEach var="member" items="${team.firstingMembersTop9}">
		<c:set var="user" value="${member.user}" />
		<li>
			<div class="team-visitors-pic-box" title="<c:out value='${user.dispName}'/>">
				<a href="../player/index.jsplayout.vi?username=<c:out value="${user.username}"/>">
					<img width="46" height="45" src="<c:out value='${user.avatorSmallUrl }'/>" />
				</a>
			</div>
			<div class="team-visitors-name-box" title="<c:out value='${user.dispName}'/>">
				<a href="../player/index.jsplayout.vi?username=<c:out value="${user.username}"/>">
					<c:out value='${user.dispName}'/>
				</a>
			</div>
		</li>
	</c:forEach>
</ul>

<script type="text/javascript">
Team_MOF_Any_Page = {
	url: "../team/membersOfFirst_any.jsp.vi?teamid=<c:out value='${team.id}'/>",
	
	more: function() {
		Team_TeamSpace_Page.left_load('../team/membersOfFirst_all.jsp.vi?teamid=' + Team.id);
	},
	
	reload: function() {
		$('#PL_MOF_Any').load(this.url);
	}
}

</script>