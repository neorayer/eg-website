<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#page-users-list {
	text-align: center;
}

#page-users-list thead th {
	border:1px solid gray;
}

#users-top-box {
	border:1px solid gray;
	background-color: #ffffff;
	margin-bottom: 10px;
	line-height: 25px;
	height: 25px;
	padding: 5px;
}

#users-team-search-form {
	padding-left:5px;
	display: inline;
	float: left;
}
#page-users-numbers-box {
	float: right;
}

.hover{
	background-color: #dedede;
}
#page-users-list tbody td{
	border-bottom:1px solid #d8dfea;
}

</style>

<div id='page-body-box'>
	<div class="hd-box">
		<h3 class="green">
			用户搜索
			<a href="../portal/index.jsplayout.vi" class="button"  style="margin-left: 800px;">返回</a>
		</h3>
	</div>
	<div class="bd-box">
	
	<div style="text-align: left;">
		<div id="users-top-box">
			<form id="users-team-search-form" action="../portal/searchUsers.jsp.vi" method="post">
				<span class="label">游戏名：</span> 
				<input type="text" name="nickname" value="<c:out value="${nickname }"/>"/>
				<input type="submit" value="搜索" onclick="Portal_Users_Page.search();return false;"/>
				<span id="users-team-search-errors-box" style="color: red;"></span>
			</form>	
			
			<span id="page-users-numbers-box">
				当前共有
				<span style="color: red;"><c:out value="${userNum}" /></span>
				注册用户！	
			</span>
			<div style="clear: both;"></div>
		</div>
		
	</div>
	
	<table id="page-users-list" width="100%" cellspacing="0" cellpadding="5">
		<thead>
			<tr>
				<th width="10%">头像</th>
				<th width="20%">名字</th>
				<th width="15%">职业</th>
				<th width="15%">战区</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
			<tr>
				<td align="center" width="10%">
					<div class="allvisitor-page-album">
						<a href="<c:out value='../player/index.jsplayout.vi?username=${user.username}'/>">
							<img src="<c:out value='${user.avatorSmallUrl}'/>" width="50px" height="50px"/>
						</a>
					</div>	
				</td>
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
	<div id="page-searchusers-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
	
	</div>
</div>

<script type="text/javascript">
Portal_Users_Page = {
	nickname: '<c:out value="${nickname }"/>',
	search: function(){
		var $form = $('#users-team-search-form');
		var query = $form.formSerialize();
		var url ='../portal/searchUsers.jsplayout.vi?'+query;
		window.location= url;
	}
}

;$(document).ready(function() {
	$('#page-users-list tr')
		.mouseover(function() {
			$(this).addClass("hover");
		})
		.mouseout(function() {
			$(this).removeClass("hover");
		});

	// 分页
	$('#page-searchusers-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href')+'&nickname=' + Portal_Users_Page.nickname;
		window.location=url;
		return false;
		
	});
	
});

</script>

