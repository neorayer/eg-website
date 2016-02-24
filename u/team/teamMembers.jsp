<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<form id="team-teammembers-search-form" action="../team/teamMembers.json.vi" method="post">
	<span class="label">职位：</span>
	<select id="team-search-role" name="roleid" onchange="Team_TeamMembers_Page.searchMembers();">
		<option value="" selected="selected">所有人</option>
		<c:forEach var="role" items="${team.roles}">
			<option value="<c:out  value='${role.id }' />"
				<c:if test="${ role.id == roleid }">
					selected
				</c:if>
			>
				<c:out value='${role.name}'/>
			</option>
		</c:forEach>
	</select>
	&nbsp;
	<span class="label">用户名：</span>
	<input id="team-search-username" type="text" name="username" value="<c:out value='${username }'/>" />
	<input type="submit" class="button" value="查询" onclick="Team_TeamMembers_Page.searchMembers();return false;" />
</form>

<table id="team-teammembers-list-box">
	<thead>
		<tr>
			<td>用户名</td>
			<td>游戏名</td>
			<td>职位</td>
			<td>操作</td>
			<td>管理</td>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="member" items="${teammembers}">
		<c:set var="user" value="${member.user}" />
		<tr class="main">
			<td class="main-td">
				<span title="用户名"><c:out value='${member.username}' /></span>
			</td>
			<td class="main-td">
				<c:out value='${user.nickname}'/>
			</td>
			<td class="main-td">
				<c:out value='${member.role.name }'/>						
			</td>
			<td class="main-td">
				<c:if test="${ACTOR.username != member.username}">
					<a href="javascript:" onclick="<c:out value='Team_TeamMembers_Page.send_msg("${member.username }")'/>">
						【发消息】
					</a>
				</c:if>
			</td>
			<td class="main-td" width="20%">
				<c:if test="${!member.isLeader && ACTOR.teamMember.hasMMember}">
					<a href="javascript:" onclick="<c:out value='Team_TeamMembers_Page.mod_member("${member.username }")'/>">
						【修改】
					</a>
					<a href="javascript:" onclick="<c:out value='Team_TeamMembers_Page.delete_member("${member.username }")'/>">
						【删除】
					</a>
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
<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>


<script type="text/javascript">

var Team_TeamMembers_Page = {
	delete_member: function(username) {
		TeamMember.del(username, function() {
			Team_TeamMembers_Page.load();
		});
	},
	mod_member: function(username) {
		var $page = $('<div class="message-win"/>').load('../team/member.mod.jsp.vi?teamid=' + Team.id +'&username='+username);
		PopupWinX.create($page).show();
	},
	load: function (){
		//window.location.reload();
		$team_tabs.tabs('select', 1);
		$team_tabs.tabs( 'url' , 1 , '../team/teamMembers.jsp.vi?id=' + Team.id);
		$team_tabs.tabs( 'load' , 1 );
	},
	
	send_msg: function(username) {
		var $page = $('<div class="message-win"/>').load('../team/msg.member_send.jsp.vi?id=' + Team.id +'&username='+username);
		PopupWinX.create($page).show();			
	},
	
	searchMembers: function() {
		var query = $('#team-teammembers-search-form').formSerialize();
		$team_tabs.tabs('select', 1);
		$team_tabs.tabs( 'url' , 1 , '../team/teamMembers.jsp.vi?id=' + Team.id + "&" + query);
		$team_tabs.tabs( 'load' , 1 );
	}
};

(function($){
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href');
		url += "&id=" + Team.id + "&" + $('#team-teammembers-search-form').formSerialize();
		$('#tabs-member-box').load(url);		
		return false;
	});
	
	// 列表行
	$("#team-teammembers-list-box").find(".main")
		.mouseover(function() {
			$(this).addClass("hover");
		})
		.mouseout(function() {
			$(this).removeClass("hover");
		})
		.click(function() {
			var tr = $(this).next();
			if (tr.css("display") == "none") {
				tr.show();
				tr.click(function() {
					tr.hide();
				});
			}
			else
				tr.hide();
		});
	
})(jQuery);

	
</script>