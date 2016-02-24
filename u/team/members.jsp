<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#team-members-list {
	text-align: center;
}

#team-members-list thead th {
	border:1px solid gray;
	background-color: #E6E6E6;
	height: 30px;
}

#team-members-list tbody td {
	border:1px solid gray;
	height: 30px;
}

</style>

<form id="team-teammembers-search-form" action="../team/teamMembers.json.vi" method="post">
	<span class="label">职位：</span>
	<select id="team-search-role" name="roleid" onchange="Team_Members_Page.searchMembers();">
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
	<input type="submit" class="button" value="查询" onclick="Team_Members_Page.searchMembers();return false;" />
</form>

<table id="team-members-list" width="100%">
	<thead>
		<tr>
			<th>名字</th>
			<th>职位</th>
			<th>电竞等级</th>
			<th>队伍贡献</th>
			<th>战龄</th>
			<th>操作</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="member" items="${members}">
		<c:set var="user" value="${member.user}" />
		<tr>
			<td>
				<a href="../player/index.jsplayout.vi?username=<c:out value='${user.username}'/>">
					<c:out value='${user.dispName}'/>
				</a>
			</td>
			<td>
				<c:out value='${member.role.name }'/>
			</td>
			<td>
				<c:out value='${user.rank}'/>
			</td>
			<td>
				<c:out value='${member.contribute}' default="0"/>
			</td>
			<td>
				0
			</td>
			<td>
				<c:if test="${not empty me && me.hasMMember && !member.isLeader}">
					<a href="javascript:" onclick="<c:out value='Team_Members_Page.mod_member("${member.username }");'/>">升/降级</a>&nbsp;
					<a href="javascript:" onclick="<c:out value='Team_Members_Page.delete_member("${member.username }");'/>">踢出</a>&nbsp;
				</c:if>	
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>

<script type="text/javascript">

Team_Members_Page = {
	url: '../team/members.jsp.vi',

	delete_member: function(username) {
		TeamMember.del(username, function() {
			Team_Members_Page.reload();
		});
	},
	
	mod_member: function(username) {
		var $page = $('<div class="message-win"/>').load('../team/member.mod.jsp.vi?teamid=' + Team.id +'&username='+username);
		PopupWinX.create($page).show();
	},
	
	reload: function (){
		Team_Coll_Page.load(this.url);
	},
	
	send_msg: function(username) {
		var $page = $('<div class="message-win"/>').load('../team/msg.member_send.jsp.vi?id=' + Team.id +'&username='+username);
		PopupWinX.create($page).show();			
	},
	
	searchMembers: function() {
		var query = $('#team-teammembers-search-form').formSerialize();
		Team_Coll_Page.load(Team_Members_Page.url + '?teamid=' + Team.id + "&" + query);
	}
};

$(document).ready(function() {
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href');
		url += "&teamid=" + Team.id + "&" + $('#team-teammembers-search-form').formSerialize();
		Team_Coll_Page.load(url);		
		return false;
	});
});
</script>
