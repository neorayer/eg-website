<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
.ui-tabs-selected {
	background-color: #ffffff;
}
</style>

<ul class="user-space-left-title" id="user-space-left-title">
	
	<li><a class="tab" title="tabs-space-left-title" href="../freshMsg/msgs.jsp.vi?username=<c:out value='${username}'/>">新鲜事</a></li>
	<li>
		<a class="tab" title="tabs-space-left-title" href="../userMsg/msgs.jsp.vi?username=<c:out value='${username}'/>">
		消息
			<c:if test="${msgcount ne '0'}">
			&#40;<span style="color: red;"><c:out value="${msgcount}"/></span>&#41;	
			</c:if>
		</a>
	</li>
	<li><a class="tab" title="tabs-space-left-title" href="../article/articles.jsp.vi?username=<c:out value='${username}'/>">个人日志</a></li>
	<li><a class="tab" title="tabs-space-left-title" href="../album/user.albumlist.jsp.vi?username=<c:out value='${username}'/>">个人相册</a></li>
	<li>
		<a class="tab" title="tabs-space-left-title" href="../userFriend/index.jsp.vi?username=<c:out value='${username}'/>">
		好友信息
		<c:if test="${friendscount ne '0'}">
			&#40;<span style="color: red;"><c:out value="${friendscount}"/></span>&#41;	
		</c:if>
		</a>
	</li>
</ul>
<div  id="tabs-space-left-title">
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	var $user_tabs =$("#user-space-left-title").tabs({
		show: function(event, ui) {
			$(ui.tab).blur();
		}
	});
	
	/*
	<c:if test="${not empty user.avatorUrl}">
		var avatorUrl = '<c:out value="${user.avatorUrl}"/>';
		if(avatorUrl == "/51bisaifiles/user/default.jpg")
			$user_tabs.tabs('select', 2);
			
	</c:if>
	*/
});

 
</script>
