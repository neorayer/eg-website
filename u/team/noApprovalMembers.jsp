<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--
	#team-teammembers-list-box tbody tr.hover td{
		background-color: orange;
	}
	.textInput{
		display: none;
	}
	.modinfo{
		padding:2px 2px;
		width: 400px;
		border: 1px solid #969696;
		background-color: #ffffff;
	}
-->
</style>

<table id="team-teammembers-list-box">
	<thead>
		<tr>
			<td>用户名</td>
			<td>游戏名</td>
			<td>申请时间</td>
			<td>管理</td>
		</tr>
	</thead>
	
	<tbody>
		<c:set var="hasAApply" value="${ACTOR.teamMember.hasAApply}" />
		<c:forEach var="member" items="${team.notPassedMembers}">
		<c:set var="user" value="${member.user}" />
		<tr class="main">
			<td class="main-td">
				<span title="用户名"><c:out value='${member.username}' /></span>
			</td>
			<td class="main-td">
				<c:out value='${user.nickname}'/>
			</td>
			<td class="main-td">
				<c:out value='${member.createDateTime }'/>						
			</td>
			<td class="main-td" width="25%">
			<c:if test="${hasAApply}">
				<button class="button" onclick="Team_noApprovalMembers_Page.pass_apply('<c:out value='${member.username }'/>');">批准</button>
				&nbsp;
				<button class="button" onclick="Team_noApprovalMembers_Page.refuse_apply('<c:out value='${member.username }'/>');">拒绝</button>
			</c:if>
			</td>
			
		</tr>
		<tr class="detail">
			<td colspan="5">
				<div id="team-teammembers-list-detail-box">
					<table>
						<tr>
							<td>用户名:</td>
							<td><c:out value='${user.username}'/></td>
							<td>游戏名:</td>
							<td><c:out value='${user.nickname}'/></td>						
						</tr>
						<tr>
							<td>等级:</td>
							<td><c:out value='${user.level}'/></td>
							<td>积分:</td>
							<td><c:out value='${user.point}'/></td>			
						</tr>
						<tr>
							<td>注册时间:</td>
							<td><c:out value='${user.regDateTime}'/></td>
							<td>电话:</td>
							<td><c:out value='${user.phone}'/></td>				
						</tr>
						<tr>
							<td>Email:</td>
							<td><c:out value='${user.email}'/></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<script type="text/javascript">

var Team_noApprovalMembers_Page = {
	pass_apply: function(username) {
		if(!confirm("您确定要批准该玩家加入吗?"))
			return false;
		Team.pass_apply(username, function() {
			Team_noApprovalMembers_Page.load();
		});
	}, 
	
	refuse_apply: function(username) {
		if(!confirm("您确定要拒绝该玩家的申请吗?"))
			return false;
		Team.refuse_apply(username, function() {
			Team_noApprovalMembers_Page.load();
		});
	},
	
	load: function (){
		$team_tabs.tabs('select', 3);
		$team_tabs.tabs( 'url' , 3 , '../team/noApprovalMembers.jsp.vi?id=' + Team.id);
		$team_tabs.tabs( 'load' , 3 );
	}
};
(function($){

})(jQuery);

var memberRows = $("#team-teammembers-list-box").find(".main");
//hover 效果
memberRows.mouseover(
	function() {
		$(this).addClass("hover");
	}
);

memberRows.mouseout(
	function() {
		$(this).removeClass("hover");
	}
);	
//显示成员详细信息
memberRows.click(
	function() {
		var tr = $(this).next();
		if (tr.css("display") == "none") {
			tr.show();
			tr.click(function() {
				tr.hide();
			});
		}
		else
			tr.hide();
	}
);

</script>