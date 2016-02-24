<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<ul class="certificate-box">
	<li class="certificate-left-box">
		<div class="certificate-album" style="background:url(../_css/images/portal/<c:out value='${teamProInfo.matchTypeId}'/>.jpg)"/>
	</li>
	<li class="certificate-right-box">
		<ul class="certificate-label-box">
			<li id="menu_certi_index" onclick="Certificate.open('certi_index')" class="certi-right-a">
				<a href="javascript:" >证件首页</a>
			</li>
			<!-- 
			<li id="menu_match_his">
				<a href="javascript:" onclick="Certificate.open('match_his')">参赛历史</a>
			</li>
			
			 -->
			<li id="menu_team_credit" onclick="Certificate.open('team_reputation')" class="certi-right-a">
				<a href="javascript:" >信誉评价<c:if test='${needRecall>0}'><span style="color:red">(<c:out value='${needRecall}'/>)</span></c:if></a>
			</li>
			<li id="menu_match_vadio" onclick="Certificate.open('match_replayhis')" class="certi-right-a">
				<a href="javascript:" >参赛录像</a>
			</li>
		</ul>
	</li>
</ul>

<script type="text/javascript">

Certificate = {
	teamId: "<c:out value='${teamProInfo.teamId}'/>",
	matchTypeId: "<c:out value='${teamProInfo.matchTypeId}'/>",

	open: function(menuId) {
		$('.certificate-album').load('../team/' + menuId +'.jsp.vi?matchtypeid=' + Certificate.matchTypeId + '&teamid=' + Certificate.teamId);
	},
	
	selectFirst: function() {
		var menuId = $('.certificate-label-box :first-child').attr('id').replace('menu_', '');
		Certificate.open(menuId);
	}
}

$(document).ready(function() {
	$(".certi-right-a:eq(0)").addClass("teamcerti-selected");
	$(".certi-right-a").click(function(){
		$(".certi-right-a").removeClass("teamcerti-selected");
		$(this).addClass("teamcerti-selected");
	});
	Certificate.selectFirst();
});
</script>