<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>
<div id="page-friend-find-box" class="page-box">

	<h3 class="h3-title hd-box green">
		<span class="h3-left">
			<img src="../_css/images/friend/friend-icon.gif" width="16" height="16" /> 
		</span>
		<span class="h3-center">
			<a href="javascript:UserFriend.reload();">我的好友</a>
		</span>
	</h3>
	
	<div style="clear: both;"></div>
	
	<div id="page-friend-find-body-box" class="bd-box">	
		<div class="friends-top-box">	
			<form action="../userFriend/find.jsplayout.vi" method="post" id="page-userfriend-search-form">
				<input type="text" name="searchuser" id="searchuser" value=""/>
				<input type="submit" value="搜索"  onclick="UserFriend.search();return false;" title="搜索" class="button"/>
				<input type="button" value="返回" onclick="UserFriend.reload();"   title="返回" class="button"/>
			</form>
			<div class="page-userfriend-toolbut-box">
				<input type="button" value="权限设置" onclick="UserFriend.setting();"   title="权限设置" class="button"/>
			</div>
			<div style="clear: both;"></div>	
		</div>
		
		<div id="page-friend-find-middle-box">
			<h3>搜索信息</h3>
			<ul id="page-friend-find-list">
				<c:forEach items="${users}" var="user">
					<li>	
						<a href="../player/index.jsplayout.vi?username=<c:out value="${user.username}"/>">	
						<img class="logo-middle-image friends-find-left-img" src="<c:out value='${user.avatorUrl}'/>" align="bottom" />
						</a>
						<div class="page-friend-find-info-box">
							<a onclick="UserFriend.vi_user_page('<c:out value="${user.username}"/>')" href="javascript:"><c:out value="${user.dispName}"/>&nbsp;的主页</a>
							<br/>
							<a onclick="UserFriend.add_userfriend('<c:out value="${user.username}"/>')" href="javascript:" id="addfriend-link">加为好友</a>
						</div>
					</li>
					<div style="clear:both;"></div>
				</c:forEach>
			</ul>
			<div id="page-userfriend-find-pagebar-box" ><c:out value="${pageBar}" escapeXml="false" /></div> 	
		</div>
	</div>
</div>			
<script type="text/javascript">

$(document).ready(function() {
	 
});
 
</script>
