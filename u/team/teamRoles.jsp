<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--
/* team_roles page */

 #team-role-add-form {
 	padding: 2px;
 	margin-bottom: 5px;
 }
  
 #team-roles-list{
 	width:100%;
 	text-align: center;
 }
 
 #team-roles-list thead th {
	border:1px solid gray;
	background-color: #E6E6E6;
	height: 30px;
 }

 #team-roles-list tbody td {
	border:1px solid gray;
	height: 30px;
 }
 
 #team-roles-powers-list {
 	width: 320px;
 	margin-left: 20px;
 }
 
 #team-roles-powers-list li {
 	float: left;
 	margin: 3px 20px 3px 0px;
 }
 
-->
</style>

<c:set var="hasMRole" value="${not empty me && me.hasMRole}" />
<c:if test="${hasMRole}">
	<form id="team-role-add-form" action="../team/teamRole.add.json.do" method="post" onsubmit="Team_Roles_Page.do_add(this);return false;">
		<input type="hidden" name="teamid" value="<c:out value='${team.id }' />" />
		
		<span class="label">职位名称：</span>
		<input maxlength="10" type="text" name="name" value="">
		<input class="button" name="submit" type="submit" value="新增">
	</form>
</c:if>
	
<table id='team-roles-list'>
	<thead>
		<tr>
			<th width="100px">职位名称</th>
			<th>权限</th>
			<th width="150px">管理</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="role" items="${team.roles}">
		<tr>
			<td>
				<c:out value='${role.name}'/>
			</td>
			<td>
				<ul id="team-roles-powers-list">
				<c:set var="powers" value="${role.powers}"/>
				<c:forEach var="authority" items="${authoritys}">
					<li>
						<c:out value="${authority.desc}" />
						
						<c:set var="flag" value="0" />
						<c:forEach var="power" items="${powers}">
							<c:if test="${authority.code == power.powerId}">
								<c:set var="flag" value="${flag + 1}" />
							</c:if>
						</c:forEach>
						
						<c:if test="${flag > 0}">
							<span style="color: green;">(√)</span>
						</c:if>
						<c:if test="${flag == 0}">
							<span style="color: red;">(×)</span>
						</c:if>
					</li>
				</c:forEach>
				</ul>
			</td>
			<td>
				<c:if test="${!role.isSys && hasMRole}">
					<a class="button" href="javascript:" onclick="Team_Roles_Page.vi_mod('<c:out value='${role.id }'/>');">编辑</a>
					<a class="button" href="javascript:" onclick="Team_Roles_Page.do_del('<c:out value='${role.id }'/>');" >删除</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>


<script type="text/javascript">

var Team_Roles_Page = {
	url: '../team/teamRoles.jsp.vi',
	
	do_add: function(form) {
		if($.trim(form.name.value) == '') {
			alert('职位名称不能为空!');
			return false;
		}
			
		$(form).ajaxSubmit(function(data) {
			alert('保存成功!');
			Team_Roles_Page.load();
		});
	},
	
	do_del: function(id) {
		if(!confirm("您确定要删除该职位吗?"))
			return false;
		
		var url ='../team/teamRole.del.json.do';
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data:{
				'teamid':Team.id,
				'id':id
			},
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				
				alert("删除成功!");
				Team_Roles_Page.load();
		   	}
		});
		
		return false;
		
	},
	
	vi_mod: function(roleId) {
		var $page = $('<div class="message-win"/>').load('../team/role.mod.jsp.vi?teamid=' + Team.id +"&roleid="+ roleId);
		PopupWinX.create($page).show();
	},
		
	load: function (){
		Team_Coll_Page.load(Team_Roles_Page.url);
	}
};

$(document).ready(function() {
	
});

</script>
