<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">

#users-top-box {
	border:1px solid gray;
	background-color: #ffffff;
	margin-bottom: 10px;
	line-height: 25px;
	height: 25px;
	padding: 5px;
	padding-left:5px;
	style="text-align: left;"
}

#page-users-list {
	text-align: center;
}

#portal-index-users-list thead th {
	border:1px solid gray;
}

#portal-index-users-list td{
	border: 1px solid gray;
}
</style>

<div>
	
	<div id="users-top-box">
		<form id="users-user-search-form" action="" method="post">
			<span class="label">游戏名：</span> 
			<input type="text" name="nickname" value=""/>
			<input type="submit" value="搜索" onclick="Portal_users_Page.search();return false;"/>
		</form>	
	</div>

	<table id="portal-index-users-list" width="100%" cellspacing="0" cellpadding="5">
		<thead>
			<tr>
			<!-- 
				<th width="20%">头像</th>
			-->
				<th width="20%">平台用户名</th>
				<th width="15%">职业</th>
				<th width="15%">战区</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
			<tr>
			<!-- 
				<td align=center>
					<div class="all-team-logo-box">
						<a href="<c:out value='#'/>">
							<img src="<c:out value='${ACTOR.avatorSmallUrl}'/>" width="46px" height="62px"/>
						</a>
					</div>	
				</td>
			-->
				<td>
					<a href="<c:out value='../player/index.jsplayout.vi?username=${user.username}'/>">
						<c:out value='${user.dispName}'/>
					</a>
				</td>
				<td>
					<a href="<c:out value='../player/index.jsplayout.vi?username=${user.username}'/>">
						<c:out value='${user.strProfession}'/>
					</a>
				</td>
				<td>
				 	<a href="<c:out value='../player/index.jsplayout.vi?username=${user.username}'/>">
				 		<c:if test="${not empty user.warZone}">
					 		<c:out value='${user.warZone.name}'/>
				 		</c:if>
				 		<c:if test="${empty user.warZone}">
					 		无
				 		</c:if>
					</a>
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
Portal_users_Page = {
	search: function(){
		var $form = $('#users-user-search-form');
		var query = $form.formSerialize();
		var url ='../portal/searchUsers.jsplayout.vi?'+query;
		window.location= url;
	}
}

$(document).ready(function() {
	
});

</script>