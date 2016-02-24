<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<div id="team-space-box">
	<div class="team-space-left-box">
		<c:import url="../team/coll.jsp.vi?teamid=${team.id}"></c:import>
	</div>
	<div class="team-space-right-box">
		<div id="PL_MOF_Any" style="margin-bottom: 20px;">
			<c:import url="../team/membersOfFirst_any.jsp.vi?teamid=${team.id}"></c:import>
		</div>
		<div id="PL_MOS_Any" style="margin-bottom: 20px;">
			<c:import url="../team/membersOfSecond_any.jsp.vi?teamid=${team.id}"></c:import>
		</div>
		<!-- 
		<div>
			<ul class="team-visitor-box">
				<li>
					<div class="team-visitor-title">啦啦队员:</div>
					<div class="team-visitors"></div>
				</li>
			</ul>
		</div>
		 -->
	</div>
</div>

<script type="text/javascript">

Team_TeamSpace_Page = {
	left_load: function(url) {
		$('.team-space-left-box').load(url);
	}	
}

$(document).ready(function() {
	Team.id = "<c:out value='${team.id}'/>";
});

</script>