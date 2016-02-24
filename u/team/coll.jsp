<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
.ui-tabs-selected {
	background-color: #ffffff;
}
</style>

<ul class="team-space-left-title">
	<li  style="position: relative">
		<c:set var="applysCount" value="${applysCount}"/>
		<c:if test="${not empty applysCount && applysCount > 0 && not empty me && me.isLeader}">
			<div id="team-applysCount-box" style="color: red; position: absolute; top: -25px; left: -25px;width: 150px;">
				(<span><c:out value='${applysCount}'/></span>个成员想加入您的战队)
			</div>
		</c:if>
		
		
		<a title="team-space-left-body" href="<c:out value='../teamMsg/msgs.jsp.vi?teamid=${team.id}'/>" onclick="$('#team-applysCount-box').hide();">
			战队信息
		</a>
		<!-- 
		<div id="team-msgs-new-count" style="color: red;position: absolute; top: -25px; left: -25px; width: 150px;">(您有N条新的消息)</div>
		 -->
	</li>
	<li>
		<a title="team-space-left-body" href="<c:out value='../team/bbses.jsp.vi?teamid=${team.id}'/>">
			战队BBS
		</a>
	</li>
	<li>
		<a title="team-space-left-body" href="<c:out value='../team/members.jsp.vi?teamid=${team.id}'/>">
			战队成员
		</a>
	</li>
	
	<li>
		<a title="team-space-left-body" href="<c:out value='../team/teamRoles.jsp.vi?teamid=${team.id}'/>">
			职位
		</a>
	</li>
</ul>
<div id="team-space-left-body">
</div>

<script type="text/javascript">
Team_Coll_Page = {
	load: function(url) {
		$('#team-space-left-body').load(url);
	}
};

$(document).ready(function() {
	$(".team-space-left-title").tabs({
		show: function(event, ui) {
			$(ui.tab).blur();
		}
	});
});

</script>
