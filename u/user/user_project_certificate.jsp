<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">



</style>

<ul class="certificate-box">
	<li class="certificate-left-box">
		<div class="certificate-album" style="background:url(../_css/images/portal/<c:out value='${userProInfo.matchTypeId}'/>.jpg)"/>
	</li>
	<li class="certificate-right-box">
		<ul class="certificate-label-box">
			<li id="menu_certi_index" onclick="Certificate.open('certi_index')" class="certi-right-a" >
				<a href="javascript:"  >证件首页</a>
			</li>
			<li id="menu_match_his" onclick="Certificate.open('match_his')" class="certi-right-a">
				<a href="javascript:"  >职业生涯</a>
			</li>
			<li id="menu_user_credit" onclick="Certificate.open('user_reputation')" class="certi-right-a">
				<a href="javascript:" >信誉评价<c:if test='${needRecall>0}'><span style="color:red">(<c:out value='${needRecall}'/>)</span></c:if></a>
			</li>
			
			<li id="menu_match_vadio" onclick="Certificate.open('match_replayhis')" class="certi-right-a">
				<c:if test="${user.profession == 'Player'}">
					<a href="javascript:" >参赛录像</a>
				</c:if>
			 <!-- 
				<c:if test="${user.profession == 'Referee'}">
					<a href="javascript:" onclick="Certificate.open('match_referee')">裁定的比赛</a>
				</c:if>
				<c:if test="${user.profession == 'Organizer'}">
					<a href="javascript:" onclick="Certificate.open('match_organization')">组织的比赛</a>
				</c:if>
			 -->
			</li>
			 
			
		</ul>
	</li>
</ul>


<script type="text/javascript">

Certificate = {
	matchTypeId: "<c:out value='${userProInfo.matchTypeId}'/>",
	username: "<c:out value='${user.username }'/>",
	open: function(menuId) {
		$('.certificate-album').load('../user/' + menuId +'.jsp.vi?matchtypeid=' + Certificate.matchTypeId +"&username="+ Certificate.username);
	},
	
	selectFirst: function() {
		var menuId = $('.certificate-label-box :first-child').attr('id').replace('menu_', '');
		Certificate.open(menuId);
	}
}

$(document).ready(function() {
	$(".certi-right-a:eq(0)").addClass("usercerti-selected");
	$(".certi-right-a").click(function(){
		$(".certi-right-a").removeClass("usercerti-selected");
		$(this).addClass("usercerti-selected");
	});
	Certificate.selectFirst();
});
</script>